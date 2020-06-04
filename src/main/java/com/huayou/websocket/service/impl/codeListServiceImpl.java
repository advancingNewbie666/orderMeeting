package com.huayou.websocket.service.impl;

import com.huayou.websocket.mapper.codeListMapper;
import com.huayou.websocket.service.codeListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("codeListService")
public class codeListServiceImpl implements codeListService {

    @Autowired
    private codeListMapper clm;

    @Override
    public List<String> findAll() {
        return clm.findAll();
    }
}
