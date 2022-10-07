package com.efrog.frogbot.model.util.lookback;

import com.efrog.frogbot.model.dao.WishDao;
import com.efrog.frogbot.model.pojo.Lookback;
import com.efrog.frogbot.model.pojo.Outcome;
import com.efrog.frogbot.model.pojo.WishEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DirectLookbacker implements Lookbacker {

    @Autowired
    private WishDao wishDao;

    @Value("${wish.guarantee.character.golden}")
    private int characterGoldenGuarantee;

    @Override
    public Lookback lookback(long userId) {
        List<WishEntry> wishes = wishDao.findByUserId(userId, characterGoldenGuarantee);
        int untilLastGolden = 0, untilLastPurpleOrGolden = 0;
        boolean foundGolden = false, foundPurpleOrGolden = false;
        Outcome lastGolden = null, lastPurpleOrGolden = null;
        for (WishEntry wish: wishes) {
            if (foundGolden)
                break;

            Outcome outcome = wish.getOutcome();
            if (outcome == Outcome.GoldenPositive || outcome == Outcome.GoldenNegative) {
                foundGolden = true;
                foundPurpleOrGolden = true;
                lastGolden = outcome;
                if (!foundPurpleOrGolden)
                    lastPurpleOrGolden = outcome;

            }
            else if (outcome == Outcome.PurplePositive || outcome == Outcome.PurpleNegative) {
                untilLastGolden++;
                foundPurpleOrGolden = true;
                lastPurpleOrGolden = outcome;
            }
            else {
                if (!foundGolden)
                    untilLastGolden++;
                if (!foundPurpleOrGolden)
                    untilLastPurpleOrGolden++;
            }
        }

        Lookback lookback_ = new Lookback();
        lookback_.userId = userId;
        lookback_.lastGolden = lastGolden;
        lookback_.lastPurpleOrGolden = lastPurpleOrGolden;
        lookback_.untilLastGolden = untilLastGolden;
        lookback_.untilLastPurpleOrGolden = untilLastPurpleOrGolden;
        return lookback_;
    }
}
