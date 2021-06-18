package com.example.rmptest;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void registerUser(UserEntity userEntity);

    @Query("SELECT * from users where email = :email and password = :password")
    UserEntity getUserByLogin(String email, String password);

    @Query("SELECT * FROM users WHERE email = :email AND name = :name")
    List<UserEntity> getUsersByEmail(String email, String name);

    @Query("SELECT * FROM users")
    List<UserEntity> getAll();

    @Query("SELECT * FROM users WHERE id = :userId")
    UserEntity getUserById(long userId);
}
