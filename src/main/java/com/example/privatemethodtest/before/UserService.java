package com.example.privatemethodtest.before;

import java.time.LocalDate;
import java.time.ZoneId;
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
     * Calculate the User's age on their living ZoneId.
     *
     * @param userId
     * @return
     */
    /**
     * @TODO Problem of this method
     * Not testable
     * Strongly depends on UserRepository implementation
     * Want to test only the behavior of LocalDate.parse, but cannot
     * Want to test only the behavior of ChronoUnit.YEARS.between, but cannot
     * Want to test the case when userId does not exist, but cannot
     */
    public long calculateTimeZoneAge(int userId){
        User user = userRepository.getUser(userId);
        if (ObjectUtils.isEmpty(user)){
            throw new RuntimeException(String.format("UserId does not exist, userId={}", userId));
        }
        LocalDate birthday = LocalDate.parse(user.birthday(), DTF);// Generate LocalDate from yyyy-MM-dd string.
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of(user.zoneId()));// Generate Zoned now on the user's Zone.
        LocalDate todayOnUserZone = LocalDate.of(now.getYear(), now.getMonth(), now.getDayOfMonth());// Generate LocalDate now by extracting date info.
        long age = ChronoUnit.YEARS.between(birthday, todayOnUserZone);// Calculate the years between 2 LocalDates.
        return age;
    }
}
