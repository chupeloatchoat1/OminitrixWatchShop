package com.example.ominitrixw.repository;


import com.example.ominitrixw.dto.WatchDTO;
import com.example.ominitrixw.entities.Comment;
import com.example.ominitrixw.entities.Watch;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface WatchRepository extends JpaRepository<Watch, String> {
    @Query(value = "Select * from Watches w where w.watch_typeid=?1", nativeQuery = true)
    List<Watch> getWatchByTypeId(String id);

    @Query(value = "SELECT * FROM Watches w WHERE w.watch_name LIKE %?1%", nativeQuery = true)
    List<Watch> getWatchByName(String name);


    @Transactional
    @Modifying
    @Query(value = "UPDATE Watches set limit_Quantity=(limit_Quantity-:buyQuantity) where watchid=:id",nativeQuery = true)
    void updateQuantity(@PathVariable("id")String id, @Param(value = "buyQuantity") int buyQuantity);


}
