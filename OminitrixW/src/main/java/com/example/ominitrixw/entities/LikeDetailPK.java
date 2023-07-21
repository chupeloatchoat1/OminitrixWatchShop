package com.example.ominitrixw.entities;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class LikeDetailPK implements Serializable {
    private String user;
    private String watch;
}
