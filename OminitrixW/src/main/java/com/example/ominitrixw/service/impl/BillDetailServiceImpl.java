package com.example.ominitrixw.service.impl;

import com.example.ominitrixw.dto.AccountDTO;
import com.example.ominitrixw.dto.BillDetailDTO;
import com.example.ominitrixw.entities.Account;
import com.example.ominitrixw.entities.Bill;
import com.example.ominitrixw.entities.BillDetail;
import com.example.ominitrixw.repository.BillDetailRepository;
import com.example.ominitrixw.service.BillDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class BillDetailServiceImpl implements BillDetailService {
    @Autowired
    private BillDetailRepository billDetailRepository;
    @Autowired
    private ModelMapper modelMapper;

    private BillDetailDTO convertEntityToDTO(BillDetail billDetail) {
        BillDetailDTO billDetailDTO = new BillDetailDTO();
        billDetailDTO = modelMapper.map(billDetail, BillDetailDTO.class);
        return billDetailDTO;
    }
    private BillDetail convertDTOToEntity(BillDetailDTO billDetailDTO) {
        BillDetail billDetail = new BillDetail();
        billDetail = modelMapper.map(billDetailDTO, BillDetail.class);
        return billDetail;
    }

    @Override
    public BillDetailDTO addBillDetail(BillDetailDTO billDetailDTO) {
        BillDetail billDetail =
                billDetailRepository.getBillDetail(billDetailDTO.getBillID(),
                        billDetailDTO.getWatchID());
        if (billDetail == null) {
            billDetail = convertDTOToEntity(billDetailDTO);
            billDetailRepository.save(billDetail);
            return billDetailDTO;
        } else
            return null;
    }

    @Override
    public List<BillDetailDTO> findAllBillDetail() {
        return billDetailRepository.findAll().stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public List<BillDetailDTO> getBillDetailByBillId(String billId) {
        List<BillDetailDTO> billDetailDTOs =
                billDetailRepository.getBillDetailByBillID(billId).stream()
                        .map(this::convertEntityToDTO).collect(Collectors.toList());
        return billDetailDTOs;
    }

    @Override
    public List<BillDetailDTO> getBillDetailByMonth(int month) {
        List<BillDetailDTO> billDetailDTOs =
                billDetailRepository.getBillDetailByMonth(month).stream()
                        .map(this::convertEntityToDTO).collect(Collectors.toList());
        return billDetailDTOs;
    }

}
