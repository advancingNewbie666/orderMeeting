package com.huayou.websocket.service;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/websocket/{sid}")
@Component
public class WebSocketServer {

    static Log log = LogFactory.getLog(WebSocketServer.class);
    // 静态变量，用来记录当前在线连接数，应该设计成线程安全的
    private static int onlineCount = 0;
    //concurrent包的線程安全set，用來存放每個客戶端對應的WebSocketServer對象
    private static CopyOnWriteArraySet<WebSocketServer> websocketSet = new CopyOnWriteArraySet<WebSocketServer>();
    // 與某個客戶端的鏈接會話，需要通過他來給客戶端發送數據
    private Session session;
    // 接受sid
    private String sid = "";

    /**
     * 鏈接建立成功後調用的方法
     */
    @OnOpen
    public void onOpen(Session session,@PathParam("sid") String sid) {
        this.session = session;
        websocketSet.add(this);
        log.info("有新窗口開始監聽：" +  sid + ",當前在線人數為："  +  getOnlineCount());
        this.sid  = sid;
        try {
            sendMessage("連接成功");
        } catch (IOException e) {
            log.error("websocket IO異常");
        }
    }

    /**
     * 連接關閉調用的方法
     */
    @OnClose
    public void onClose() {
        websocketSet.remove(this);
        subOnlineCount();
        log.info("有一連接關閉！當前在線人數為" + getOnlineCount());
    }

    /**
     * 收到客戶端消息後調用的方法
     */
    @OnMessage
    public void onMessage(String message, Session session){
        log.info("收到來自窗口" + sid + "的消息：" + message);
        //群發消息
        for(WebSocketServer webSocketServer : websocketSet) {
            try {
                webSocketServer.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 錯誤時調用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 實現服務器主動推送
     */
    private void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 群發自定義消息
     */
    public static void sendInfo(String message, @PathParam("sid") String sid) throws IOException {
        log.info("推送消息到窗口：" + sid + ",推送內容：" + message);
        for(WebSocketServer webSocketServer : websocketSet){
            // null為全部推送
            try{
                if (sid == null) {
                    webSocketServer.sendMessage(message);
                } else if (webSocketServer.sid.equals(sid)) {
                    webSocketServer.sendMessage(message);
                }
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public  static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public  static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
