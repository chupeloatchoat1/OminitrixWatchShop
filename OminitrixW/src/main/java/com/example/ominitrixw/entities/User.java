package com.example.ominitrixw.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "Users")
public class User implements Serializable {
    @Id
    private String userID;
    private String avatar;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Account account;

    @OneToMany(mappedBy = "user")
    private List<Comment> commentList;
    @OneToMany(mappedBy = "user")
    private List<LikeDetail> likeDetailList;
    private String OTP;
    private Timestamp OTPexpire;

    public User(String userID) {
        this.userID = userID;
    }
}
