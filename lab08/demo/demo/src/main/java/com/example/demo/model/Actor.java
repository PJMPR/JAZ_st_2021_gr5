package com.example.demo.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int actorId;
    private String firstName;
    private String lastName;
    private Timestamp lastUpdate;
    private Collection<FilmActor> filmActors;

    public Actor(String firstName,String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Actor() {

    }

    @Id
    @Column(name = "actor_id")
    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "last_update")
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Actor actor = (Actor) o;

        if (actorId != actor.actorId) return false;
        if (!Objects.equals(firstName, actor.firstName)) return false;
        if (!Objects.equals(lastName, actor.lastName)) return false;
        return Objects.equals(lastUpdate, actor.lastUpdate);
    }

    @Override
    public int hashCode() {
        int result = actorId;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "actor")
    public Collection<FilmActor> getFilmActors() {
        return filmActors;
    }

    public void setFilmActors(Collection<FilmActor> filmActors) {
        this.filmActors = filmActors;
    }
}