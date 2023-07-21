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
@Table(name = "Watches")
public class Watch {
    @Id
    @GeneratedValue(generator = "auto_WatchID")
    @GenericGenerator(
            name = "auto_WatchID",
            parameters = {
                    @Parameter(name = "prefix", value = "W"),
                    @Parameter(name = "table",value = "Watch"),
                    @Parameter(name = "column",value = "watchID")
            },
            strategy ="com.example.ominitrixw.entities.IdGenerator"
    )
    private String watchID;
    private String watchName;
    private double price;
    private boolean waterResistance;
    private String description;
    private double thickness;
    private boolean watchGender;
    private int voteLike;
    private int limitQuantity;
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "colorID")
    private Color color;
    @ManyToOne
    @JoinColumn(name = "watchTypeID")
    private WatchType watchType;
    @ManyToOne
    @JoinColumn(name = "brandID")
    private Brand brand;
    @OneToMany(mappedBy = "watch")
    private List<Comment> commentList;
    @OneToMany(mappedBy = "watch")
    private List<LikeDetail> likeDetailList;
    @ElementCollection
    @CollectionTable(name = "images", joinColumns = @JoinColumn(name = "watchID"))
    @Column(name = "imageURL", nullable = false)
    private List<String> images;
    @OneToMany(mappedBy = "watch")
    private List<BillDetail> billDetailList;
}
