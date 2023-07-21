package com.example.ominitrixw.controller;

import com.example.ominitrixw.dto.CommentDTO;
import com.example.ominitrixw.dto.LikeDetailDTO;
import com.example.ominitrixw.entities.LikeDetail;
import com.example.ominitrixw.service.impl.CommentServiceImpl;
import com.example.ominitrixw.service.impl.LikeDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ominitrix/like")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LikeDetailController {
    @Autowired
    private LikeDetailServiceImpl likeDetailService;
    @PostMapping(value = "/add")
    public LikeDetailDTO addLikeDetail(@RequestBody LikeDetailDTO likeDetailDTO){
        try {
            likeDetailDTO = likeDetailService.addLikeDetail(likeDetailDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return likeDetailDTO;
    }


    @GetMapping(value = {"/{id}"})
    public int getLikeDetailByWatchId(@PathVariable String id){
        int like =0;
        try {
            like = likeDetailService.getLikeDetailByWatchId(id);

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return like;
    }
    @DeleteMapping(value="/delete")
    public void deleteLikeDetail(@Param("userID") String userID, @Param("watchID") String watchID){
         likeDetailService.deleteLikeDetail(userID, watchID);
    }
}
