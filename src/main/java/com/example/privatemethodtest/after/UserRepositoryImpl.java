package com.example.privatemethodtest.after;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepositoryInterface {

    public User getUser(int userId) {
        // mock. Please imagine to access to DB
        return new User(userId, "1986-01-30", "Asia/Tokyo");
    }
}
