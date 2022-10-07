package com.efrog.frogbot.model.service;


import com.efrog.frogbot.model.dao.WishDao;
import com.efrog.frogbot.model.pojo.Outcome;
import com.efrog.frogbot.model.pojo.WishEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
public class WishService {

    private Random random = new Random();

    @Autowired
    private WishDao wishDao;

    public WishEntry wishCharacterSingle(long userId) {
        Outcome outcome;
        int nextRandom = random.nextInt(75);
        outcome = (nextRandom <= 0? Outcome.GoldenPositive: Outcome.Blue);
        WishEntry wish =  new WishEntry(0L, userId, outcome);
        wishDao.insert(wish);
        return wish;
    }

    public List<WishEntry> wishCharacterBatch(long userId, int count) {
        if (count <= 0)
            return new ArrayList<>(1);

        ArrayList<WishEntry> wishes = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            WishEntry wish = wishCharacterSingle(userId);
            wishes.add(wish);
        }

        Collections.reverse(wishes);
        return wishes;
    }
}
