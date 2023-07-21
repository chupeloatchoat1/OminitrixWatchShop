package com.example.ominitrixw.service.impl;

import com.example.ominitrixw.dto.BillDTO;
import com.example.ominitrixw.dto.CommentDTO;
import com.example.ominitrixw.entities.Bill;
import com.example.ominitrixw.entities.BillDetail;
import com.example.ominitrixw.entities.Comment;
import com.example.ominitrixw.repository.CommentRepository;
import com.example.ominitrixw.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ModelMapper modelMapper;
    private CommentDTO convertEntityToDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO = modelMapper.map(comment, CommentDTO.class);
        return commentDTO;
    }

    private Comment convertDTOToEntity(CommentDTO commentDTO){
        Comment comment = new Comment();
        comment = modelMapper.map(commentDTO, Comment.class);
        return comment;
    }
    @Override
    public CommentDTO addComment(CommentDTO commentDTO) {
        try{
            Comment comment = convertDTOToEntity(commentDTO);
            commentRepository.save(comment);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return commentDTO;
    }

    @Override
    public List<CommentDTO> getCommentByUserID(String userID) {
        return commentRepository.getCommentByUserId(userID).stream().map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> getCommentByWatchID(String roomID) {
        return commentRepository.getCommentByWatchId(roomID).stream().map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> getAllComment() {
        return commentRepository.findAll().stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }
}
