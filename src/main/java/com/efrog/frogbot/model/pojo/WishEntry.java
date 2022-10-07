package com.efrog.frogbot.model.pojo;

import java.util.Date;

public class WishEntry {
  private long wishId;
  private long userId;
  private Date time;
  private Outcome outcome;

  public WishEntry() {
    super();
  }

  public WishEntry(long wishId, long userId, Outcome outcome) {
    this.userId = userId;
    this.wishId = wishId;
    this.outcome = outcome;
    this.time = new Date();
  }

  public long getWishId() {
    return wishId;
  }

  public WishEntry setWishId(long wishId) {
    this.wishId = wishId;
    return this;
  }

  public long getUserId() {
    return userId;
  }

  public WishEntry setUserId(long userId) {
    this.userId = userId;
    return this;
  }

  public Date getTime() {
    return time;
  }

  public WishEntry setTime(Date time) {
    this.time = time;
    return this;
  }

  public Outcome getOutcome() {
    return outcome;
  }

  public WishEntry setOutcome(Outcome outcome) {
    this.outcome = outcome;
    return this;
  }
}
