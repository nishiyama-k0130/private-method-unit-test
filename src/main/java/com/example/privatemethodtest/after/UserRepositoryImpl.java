package com.example.privatemethodtest.after;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepositoryInterface{

    public User getUser(int userId){
        // mock
        return new User(userId, "1986-01-30", "Asia/Tokyo");
    }
}
