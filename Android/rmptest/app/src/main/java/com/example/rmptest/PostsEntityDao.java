package com.example.rmptest;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PostsEntityDao {

    @Insert
    void add(PostsEntity postsEntity);

    @Update
    void update(PostsEntity postsEntity);

    @Delete
    void delete(PostsEntity postsEntity);

    @Query("SELECT * FROM posts")
    List<PostsEntity> getAll();

    @Query("SELECT * FROM posts WHERE id = :id")
    PostsEntity getById(long id);

    @Query("SELECT * FROM posts Where userId = :id")
    List<PostsEntity> getByUser(long id);




}
