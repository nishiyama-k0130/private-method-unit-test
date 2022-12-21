package com.example.privatemethodtest.before;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Userの住んでいるゾーンでの年齢を計算します。
     * Calculate the User's age on their living ZoneId.
     *
     * @param userId
     * @return
     */
    /**
     * Problem
     * このメソッドはテストがしずらい。
     * Repositoryクラスに強く依存しているので、そちらの挙動に左右される。
     * LocalDate.parseの挙動のみをテストしたいのにできない。
     * ChronoUnit.YEARS.betweenの挙動のみをテストしたいのにできない。
     * userIdが存在しない場合の挙動をチェックしたいのにできない。
     */
    public long calculateTimeZoneAge(int userId){
        User user = userRepository.getUser(userId);
        if (ObjectUtils.isEmpty(user)){
            throw new RuntimeException(String.format("UserId does not exist, userId={}", userId));
        }
        LocalDate birthday = LocalDate.parse(user.getBirthday(), DTF);
        ZonedDateTime now = ZonedDateTime.now(user.getZoneId());
        LocalDate todayOnUserZone = LocalDate.of(now.getYear(), now.getMonth(), now.getDayOfMonth());
        long age = ChronoUnit.YEARS.between(birthday, todayOnUserZone);
        return age;
    }
}
