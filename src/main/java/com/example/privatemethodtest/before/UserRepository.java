package com.example.privatemethodtest.before;

import java.time.ZoneId;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    public User getUser(int id){
        // mock. Please imagine to access to DB
        return new User(id, "1986-01-30", ZoneId.of("Asia/Tokyo"));
    }
}
