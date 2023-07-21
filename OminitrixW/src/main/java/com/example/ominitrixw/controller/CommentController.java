package com.example.ominitrixw.controller;

import com.example.ominitrixw.dto.CommentDTO;
import com.example.ominitrixw.entities.Comment;
import com.example.ominitrixw.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ominitrix/comment")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommentController {
    @Autowired
    private CommentServiceImpl commentService;
    @PostMapping(value = "/add")
    public CommentDTO addComment(@RequestBody CommentDTO commentDTO){
        try {
            commentDTO = commentService.addComment(commentDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return commentDTO;
    }
    @GetMapping(value = {"/user-comment/{id}"})
    public List<CommentDTO> getCommentByUserID(@PathVariable String id){
        List<CommentDTO> commentDTOList = new ArrayList<>();
        try {
            commentDTOList = commentService.getCommentByUserID(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return commentDTOList;
    }
    @GetMapping(value = {"/watch-comment/{id}"})
    public List<CommentDTO> getCommentByWatchID(@PathVariable String id){
        List<CommentDTO> commentDTOList = new ArrayList<>();
        try {
            commentDTOList = commentService.getCommentByWatchID(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return commentDTOList;
    }
    @GetMapping(value = {""})
    public List<CommentDTO> getAllComment(){
        List<CommentDTO> commentDTOList = new ArrayList<>();
        try {
            commentDTOList = commentService.getAllComment();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return commentDTOList;
    }
}
