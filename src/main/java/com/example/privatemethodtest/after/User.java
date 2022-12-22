package com.example.privatemethodtest.after;

/**
 * @param userId
 * @param brithday yyyy-MM-dd
 * @param zoneId   Where the user lives, US/Pacific (UTC-07:00), Asia/Tokyo
 *                 (UTC+09:00), Asia/Manila (UTC+08:00)
 */
public record User(int userId, String birthday, String zoneId) {
}
