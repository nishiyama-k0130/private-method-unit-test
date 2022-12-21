package com.example.privatemethodtest.after;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceImplTest {

    @Test
    public void calculateTimeZoneAge() {
        UserRepositoryInterface mockUserRepo = mock(UserRepositoryInterface.class);
        when(mockUserRepo.getUser(1)).thenReturn(new User(1, "2001-02-04", "America/Los_Angeles"));
        UserServiceImpl userService = new UserServiceImpl(mockUserRepo);// Inject Mock from constructor.
        long age = userService.calculateTimeZoneAge(1);
        assertTrue(age > 20);// 現在時刻に依存して、チェックが難しいので、実装日より将来かどうかだけをチェックする
    }

    /**
     * Exceptionのテスト
     */
    @Test
    public void calculateTimeZoneAgeUserNotExist() {
        UserServiceImpl userService = new UserServiceImpl(mock(UserRepositoryInterface.class));
        try {
            userService.calculateTimeZoneAge(2);
        } catch (RuntimeException e) {
            assertTrue(e.getMessage().startsWith("UserId does not exist"));
            return;
        }
        assertTrue(false, "Should not reach here");
    }

    /**
     * テスト内容がUserRepositoryに依存しないので、UserRepositoryのmockを渡す必要すらない
     */
    @Test
    public void convertLocalDate() throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        UserServiceImpl userService = new UserServiceImpl(null);
        Method method = userService.getClass().getDeclaredMethod("convertLocalDate", String.class);
        method.setAccessible(true);
        LocalDate result = (LocalDate) method.invoke(userService, "2021-01-31");
        assertEquals(result.getYear(), 2021);
        assertEquals(result.getMonthValue(), 1);
        assertEquals(result.getDayOfYear(), 31);
    }

    @Test
    public void generateNowLocalDateFromTimeZone() throws NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        UserServiceImpl userService = new UserServiceImpl(null);
        Method method = userService.getClass().getDeclaredMethod("generateNowLocalDateFromTimeZone", String.class);
        method.setAccessible(true);
        LocalDate result = (LocalDate) method.invoke(userService, "US/Pacific");// UTC-07:00
        ZonedDateTime tokyoNow = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));// UTC+09:00
        ZonedDateTime usPacificNow = tokyoNow.minusHours(16);// Diff from US and Tokyo
        assertEquals(result.getYear(), usPacificNow.getYear());
        assertEquals(result.getMonthValue(), usPacificNow.getMonthValue());
        assertEquals(result.getDayOfYear(), usPacificNow.getDayOfYear());
    }

    @Test
    public void calculateYearsBetween() throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        UserServiceImpl userService = new UserServiceImpl(null);
        Method method = userService.getClass().getDeclaredMethod("calculateYearsBetween", LocalDate.class,
                LocalDate.class);
        method.setAccessible(true);
        long result = (long) method.invoke(userService, LocalDate.of(2000, 1, 1), LocalDate.of(2000, 12, 31));
        assertEquals(result, 0);
        // Boarder Test 境界値テスト
        result = (long) method.invoke(userService, LocalDate.of(2010, 2, 1), LocalDate.of(2020, 1, 31));
        assertEquals(result, 9);
        result = (long) method.invoke(userService, LocalDate.of(2010, 2, 1), LocalDate.of(2020, 2, 1));
        assertEquals(result, 10);
        // Leap Year Test うるう年テスト
        result = (long) method.invoke(userService, LocalDate.of(2008, 2, 29), LocalDate.of(2018, 2, 28));
        assertEquals(result, 9);
        result = (long) method.invoke(userService, LocalDate.of(2008, 2, 29), LocalDate.of(2018, 3, 1));
        assertEquals(result, 10);
        result = (long) method.invoke(userService, LocalDate.of(2008, 2, 29), LocalDate.of(2020, 2, 29));
        assertEquals(result, 12);
    }
}
