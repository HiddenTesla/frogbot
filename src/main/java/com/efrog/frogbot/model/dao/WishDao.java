package com.efrog.frogbot.model.dao;

import com.efrog.frogbot.model.database.mapper.WishMapper;
import com.efrog.frogbot.model.pojo.WishEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WishDao {

    @Autowired
    private WishMapper wishMapper;

    public void insert(WishEntry wishEntry) {
        wishMapper.insert(wishEntry);
    }

}