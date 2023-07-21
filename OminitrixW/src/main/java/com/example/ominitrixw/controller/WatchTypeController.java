package com.example.ominitrixw.controller;

import com.example.ominitrixw.dto.BrandDTO;
import com.example.ominitrixw.dto.WatchTypeDTO;
import com.example.ominitrixw.entities.Brand;
import com.example.ominitrixw.entities.Watch;
import com.example.ominitrixw.entities.WatchType;
import com.example.ominitrixw.service.impl.BrandServiceImpl;
import com.example.ominitrixw.service.impl.WatchTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ominitrix/type")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WatchTypeController {
    @Autowired
    private WatchTypeServiceImpl watchTypeService;


    @PostMapping(value = "/add")
    public WatchTypeDTO addWatchType(@RequestBody WatchType newWatchType){
        WatchTypeDTO watchTypeDTO;
        try {
            watchTypeDTO = watchTypeService.addWatchType(newWatchType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return watchTypeDTO;
    }
    @GetMapping("/{id}")
    public WatchTypeDTO findWatchTypeByID(@PathVariable String id){
        WatchTypeDTO watchTypeDTO;
        try {
            watchTypeDTO = watchTypeService.findWatchTypeByID(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return watchTypeDTO;
    }
    @GetMapping(value = {""})
    public List<WatchTypeDTO> findAllWatchType(){
        List<WatchTypeDTO> typeDTOList = new ArrayList<>();
        try {
            typeDTOList = watchTypeService.findAllWatchType();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return typeDTOList;
    }
    @PutMapping(value = "/update")
    public boolean updateWatchType(@RequestBody WatchType watchType){
        try {
            watchTypeService.updateWatchType(watchType);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    @DeleteMapping("/{id}")
    public boolean deleteWatchType(@PathVariable String id){
        try {
            watchTypeService.deleteWatchType(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
