package com.example.ominitrixw.repository;

import com.example.ominitrixw.entities.Comment;
import com.example.ominitrixw.entities.CommentPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, CommentPK> {

    @Query(value = "Select * from Comments c where c.watchid = ?1", nativeQuery = true)
    List<Comment> getCommentByWatchId(String id);

    @Query(value = "Select * from Comments c where c.userid = ?1", nativeQuery = true)
    List<Comment> getCommentByUserId(String id);
}
