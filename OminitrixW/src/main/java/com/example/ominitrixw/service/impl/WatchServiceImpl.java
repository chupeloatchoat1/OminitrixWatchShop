package com.example.ominitrixw.service.impl;

import com.example.ominitrixw.dto.BillDetailDTO;
import com.example.ominitrixw.dto.WatchDTO;
import com.example.ominitrixw.entities.Account;
import com.example.ominitrixw.entities.Bill;
import com.example.ominitrixw.entities.BillDetail;
import com.example.ominitrixw.entities.Watch;
import com.example.ominitrixw.repository.BillDetailRepository;
import com.example.ominitrixw.repository.BillRepository;
import com.example.ominitrixw.repository.WatchRepository;
import com.example.ominitrixw.service.WatchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class WatchServiceImpl implements WatchService {
    @Autowired
    private WatchRepository watchRepository;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private BillDetailRepository billDetailRepository;

    @Autowired
    private ModelMapper modelMapper;

    private WatchDTO convertEntityToDTO(Watch watch) {
        WatchDTO watchDTO = new WatchDTO();
        watchDTO = modelMapper.map(watch, WatchDTO.class);
        return watchDTO;
    }

    private Watch convertDTOToEntity(WatchDTO watchDTO) {
        Watch watch = new Watch();
        watch = modelMapper.map(watchDTO, Watch.class);
        return watch;
    }

    @Override
    public WatchDTO addWatch(WatchDTO watchDTO) {
//        Watch watch = watchRepository.findById(watchDTO.getWatchID()).orElse(null);
//        if (watch == null) {
//            watch = convertDTOToEntity(watchDTO);
//            watchRepository.save(watch);
//            return watchDTO;
//        }else {
//            return null;
//        }
        Watch watch = convertDTOToEntity(watchDTO);
        watchRepository.save(watch);
        return watchDTO;
    }

    @Override
    public List<WatchDTO> findAllWatch() {
        return watchRepository.findAll().stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public WatchDTO getWatchByID(String id) {
        Watch watch = watchRepository.findById(id).orElse(null);
        if (watch == null)
            return null;
        else {
            return convertEntityToDTO(watch);
        }
    }

    @Override
    public WatchDTO updateWatch(WatchDTO watchDTO) {
        Watch watch = watchRepository.findById(watchDTO.getWatchID()).orElse(null);
        if (watch != null) {
            watch = convertDTOToEntity(watchDTO);
            watchRepository.save(watch);
            return watchDTO;
        } else {
            return null;
        }
    }

//    @Override
//    public boolean deleteWatch(String id) {
//        if (watchRepository.existsById(id)) {
//            watchRepository.deleteById(id);
//            return true;
//        }
//        return false;
//    }

    @Override
    public List<WatchDTO> findWatchByTypeID(String id) {
        return watchRepository.getWatchByTypeId(id).stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public List<WatchDTO> getWatchByName(String name) {
        return watchRepository.getWatchByName(name).stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public void updateQuantity(String id, int buyQuantity) {
        watchRepository.updateQuantity(id, buyQuantity);
    }

    public boolean updateSTT(String id, boolean newSTT) {
        Watch watch = watchRepository.findById(id).orElse(null);
        if (Objects.nonNull(watch)) {
            watch.setStatus(newSTT);
            watchRepository.save(watch);
            return true;
        }
        return false;
    }

    @Override
    public List<Double> getRevenueWatchByMonth(int month) {
        List<Double> listValue = new ArrayList<>();
        double sumMale = 0; double sumFemale = 0;
        List<BillDetail> billDetailList = new ArrayList<>();
        Set<Watch> watchList = new HashSet<>();
        List<Bill> billList = billRepository.getBillListByMonth(month);
        for (Bill bill : billList) {
            billDetailList.addAll(billDetailRepository.getBillDetailByBillID(bill.getBillID()));
        }
        for (BillDetail billDetail : billDetailList ){
            watchList.add(convertDTOToEntity(getWatchByID(billDetail.getWatch().getWatchID())));

            if(getWatchByID(billDetail.getWatch().getWatchID()).isWatchGender()){
                sumMale += billDetail.getPrice() * billDetail.getQuantity();
            }
            else{
                sumFemale += billDetail.getPrice() * billDetail.getQuantity();
            }
        }

        listValue.add(sumMale);
        listValue.add(sumFemale);
        return listValue;
    }


}
