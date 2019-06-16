package com.delicious.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Builder @NoArgsConstructor @AllArgsConstructor @Getter @Setter @EqualsAndHashCode
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    private String description;

    @NotNull
    private URL image;

//    @ElementCollection
    private URL link;

    @NotNull
    @Column(name = "number_of_votes")
    private Long numberOfVotes = 0L;

    @NotNull
    @Column(name = "starts_sum")
    private Long startsSum = 0L;

    @OneToMany(cascade = CascadeType.ALL)
    @Column(name = "user_stars_pars")
    @Builder.Default
    private Set<UserStarsPar> userStarsPars = new LinkedHashSet<>();

    public double getAvgStarts(){
        double result = 0;
        if(this.startsSum.compareTo(0L) > 0){
            result = this.startsSum.doubleValue() / this.numberOfVotes;
        }
        return result;
    }

    public Double addStarts(Long numberOfStarts){
        this.numberOfVotes++;
        this.startsSum+=numberOfStarts;
        return this.startsSum.doubleValue() / this.numberOfVotes;
    }

    public Recipe increaseStars(Integer stars, RichUser user) {
        UserStarsPar userStarsPar = UserStarsPar.builder().user(user).stars(stars).build();
        if(!getUserStarsPars().contains(userStarsPar)){
            numberOfVotes++;
            startsSum += stars;
            getUserStarsPars().add(userStarsPar);
        }
        return this;
    }

    public Recipe decreaseStars(RichUser user) {
        Optional<UserStarsPar> optionalUserStarsPar = getUserStarsPars().stream().filter(userStarsPar -> userStarsPar.getUser().equals(user)).findFirst();
        Boolean existScoreByUser = optionalUserStarsPar.isPresent();
        if(existScoreByUser){
            UserStarsPar userStarsPar = optionalUserStarsPar.get();
            Integer stars = userStarsPar.getStars();
            if(numberOfVotes != 0) {
                numberOfVotes--;
                startsSum -= stars;
                getUserStarsPars().remove(userStarsPar);
            }
        }
        return this;
    }

}
