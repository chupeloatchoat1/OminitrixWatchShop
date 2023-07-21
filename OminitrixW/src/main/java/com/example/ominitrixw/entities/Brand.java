package com.example.ominitrixw.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "Brands")
public class Brand implements Serializable {
    @Id
    @GeneratedValue(generator = "auto_BrandID")
    @GenericGenerator(
            name = "auto_BrandID",
            parameters = {
                    @Parameter(name = "prefix", value = "B"),
                    @Parameter(name = "table",value = "Brand"),
                    @Parameter(name = "column",value = "brandID")
            },
            strategy ="com.example.ominitrixw.entities.IdGenerator"
    )
    private String brandID;
    private String brandName;
}
