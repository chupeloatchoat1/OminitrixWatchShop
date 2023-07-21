package com.example.ominitrixw.service;

import com.example.ominitrixw.dto.BillDTO;
import com.example.ominitrixw.entities.Bill;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface BillService {
    public BillDTO addBill(BillDTO billDTO);
    public BillDTO findBillByID(String id);
    public List<BillDTO> findAllBill();
    public List<BillDTO> getBillByMonth(int month);
    public List<BillDTO> getRecentOrder();
    List<BillDTO> getBillByDate(Date date);
    public List<BillDTO> getBillByUserID(String userID);
}
