package com.huayou.websocket.mapper;

import com.huayou.websocket.entity.Use;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Use record);

    int insertSelective(Use record);

    Use selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Use record);

    int updateByPrimaryKey(Use record);

    List<Use> selectByRoomIdAndUseDate(@Param("roomId") String roomId,@Param("useDate") String useDate);

    List<Use> selectByUseDateAndTimes(Use record);

    List<Use> selectByTimes(@Param("roomId") String roomId,@Param("beginDate") String beginDate,@Param("endDate") String endDate);
}