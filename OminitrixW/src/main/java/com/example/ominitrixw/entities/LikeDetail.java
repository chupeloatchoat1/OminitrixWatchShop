package com.example.ominitrixw.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "LikeDetail")
@IdClass(LikeDetailPK.class)
public class LikeDetail implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;
    @Id
    @ManyToOne
    @JoinColumn(name = "watchID")
    private Watch watch;
}
