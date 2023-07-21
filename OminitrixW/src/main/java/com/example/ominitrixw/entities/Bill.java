package com.example.ominitrixw.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "Bills")
public class Bill implements Serializable {
    @Id
//    @GeneratedValue(generator = "auto_BillID")
//    @GenericGenerator(
//            name = "auto_BillID",
//            parameters = {
//                    @Parameter(name = "prefix", value = "B_"),
//                    @Parameter(name = "table",value = "Bill"),
//                    @Parameter(name = "column",value = "billID")
//            },
//            strategy ="com.example.ominitrixw.entities.IdGenerator"
//    )
    private String billID;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @OneToMany(mappedBy = "bill")
    private List<BillDetail> billDetailList;

}
