package com.example.ominitrixw.controller;

import com.example.ominitrixw.dto.BillDetailDTO;
import com.example.ominitrixw.dto.WatchDTO;
import com.example.ominitrixw.entities.Watch;
import com.example.ominitrixw.service.impl.WatchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ominitrix/watch")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WatchController {
    @Autowired
    private WatchServiceImpl watchService;
    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));

    @PostMapping(value = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public WatchDTO addWatch(@RequestParam("watchName") String watchName,
                            @RequestParam("price") double price,
                            @RequestParam("waterResistance")boolean water,
                            @RequestParam("description")String description,
                            @RequestParam("thickness")double thickness,
                            @RequestParam("watchGender")boolean gender,
                            @RequestParam("voteLike")int like,
                            @RequestParam("limitQuantity")int limit,
                            @RequestParam("colorID")String colorID,
                            @RequestParam("typeID") String typeID,
                            @RequestParam("brandID")String brandID,
                            @RequestParam("fileName")List<MultipartFile> multipartFile) throws IOException {
        Path staticPath = Paths.get("static");
        Path imagePath = Paths.get("images/watch");
        List<String> images = new ArrayList<>();
        if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
            Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
        }
        multipartFile.forEach(item->{
            Path file = CURRENT_FOLDER.resolve(staticPath)
                    .resolve(imagePath).resolve(item.getOriginalFilename());
            try (OutputStream os = Files.newOutputStream(file)) {
                os.write(item.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            images.add(imagePath.resolve(item.getOriginalFilename()).toString());
        });
        try {
            WatchDTO watchDTO = new WatchDTO();
            watchDTO.setWatchName(watchName);
            watchDTO.setPrice(price);
            watchDTO.setWaterResistance(water);
            watchDTO.setDescription(description);
            watchDTO.setThickness(thickness);
            watchDTO.setWatchGender(gender);
            watchDTO.setVoteLike(like);
            watchDTO.setLimitQuantity(limit);
            watchDTO.setColorID(colorID);
            watchDTO.setTypeID(typeID);
            watchDTO.setBrandID(brandID);
            watchDTO.setImages(images);
            watchService.addWatch(watchDTO);
            return watchDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    @GetMapping(value = {""})
    public List<WatchDTO> findAllWatch(){
        List<WatchDTO> watchDTOList = new ArrayList<>();
        try {
            watchDTOList = watchService.findAllWatch();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return watchDTOList;
    }
    @GetMapping("/{id}")
    public WatchDTO findWatchByID(@PathVariable String id){
        WatchDTO watchDTO;
        try {
            watchDTO = watchService.getWatchByID(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return watchDTO;
    }

    @GetMapping("/findByName/{name}")
    public List<WatchDTO> findWatchByName(@PathVariable String name){
        List<WatchDTO> watchDTOList = new ArrayList<>();
        try {
            watchDTOList = watchService.getWatchByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return watchDTOList;
    }
    @PutMapping(value = "/update")
    public WatchDTO updateWatch(@RequestParam("watchName") String watchName,
                                @RequestParam("price") double price,
                                @RequestParam("waterResistance")boolean water,
                                @RequestParam("description")String description,
                                @RequestParam("thickness")double thickness,
                                @RequestParam("watchGender")boolean gender,
                                @RequestParam("voteLike")int like,
                                @RequestParam("limitQuantity")int limit,
                                @RequestParam("colorID")String colorID,
                                @RequestParam("typeID") String typeID,
                                @RequestParam("brandID")String brandID,
                                @RequestParam("watchID")String watchID,
                                @RequestParam("fileName")List<MultipartFile> multipartFile) throws IOException {
        Path staticPath = Paths.get("static");
        Path imagePath = Paths.get("images/watch");
        List<String> images = new ArrayList<>();
        if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
            Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
        }
        multipartFile.forEach(item->{
            Path file = CURRENT_FOLDER.resolve(staticPath)
                    .resolve(imagePath).resolve(item.getOriginalFilename());
            try (OutputStream os = Files.newOutputStream(file)) {
                os.write(item.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            images.add(imagePath.resolve(item.getOriginalFilename()).toString());
        });
        try {
            WatchDTO watchDTO = new WatchDTO();
            watchDTO.setWatchID(watchID);
            watchDTO.setWatchName(watchName);
            watchDTO.setPrice(price);
            watchDTO.setWaterResistance(water);
            watchDTO.setDescription(description);
            watchDTO.setThickness(thickness);
            watchDTO.setWatchGender(gender);
            watchDTO.setVoteLike(like);
            watchDTO.setLimitQuantity(limit);
            watchDTO.setColorID(colorID);
            watchDTO.setTypeID(typeID);
            watchDTO.setBrandID(brandID);
            watchDTO.setImages(images);
            watchService.updateWatch(watchDTO);
            return watchDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping(value = {"/updateSTT"})
    public boolean updateSTT(@Param("id") String id, @Param("newSTT")boolean newSTT){
        try {
            watchService.updateSTT(id, newSTT);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    @GetMapping(value = {"/find-by-type/{id}"})
    public List<WatchDTO> findWatchByTypeID(@PathVariable String id){
        List<WatchDTO> watchDTOList = new ArrayList<>();
        try {
            watchDTOList = watchService.findWatchByTypeID(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return watchDTOList;
    }
    @PutMapping(value = {"/updateQuantity/{id}"})
    public boolean updateQuantity(@PathVariable String id, @RequestParam(value = "buyQuantity")int buyQuantity){
        try {
            watchService.updateQuantity(id, buyQuantity);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    @GetMapping(value = {"/getWatchRevenueByMonth/{month}"})
    public List<Double> getRevenueWatchByMonth(@PathVariable int month){
        List<Double> z = watchService.getRevenueWatchByMonth(month);
        return z;
    }
}
