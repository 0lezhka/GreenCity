package greencity.entity;

import greencity.entity.enums.PlaceStatus;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import lombok.*;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(
    exclude = {"comments", "photos", "location", "favoritePlaces", "category", "rates", "webPages", "status"})
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "place")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "place")
    private List<Photo> photos = new ArrayList<>();

    @OneToMany(mappedBy = "place")
    private List<SpecificationValue> specificationValues = new ArrayList<>();

    @OneToOne(mappedBy = "place")
    private Location location;

    @OneToMany(mappedBy = "place")
    private List<FavoritePlace> favoritePlaces = new ArrayList<>();

    @ManyToMany(mappedBy = "places")
    private List<WebPage> webPages = new ArrayList<>();

    @ManyToOne private Category category;

    @OneToMany(mappedBy = "place")
    private List<Rate> rates = new ArrayList<>();

    @OneToMany(mappedBy = "place")
    private List<OpeningHours> openingHoursList = new ArrayList<>();

    @ManyToOne private User author;

    @Column(name = "modified_date")
    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm")
    private LocalDateTime modifiedDate = LocalDateTime.now();

    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "status")
    private PlaceStatus status = PlaceStatus.PROPOSED;
}
