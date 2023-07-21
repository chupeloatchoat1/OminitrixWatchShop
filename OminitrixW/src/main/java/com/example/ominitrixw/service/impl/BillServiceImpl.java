package com.example.ominitrixw.service.impl;

import com.example.ominitrixw.dto.BillDTO;
import com.example.ominitrixw.entities.Bill;
import com.example.ominitrixw.repository.BillRepository;
import com.example.ominitrixw.service.BillService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepository;
    @Autowired

    private ModelMapper modelMapper;

    private BillDTO convertEntityToDTO(Bill bill) {
        BillDTO billDTO = new BillDTO();
        billDTO = modelMapper.map(bill, BillDTO.class);
        return billDTO;
    }
    private Bill convertDTOToEntity(BillDTO billDTO){
        Bill bill = new Bill();
        bill = modelMapper.map(billDTO, Bill.class);
        return bill;
    }

    @Override
    public BillDTO addBill(BillDTO billDTO) {
//        Bill bill = billRepository.findById(billDTO.getBillID()).orElse(null);
//        if (bill == null) {
//            bill = convertDTOToEntity(billDTO);
//            billRepository.save(bill);
//            return billDTO;
//        } else
//            return null;
        Bill bill = convertDTOToEntity(billDTO);
        billRepository.save(bill);
        return billDTO;
    }
    @Override
    public BillDTO findBillByID(String id) {
        Bill bill = billRepository.findById(id).orElse(null);
        if (bill == null)
            return null;
        else
            return convertEntityToDTO(bill);
    }
    @Override
    public List<BillDTO> findAllBill() {
        return billRepository.findAll().stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }
    @Override
    public List<BillDTO> getBillByMonth(int month) {
        return billRepository.getBillListByMonth(month).stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }
    @Override
    public List<BillDTO> getRecentOrder() {
        return billRepository.getRecentOrder().stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public List<BillDTO> getBillByDate(Date date) {
        return null;
    }
    @Override
    public List<BillDTO> getBillByUserID(String userID) {
        return billRepository.getBillByUserID(userID).stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }
}
