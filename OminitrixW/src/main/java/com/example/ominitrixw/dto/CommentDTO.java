package com.example.ominitrixw.dto;

import com.example.ominitrixw.entities.User;
import com.example.ominitrixw.entities.Watch;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentDTO {
    private String userID;
    private String watchID;
    private Date date;
    private String comment;
}
