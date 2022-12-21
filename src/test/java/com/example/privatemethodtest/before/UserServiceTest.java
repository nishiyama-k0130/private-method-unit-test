package com.example.privatemethodtest.before;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void calculateAge(){
        long age = userService.calculateTimeZoneAge(1);
        assertTrue(age > 35);// 現在時刻に依存して、チェックが難しいので、実装日より将来かどうかだけをチェックする
    }
}
