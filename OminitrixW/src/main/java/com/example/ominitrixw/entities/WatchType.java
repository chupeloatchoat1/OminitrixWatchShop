package com.example.ominitrixw.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "WatchTypes")
public class WatchType {
    @Id
    @GeneratedValue(generator = "auto_TypeID")
    @GenericGenerator(
            name = "auto_TypeID",
            parameters = {
                    @Parameter(name = "prefix", value = "T"),
                    @Parameter(name = "table",value = "WatchType"),
                    @Parameter(name = "column",value = "typeID")
            },
            strategy ="com.example.ominitrixw.entities.IdGenerator"
    )
    private String typeID;
    private String typeName;
    @OneToMany(mappedBy = "watchType")
    private List<Watch> watchList;
}
