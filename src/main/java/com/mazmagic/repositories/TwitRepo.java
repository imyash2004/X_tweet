package com.mazmagic.repositories;

import com.mazmagic.model.Like;
import com.mazmagic.model.Twit;
import com.mazmagic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TwitRepo extends JpaRepository<Twit,Long> {
    public List<Twit>findAllByIsTwitTrueOrderByCreatedAtDesc();

    List<Twit> findByRetwitUserContainsOrUser_IdAndIsTwitTrueOrderByCreatedAtDesc(User user, Long userId);
    List<Twit> findByLikesContainingOrderByCreatedAtDesc(User user);

    @Query("select t from Twit t JOIN t.likes l where t.user.id=:userId")
    List<Twit>findByLikesUser_id(Long userId);
}
