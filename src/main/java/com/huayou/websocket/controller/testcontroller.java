package com.huayou.websocket.controller;

import com.huayou.websocket.entity.Use;
import com.huayou.websocket.mapper.UseMapper;
import com.huayou.websocket.service.WebSocketServer;
import com.huayou.websocket.vo.useVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class testcontroller {

    @RequestMapping("/hello")
    public String hello() {

        return "hello";
    }

    @GetMapping("/socket/{sid}")
    public ModelAndView socket(@PathVariable("sid") String sid){
        ModelAndView mav = new ModelAndView("/socket");
        mav.addObject("sid", sid);
        return mav;
    }


    @ResponseBody
    @RequestMapping("/socket/push/{sid}")
    public String pushToWeb (String message, @PathVariable("sid") String sid) {
        try {
            WebSocketServer.sendInfo(message, sid);
        } catch (IOException e) {
            e.printStackTrace();
            return "发送失败";
        }
        return "发送成功";
    }

}
