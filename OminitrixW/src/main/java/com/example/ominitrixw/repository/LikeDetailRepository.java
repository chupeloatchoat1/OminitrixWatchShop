package com.example.ominitrixw.repository;


import com.example.ominitrixw.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LikeDetailRepository extends JpaRepository<LikeDetail, LikeDetailPK> {
    @Query(value = "SELECT COUNT(*) FROM like_detail where watchid =?1", nativeQuery = true)
    int getLikeDetailByWatchId(String id);
    @Modifying
    @Transactional
    @Query(value = "DELETE from like_detail where userid =?1 and watchid=?2", nativeQuery = true)
    void deleteLikeDetail(String userID, String watchID);
}