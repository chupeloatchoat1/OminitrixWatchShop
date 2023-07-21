package com.example.ominitrixw.controller;

import com.example.ominitrixw.dto.BillDetailDTO;
import com.example.ominitrixw.service.impl.BillDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ominitrix/bill-detail")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BillDetailController {
    @Autowired
    private BillDetailServiceImpl billDetailService;

    @PostMapping(value = "/add")
    public BillDetailDTO addBillDetail(@RequestBody BillDetailDTO billDetailDTO){
        try {
            billDetailDTO = billDetailService.addBillDetail(billDetailDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return billDetailDTO;
    }
    @GetMapping("")
    public List<BillDetailDTO> findAllBillDetail(){
        List<BillDetailDTO> billDetailDTOList = new ArrayList<>();
        try {
            billDetailDTOList = billDetailService.findAllBillDetail();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return billDetailDTOList;
    }
    @GetMapping("/{id}")
    public List<BillDetailDTO> getBillDetailByBillId(@PathVariable String id){
        List<BillDetailDTO> billDetailDTOList = new ArrayList<>();
        try {
            billDetailDTOList = billDetailService.getBillDetailByBillId(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return billDetailDTOList;
    }
    @GetMapping("/getBillByMonth")
    public List<Double> getTotalByMonth(){
        List<Double> l = new ArrayList<>();
        double sum1 = 0; double sum2=0;; double sum3=0; double sum4= 0;double sum5=0; double sum6=0; double sum7=0;
        double sum8=0; double sum9=0;double sum10=0; double sum11=0; double sum12=0;
        List<BillDetailDTO> billDetailDTOList1 = new ArrayList<>();
        List<BillDetailDTO> billDetailDTOList2 = new ArrayList<>();
        List<BillDetailDTO> billDetailDTOList3 = new ArrayList<>();
        List<BillDetailDTO> billDetailDTOList4 = new ArrayList<>();
        List<BillDetailDTO> billDetailDTOList5 = new ArrayList<>();
        List<BillDetailDTO> billDetailDTOList6 = new ArrayList<>();
        List<BillDetailDTO> billDetailDTOList7 = new ArrayList<>();
        List<BillDetailDTO> billDetailDTOList8 = new ArrayList<>();
        List<BillDetailDTO> billDetailDTOList9 = new ArrayList<>();
        List<BillDetailDTO> billDetailDTOList10 = new ArrayList<>();
        List<BillDetailDTO> billDetailDTOList11 = new ArrayList<>();
        List<BillDetailDTO> billDetailDTOList12 = new ArrayList<>();

        billDetailDTOList1 = billDetailService.getBillDetailByMonth(1);
        for (BillDetailDTO billDetailDTO :
        billDetailDTOList1        ) {
            sum1 += billDetailDTO.getPrice() * billDetailDTO.getQuantity();
        }
        l.add(sum1);
        //======
        billDetailDTOList2 = billDetailService.getBillDetailByMonth(2);
        for (BillDetailDTO billDetailDTO :
                billDetailDTOList2        ) {
            sum2 += billDetailDTO.getPrice() * billDetailDTO.getQuantity();
        }
        l.add(sum2);
        //======
        billDetailDTOList3 = billDetailService.getBillDetailByMonth(3);
        for (BillDetailDTO billDetailDTO :
                billDetailDTOList3        ) {
            sum3 += billDetailDTO.getPrice() * billDetailDTO.getQuantity();
        }
        l.add(sum3);
        //======
        billDetailDTOList4 = billDetailService.getBillDetailByMonth(4);
        for (BillDetailDTO billDetailDTO :
                billDetailDTOList4        ) {
            sum4 += billDetailDTO.getPrice() * billDetailDTO.getQuantity();
        }
        l.add(sum4);
        //======
        billDetailDTOList5 = billDetailService.getBillDetailByMonth(5);
        for (BillDetailDTO billDetailDTO :
                billDetailDTOList5        ) {
            sum5 += billDetailDTO.getPrice() * billDetailDTO.getQuantity();
        }
        l.add(sum5);
        //======
        billDetailDTOList6 = billDetailService.getBillDetailByMonth(6);
        for (BillDetailDTO billDetailDTO :
                billDetailDTOList6        ) {
            sum6 += billDetailDTO.getPrice() * billDetailDTO.getQuantity();
        }
        l.add(sum6);
        //======
        billDetailDTOList7 = billDetailService.getBillDetailByMonth(7);
        for (BillDetailDTO billDetailDTO :
                billDetailDTOList7        ) {
            sum7 += billDetailDTO.getPrice() * billDetailDTO.getQuantity();
        }
        l.add(sum7);
        //======
        billDetailDTOList8 = billDetailService.getBillDetailByMonth(8);
        for (BillDetailDTO billDetailDTO :
                billDetailDTOList8        ) {
            sum8 += billDetailDTO.getPrice() * billDetailDTO.getQuantity();
        }
        l.add(sum8);
        //======
        billDetailDTOList9 = billDetailService.getBillDetailByMonth(9);
        for (BillDetailDTO billDetailDTO :
                billDetailDTOList9        ) {
            sum9 += billDetailDTO.getPrice() * billDetailDTO.getQuantity();
        }
        l.add(sum9);
        //======
        billDetailDTOList10 = billDetailService.getBillDetailByMonth(10);
        for (BillDetailDTO billDetailDTO :
                billDetailDTOList10        ) {
            sum10 += billDetailDTO.getPrice() * billDetailDTO.getQuantity();
        }
        l.add(sum10);
        //======
        billDetailDTOList11 = billDetailService.getBillDetailByMonth(11);
        for (BillDetailDTO billDetailDTO :
                billDetailDTOList11        ) {
            sum11 += billDetailDTO.getPrice() * billDetailDTO.getQuantity();
        }
        l.add(sum11);
        //======
        billDetailDTOList12 = billDetailService.getBillDetailByMonth(12);
        for (BillDetailDTO billDetailDTO :
                billDetailDTOList12        ) {
            sum12 += billDetailDTO.getPrice() * billDetailDTO.getQuantity();
        }
        l.add(sum12);


        return l;
    }

}
