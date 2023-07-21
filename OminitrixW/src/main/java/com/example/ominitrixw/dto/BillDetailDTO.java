package com.example.ominitrixw.dto;

import com.example.ominitrixw.entities.Bill;
import com.example.ominitrixw.entities.Watch;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BillDetailDTO {
    private int quantity;
    private double price;
    private String billID;
    private String watchID;
}
