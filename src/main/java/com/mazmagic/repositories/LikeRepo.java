package com.mazmagic.repositories;

import com.mazmagic.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LikeRepo extends JpaRepository<Like,Long> {
    @Query("select l from Like l where l.user.id=:userId and l.twit.id=:twitId")
    public Like isLikeExist(@Param("userId") long userId,@Param("twitId")Long twitId);

    @Query("select l from Like l where l.twit.id=:twitId")
    public List<Like> findByTwit(@Param("twitId") Long twitId);

}
