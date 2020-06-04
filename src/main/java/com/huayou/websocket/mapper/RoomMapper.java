package com.huayou.websocket.mapper;

import com.huayou.websocket.entity.Room;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomMapper {
    int deleteByPrimaryKey(Integer roomId);

    int insert(Room record);

    int insertSelective(Room record);

    Room selectByPrimaryKey(Integer roomId);

    int updateByPrimaryKeySelective(Room record);

    int updateByPrimaryKey(Room record);

    List<Room> selectAllRoom();
}