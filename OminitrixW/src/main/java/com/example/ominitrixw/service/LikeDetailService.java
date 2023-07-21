package com.example.ominitrixw.service;

import com.example.ominitrixw.dto.AccountDTO;
import com.example.ominitrixw.dto.LikeDetailDTO;
import com.example.ominitrixw.entities.Account;
import com.example.ominitrixw.entities.LikeDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LikeDetailService {
    public LikeDetailDTO addLikeDetail(LikeDetailDTO likeDetailDTO);
    public int getLikeDetailByWatchId(String id);
    public void deleteLikeDetail(String userID, String watchID);
}
