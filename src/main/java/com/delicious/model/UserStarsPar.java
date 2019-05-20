package com.delicious.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

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

    @Override
    public int hashCode(){
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(Arrays.asList(getUser().getName(), getUser().getLastName(), getUser().getEmail(), getUser().getImage(), getStars().toString()));
        return builder.toHashCode();
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof UserStarsPar)) {
            return false;
        }
        UserStarsPar userStarsPar  = (UserStarsPar) o;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(getUser().getName(), userStarsPar.getUser().getName());
        builder.append(getUser().getLastName(), userStarsPar.getUser().getLastName());
        builder.append(getUser().getEmail(), userStarsPar.getUser().getEmail());
        builder.append(getUser().getImage(), userStarsPar.getUser().getImage());
        builder.append(getStars(), userStarsPar.getStars());
        return builder.isEquals();
    }

}
