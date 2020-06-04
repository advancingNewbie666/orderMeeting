package com.huayou.websocket.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface codeListMapper {
    List<String> findAll();
}
