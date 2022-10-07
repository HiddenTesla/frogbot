package com.efrog.frogbot.model.util.probability;

import com.efrog.frogbot.model.util.Dice;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "wish.probability.character")
public class GoldenCharacterVendor implements InitializingBean {

    private Map<String, Integer> gliding;

    private Dice dice = new Dice();

    @Value("${wish.probability.character.denominator}")
    private int denominator;

    @Value("${wish.probability.character.threshold}")
    private int threshold;

    private Map<Integer, Integer> unluckyProb;

    @Value("${wish.probability.character.lucky}")
    private int lucky;

    public Map<String, Integer> getGliding() {
        return gliding;
    }

    public GoldenCharacterVendor setGliding(Map<String, Integer> gliding) {
        this.gliding = gliding;
        return this;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        unluckyProb = new HashMap<>();
        for (Map.Entry<String, Integer> e: gliding.entrySet()) {
            Integer newKey = Integer.valueOf(e.getKey());
            unluckyProb.put(newKey, e.getValue());
        }
        return;
    }

    public boolean yieldGolden(int untilLastGolden) {
        int ordinal = untilLastGolden + 1;
        if (ordinal < threshold)
            return dice.roll(lucky, denominator);
        else
            return dice.roll(ordinal, denominator);
    }
}
