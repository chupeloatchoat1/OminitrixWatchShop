package com.example.ominitrixw.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="Comments")
@IdClass(CommentPK.class)
public class Comment implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;
    @Id
    @ManyToOne
    @JoinColumn(name = "watchID")
    private Watch watch;

    @Id
    private Date date;
    private String comment;
}
