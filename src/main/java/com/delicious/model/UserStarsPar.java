package com.delicious.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "user_stars_par")
public class UserStarsPar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @OneToOne
    private RichUser user;

    @NotNull
    @Range(min = 1, max = 5)
    private Integer stars;

    public UserStarsPar(){}

    public UserStarsPar(RichUser user, Integer stars){
        this.user = user;
        this.stars = stars;
    }

    public RichUser getUser() {
        return user;
    }

    public void setUser(RichUser user) {
        this.user = user;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    // TODO implement metods that makes hashset work, we want store only one time each UserStarsPar [hashCode(), equals(), etc]

}
