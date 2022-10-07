package com.efrog.frogbot.controller;

import com.efrog.frogbot.model.pojo.Lookback;
import com.efrog.frogbot.model.util.lookback.Lookbacker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/lookback")
public class LookbackController {

    @Autowired
    @Qualifier("directLookbacker")
    private Lookbacker lookbacker;

    @RequestMapping(method = RequestMethod.GET, path = "/{userId}")
    public Lookback getLookback(@PathVariable long userId) {
        return lookbacker.lookback(userId);
    }
}
