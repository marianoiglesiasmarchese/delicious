package com.delicious.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "user_stars_par")
@Builder @NoArgsConstructor @AllArgsConstructor @Getter @Setter @EqualsAndHashCode
public class UserStarsPar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude
    private Long id;

    @NotNull
    @OneToOne
    @JsonIgnore
    private RichUser user;

    @NotNull
    @Range(min = 1, max = 5)
    @EqualsAndHashCode.Exclude
    private Integer stars;

}
