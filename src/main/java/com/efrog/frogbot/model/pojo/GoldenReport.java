package com.efrog.frogbot.model.pojo;

public class GoldenReport {
    private long userId;
    private int count;
    private Outcome outcome;

    public long getUserId() {
        return userId;
    }

    public GoldenReport setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public int getCount() {
        return count;
    }

    public GoldenReport setCount(int count) {
        this.count = count;
        return this;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public GoldenReport setOutcome(Outcome outcome) {
        this.outcome = outcome;
        return this;
    }
}

