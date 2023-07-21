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
public class BillDetailPK implements Serializable {
    private String watch;
    private String bill;
}
