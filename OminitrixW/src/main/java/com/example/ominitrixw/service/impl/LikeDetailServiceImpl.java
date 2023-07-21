package com.example.ominitrixw.service.impl;

import com.example.ominitrixw.dto.LikeDetailDTO;
import com.example.ominitrixw.entities.Comment;
import com.example.ominitrixw.entities.LikeDetail;
import com.example.ominitrixw.repository.LikeDetailRepository;
import com.example.ominitrixw.service.LikeDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LikeDetailServiceImpl implements LikeDetailService {
    @Autowired
    private LikeDetailRepository likeDetailRepository;
    @Autowired
    private ModelMapper modelMapper;

    private LikeDetailDTO convertEntityToDTO(LikeDetail likeDetail) {
        LikeDetailDTO detailDTO = new LikeDetailDTO();
        detailDTO = modelMapper.map(likeDetail, LikeDetailDTO.class);
        return detailDTO;
    }
    private LikeDetail convertDTOToEntity(LikeDetailDTO likeDetailDTO) {
        LikeDetail likeDetail = new LikeDetail();
        likeDetail = modelMapper.map(likeDetailDTO, LikeDetail.class);
        return likeDetail;
    }

    @Override
    public LikeDetailDTO addLikeDetail(LikeDetailDTO likeDetailDTO) {
        try{
            LikeDetail likeDetail = convertDTOToEntity(likeDetailDTO);
            likeDetailRepository.save(likeDetail);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return likeDetailDTO;
    }

    @Override
    public int getLikeDetailByWatchId(String id) {
        return likeDetailRepository.getLikeDetailByWatchId(id);
    }

    @Override
    public void deleteLikeDetail(String userID, String watchID) {
         likeDetailRepository.deleteLikeDetail(userID, watchID);
    }
}
