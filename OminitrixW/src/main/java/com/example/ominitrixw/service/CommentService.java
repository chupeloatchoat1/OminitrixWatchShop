package com.example.ominitrixw.service;

import com.example.ominitrixw.dto.CommentDTO;
import com.example.ominitrixw.entities.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    public CommentDTO addComment(CommentDTO commentDTO);
    public List<CommentDTO> getCommentByUserID(String userID);
    public List<CommentDTO> getCommentByWatchID(String roomID);
    public List<CommentDTO> getAllComment();
}
