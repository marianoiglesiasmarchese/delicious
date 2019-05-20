package com.delicious.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.net.URL;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
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
    private Set<UserStarsPar> userStarsPars;

    public Recipe(){}

    public Recipe(String name, String description, URL image, URL link) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.link = link;
        this.setUserStarsPars(new HashSet<>());
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public URL getImage() {
        return image;
    }

    public void setImage(URL image) {
        this.image = image;
    }

    public URL getLink() {
        return link;
    }

    public void setLink(URL link) {
        this.link = link;
    }

    public Long getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(Long numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    public Long getStartsSum() {
        return startsSum;
    }

    public void setStartsSum(Long startsSum) {
        this.startsSum = startsSum;
    }

    public Set<UserStarsPar> getUserStarsPars() {
        return userStarsPars;
    }

    public void setUserStarsPars(Set<UserStarsPar> userStarsPars) {
        this.userStarsPars = userStarsPars;
    }

    // TODO implement metods that makes hashset work, we want store only one time each Recipe [hashCode(), equals(), etc]

    public void increaseStars(Integer stars, RichUser user) {
        UserStarsPar userStarsPar = new UserStarsPar(user, stars);
        if(!getUserStarsPars().contains(userStarsPar)){
            numberOfVotes++;
            startsSum += stars;
            getUserStarsPars().add(userStarsPar);
        }
    }

    public void decreaseStars(RichUser user) {
        Optional<UserStarsPar> optionalUserStarsPar = getUserStarsPars().stream().filter(userStarsPar -> userStarsPar.getUser().equals(user)).findFirst();
        Boolean existScoreByUser = optionalUserStarsPar.isPresent();
        if(existScoreByUser){
            UserStarsPar userStarsPar =optionalUserStarsPar.get();
            Integer stars = userStarsPar.getStars();
            if(numberOfVotes != 0) {
                numberOfVotes--;
                startsSum -= stars;
            }
        }
    }


}
