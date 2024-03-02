package com.mazmagic.repositories;

import com.mazmagic.model.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {


    public User findUserByEmail(String email);

    @Query("select distinct u from User u where u.fullName like %:query% or u.email like %:query%")
    public List<User> searchUser(@Param("query")String query);
}
