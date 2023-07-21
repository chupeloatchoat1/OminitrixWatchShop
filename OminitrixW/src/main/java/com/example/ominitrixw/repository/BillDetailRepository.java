package com.example.ominitrixw.repository;


import com.example.ominitrixw.entities.BillDetail;
import com.example.ominitrixw.entities.BillDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, BillDetailPK> {

    @Query(value = "Select * from [bill_detail] b where b.billid = ?1", nativeQuery = true)
    List<BillDetail> getBillDetailByBillID(String billId);

    @Query(value = "Select * from bill_detail b where b.billid = ?1 and b.watchid = ?2", nativeQuery = true)
    BillDetail getBillDetail(String billId, String roomID);


    @Query(value = "select bill_detail.billid, bill_detail.watchid, bill_detail.price, bill_detail.quantity from bill_detail join bills on bill_detail.billid = bills.billid where  MONTH(bills.date) = ?1", nativeQuery = true)
    List<BillDetail> getBillDetailByMonth(int month);
}
