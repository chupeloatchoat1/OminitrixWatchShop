package com.example.ominitrixw.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.io.Serializable;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "Colors")
public class Color implements Serializable {
    @Id
    @GeneratedValue(generator = "auto_ColorID")
    @GenericGenerator(
            name = "auto_ColorID",
            parameters = {
                    @Parameter(name = "prefix", value = "C"),
                    @Parameter(name = "table",value = "Color"),
                    @Parameter(name = "column",value = "colorID")
            },
            strategy ="com.example.ominitrixw.entities.IdGenerator"
    )
    private String colorID;
    private String colorName;
    @OneToMany(mappedBy = "color")
    private List<Watch> watchList;
}
