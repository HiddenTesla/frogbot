package com.efrog.frogbot.controller;

import com.efrog.frogbot.model.database.mapper.WishMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/wish")
public class WishController {

    @Autowired
    private WishMapper wishMapper;

    @RequestMapping(method = RequestMethod.POST, path = "/{userId}/character/single")
    public int wishOne(@PathVariable long userId) {
        return wishMapper.frog();
    }
}
