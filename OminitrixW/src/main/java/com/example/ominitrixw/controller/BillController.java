package com.example.ominitrixw.controller;

import com.example.ominitrixw.dto.BillDTO;
import com.example.ominitrixw.dto.ColorDTO;
import com.example.ominitrixw.entities.Bill;
import com.example.ominitrixw.service.impl.BillServiceImpl;
import com.example.ominitrixw.service.impl.ColorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/ominitrix/bill")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BillController {
    @Autowired
    private BillServiceImpl billService;

    @PostMapping(value = "/add")
    public BillDTO addBill(@RequestBody BillDTO billDTO){
        //BillDTO billDTO;
        try {
            billDTO = billService.addBill(billDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return billDTO;
    }

    @GetMapping("/{id}")
    public BillDTO findBillByID(@PathVariable String id){
        BillDTO billDTO = new BillDTO();
        try {
            billDTO = billService.findBillByID(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return billDTO;
    }
    @GetMapping(value = {""})
    public List<BillDTO> findAllBill(){
        List<BillDTO> billDTOList = new ArrayList<>();
        try {
            billDTOList = billService.findAllBill();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return billDTOList;
    }
    @GetMapping("/month-bill/{month}")
    public List<BillDTO> getBillByMonth(@PathVariable int month){
        List<BillDTO> billDTOList = new ArrayList<>();
        try {
            billDTOList = billService.getBillByMonth(month);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return billDTOList;
    }
    @GetMapping("/recent-bill")
    public List<BillDTO> getRecentOrder(){
        List<BillDTO> billDTOList = new ArrayList<>();
        try {
            billDTOList = billService.getRecentOrder();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return billDTOList;
    }
    @GetMapping("/getBillByUserID/{userID}")
    public List<BillDTO> getBillByUserID(@PathVariable String userID){
        List<BillDTO> billDTOList = new ArrayList<>();
        try {
            billDTOList = billService.getBillByUserID(userID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return billDTOList;
    }
}
