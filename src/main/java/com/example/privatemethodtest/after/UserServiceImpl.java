package com.example.privatemethodtest.after;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class UserServiceImpl implements UserServiceInterface {

    private UserRepositoryInterface userRepository;

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    public UserServiceImpl(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Calculate the User's age on their living ZoneId.
     *
     * @param userId
     * @return
     */
    public long calculateTimeZoneAge(int userId) {
        User user = userRepository.getUser(userId);
        if (ObjectUtils.isEmpty(user)) {
            throw new RuntimeException(String.format("UserId does not exist, userId=%s", userId));
        }
        LocalDate birthday = convertLocalDate(user.getBirthday());
        LocalDate todayOnUserZone = generateNowLocalDateFromTimeZone(user.getZoneId());
        long age = this.calculateYearsBetween(birthday, todayOnUserZone);
        return age;
    }

    /**
     *
     * @param dateStr yyyy-MM-dd
     * @return
     */
    private LocalDate convertLocalDate(String dateStr) {
        return LocalDate.parse(dateStr, DTF);
    }

    private LocalDate generateNowLocalDateFromTimeZone(String zoneId) {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of(zoneId));
        LocalDate todayFromZoneId = LocalDate.of(now.getYear(), now.getMonth(), now.getDayOfMonth());
        return todayFromZoneId;
    }

    private long calculateYearsBetween(LocalDate from, LocalDate to) {
        return ChronoUnit.YEARS.between(from, to);
    }
}
