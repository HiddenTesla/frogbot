package com.efrog.frogbot.model.service;


import com.efrog.frogbot.model.dao.WishDao;
import com.efrog.frogbot.model.pojo.Lookback;
import com.efrog.frogbot.model.pojo.Outcome;
import com.efrog.frogbot.model.pojo.WishEntry;
import com.efrog.frogbot.model.util.Dice;
import com.efrog.frogbot.model.util.lookback.Lookbacker;
import com.efrog.frogbot.model.util.probability.GoldenCharacterVendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class WishService {

    private Dice dice = new Dice();

    @Autowired
    private WishDao wishDao;

    @Value("${wish.guarantee.character.purple}")
    private int characterPurpleGuarantee;

    @Value("${wish.guarantee.character.golden}")
    private int characterGoldenGuarantee;

    @Autowired
    @Qualifier("directLookbacker")
    private Lookbacker lookbacker;

    @Autowired
    private GoldenCharacterVendor goldenCharacterVendor;

    public WishEntry wishCharacterSingle(long userId) {
        Outcome outcome = generateNextOutcome(userId);
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

    public List<WishEntry> findWishHistory(long userId) {
        return wishDao.findByUserId(userId, characterGoldenGuarantee);
    }

    private Outcome generateNextOutcome(long userId) {
        Lookback lookback = lookbacker.lookback(userId);

        // Guaranteed golden
        if (lookback.untilLastGolden >= characterGoldenGuarantee - 1) {
            if (lookback.lastGolden == Outcome.GoldenNegative) {
                return Outcome.GoldenPositive;
            }
            return randomGolden();
        }

        // Lucky enough to get a golden
        if (goldenCharacterVendor.yieldGolden(lookback.untilLastGolden)) {
            if (lookback.lastGolden == Outcome.GoldenNegative) {
                return Outcome.GoldenPositive;
            }
            return randomGolden();
        }

        // Guaranteed purple
        if (lookback.untilLastPurpleOrGolden >= characterPurpleGuarantee - 1) {
            if (lookback.lastPurpleOrGolden == Outcome.PurpleNegative) {
                return Outcome.PurplePositive;
            }
            return randomPurple();
        }

        // Lucky enough to get a purple
        if (dice.roll(characterPurpleGuarantee)) {
            if (lookback.lastPurpleOrGolden == Outcome.PurpleNegative) {
                return Outcome.PurplePositive;
            }
            return randomPurple();
        }

        // No golden or purple :(
        return Outcome.Blue;
    }

    private Outcome randomGolden() {
        return dice.roll(2)? Outcome.GoldenNegative: Outcome.GoldenPositive;
    }

    private Outcome randomPurple() {
        return dice.roll(2)? Outcome.PurpleNegative: Outcome.PurplePositive;
    }
}
