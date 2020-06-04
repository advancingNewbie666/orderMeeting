package com.huayou.websocket.controller;


import com.huayou.websocket.entity.Room;
import com.huayou.websocket.entity.Use;
import com.huayou.websocket.mapper.RoomMapper;
import com.huayou.websocket.mapper.UseMapper;
import com.huayou.websocket.message.returnMessage;
import com.huayou.websocket.vo.useVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RestController("meetingOrder")
public class MeetingOrderController {

    @Autowired
    private UseMapper useMapper;
    @Autowired
    private RoomMapper roomMapper;
    @GetMapping("/getUseItem")
    public List<useVo> getUseItem(@RequestParam("roomId") String roomId,
                                  @RequestParam("useTime") String useTime,
                                  @RequestParam("beginDate") String beginDate,
                                  @RequestParam("endDate") String endDate){
        List<useVo> listVo = new ArrayList<useVo>();
        List<Use> useList = new ArrayList<>();

        if(useTime!=null&&!"".equals(useTime)){
            useList = useMapper.selectByRoomIdAndUseDate(roomId, useTime);
        }else if (beginDate!=null&&!"".equals(beginDate)&&endDate!=null&&!"".equals(endDate)){
            useList = useMapper.selectByTimes(roomId, beginDate, endDate);
        }

        for (Use use : useList) {
            useVo vo = new useVo();
            BeanUtils.copyProperties(use,vo);
            String str = "yyyy-MM-dd";

            SimpleDateFormat sdf = new SimpleDateFormat(str);
            vo.setUseDate(sdf.format(use.getUseDate()));

            String str1 = "HH:mm:ss";
            SimpleDateFormat strTime = new SimpleDateFormat(str1);
            vo.setStartTime(strTime.format(use.getStartTime()));
            vo.setEndTime(strTime.format(use.getEndTime()));
            listVo.add(vo);
        }

//        listVo.addAll(listVo);

        return listVo;
    }

    @PostMapping("/addUseItem")
    public returnMessage addUseItem(@RequestBody Use use) throws  Exception{
        returnMessage message = new returnMessage();

        // 查询该时间段内是否有预定
        List<Use> list = useMapper.selectByUseDateAndTimes(use);
        if(CollectionUtils.isEmpty(list)){
            useMapper.insert(use);
            message.setMessageCode("200");
            message.setMessageName("预定成功");
        }else{
            message.setMessageCode("550");
            message.setMessageName("该时间段被占用，请检查");
        }
        return message;
    }

    @GetMapping("/getRoomList")
    public List<Room> getRoomList(){

        List<Room> useList = roomMapper.selectAllRoom();

//        listVo.addAll(listVo);

        return useList;
    }
}
