package com.example.ominitrixw.repository;


import com.example.ominitrixw.entities.Bill;
import com.example.ominitrixw.entities.BillDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, String> {
    @Query(value = "select * from bills b where MONTH(b.date) = ?1",nativeQuery = true)
    List<Bill> getBillListByMonth(int month);

    @Query(value = "select top 10 * from bills b order By b.date desc",nativeQuery = true)
    List<Bill> getRecentOrder();

    @Query(value = "SELECT * FROM bills WHERE date = ?1",nativeQuery = true)
    List<Bill> getBillByDate(Date date);

    @Query(value = "SELECT * FROM bills WHERE userid = ?1",nativeQuery = true)
    List<Bill> getBillByUserID(String userID);

}
