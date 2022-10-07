package com.efrog.frogbot.model.util.lookback;

import com.efrog.frogbot.model.pojo.Lookback;

public interface Lookbacker {
    Lookback lookback(long userId);
}
