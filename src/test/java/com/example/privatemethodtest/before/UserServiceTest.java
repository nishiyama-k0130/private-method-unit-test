package com.example.privatemethodtest.before;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    /**
     * @TODO Problem
     * Tried to write UnitTest, but difficult.
     * 1. What userId shold be passed?
     * 2. This test requires DB connection
     */
    @Test
    public void calculateAge() {
        long age = userService.calculateTimeZoneAge(1);
        assertTrue(age > 35);
    }
}
