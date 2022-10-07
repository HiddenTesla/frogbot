package com.efrog.frogbot.controller;

import com.efrog.frogbot.model.pojo.GoldenReport;
import com.efrog.frogbot.model.pojo.Outcome;
import com.efrog.frogbot.model.pojo.WishEntry;
import com.efrog.frogbot.model.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/wish")
public class WishController {

    @Autowired
    private WishService wishService;

    @RequestMapping(method = RequestMethod.GET, path = "/{userId}/character/history")
    public List<WishEntry> getCharacterWishHistory(@PathVariable long userId) {
        return wishService.findWishHistory(userId);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{userId}/character/single")
    public WishEntry wishCharacterSingle(@PathVariable long userId) {
        return wishService.wishCharacterSingle(userId);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{userId}/character/batch")
    public List<WishEntry> wishCharacterBatch(
            @PathVariable long userId,
            @RequestParam(required = false, defaultValue = "10") int count)
    {
        return wishService.wishCharacterBatch(userId, count);
    }


    @RequestMapping(method = RequestMethod.POST, path = "/{userId}/character/golden")
    public GoldenReport wishUntilGoldenCharacter(@PathVariable long userId) {
        int count = 0;
        Outcome outcome;
        do {
            count++;
            WishEntry wish = wishService.wishCharacterSingle(userId);
            outcome = wish.getOutcome();
        }
        while(outcome != Outcome.GoldenNegative && outcome != Outcome.GoldenPositive);

        GoldenReport goldenReport = new GoldenReport();
        goldenReport.setUserId(userId);
        goldenReport.setCount(count);
        goldenReport.setOutcome(outcome);
        return goldenReport;
    }
}
