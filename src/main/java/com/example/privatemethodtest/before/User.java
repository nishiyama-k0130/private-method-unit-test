package com.example.privatemethodtest.before;


import java.time.ZoneId;

public class User {

    private int userId;
    /**
     * yyyy-mm-dd
     */
    private String birthday;
    /**
     * Where the user lives
     * ユーザーが住んでいるゾーン
     * EX) US/Pacific (UTC-07:00), Asia/Tokyo (UTC+09:00), Asia/Manila (UTC+08:00)
     */
    private ZoneId zoneId;

    public User(int userId, String birthday, ZoneId zoneId) {
        this.userId = userId;
        this.birthday = birthday;
        this.zoneId = zoneId;
    }

    public int getId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public ZoneId getZoneId() {
        return zoneId;
    }
    public void setZoneId(ZoneId zoneId) {
        this.zoneId = zoneId;
    }
}
