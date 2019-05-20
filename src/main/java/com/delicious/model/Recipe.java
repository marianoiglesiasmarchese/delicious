package com.delicious.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedHashSet;
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
    private Set<UserStarsPar> userStarsPars = new LinkedHashSet<>();

    public Recipe(){}

    public Recipe(String name, String description, URL image, URL link) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.link = link;
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

    @Override
    public int hashCode(){
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(Arrays.asList(getName(), getDescription(), getImage().toString()));
        return builder.toHashCode();
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Recipe)) {
            return false;
        }
        Recipe recipe  = (Recipe) o;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(getName(), recipe.getName());
        builder.append(getDescription(), recipe.getDescription());
        builder.append(getImage(), recipe.getImage());
        return builder.isEquals();
    }

    public Recipe increaseStars(Integer stars, RichUser user) {
        UserStarsPar userStarsPar = new UserStarsPar(user, stars);
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
