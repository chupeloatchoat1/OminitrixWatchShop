package com.example.ominitrixw.service;

import com.example.ominitrixw.dto.BillDetailDTO;
import com.example.ominitrixw.entities.BillDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BillDetailService {
    public BillDetailDTO addBillDetail(BillDetailDTO billDetailDTO);
    public List<BillDetailDTO> findAllBillDetail();

    public List<BillDetailDTO> getBillDetailByBillId(String billId);

    public List<BillDetailDTO> getBillDetailByMonth(int month);
}
