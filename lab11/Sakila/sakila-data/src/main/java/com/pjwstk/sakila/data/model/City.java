package com.pjwstk.sakila.data.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
public class City {
    private Long id;
    private String city;
    private Timestamp lastUpdate;
    private Collection<Address> addresses;
    private Country country;

    @Id
    @Column(name = "city_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

        City city1 = (City) o;

        if (id != null ? !id.equals(city1.id) : city1.id != null) return false;
        if (city != null ? !city.equals(city1.city) : city1.city != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(city1.lastUpdate) : city1.lastUpdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "city")
    public Collection<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Collection<Address> addresses) {
        this.addresses = addresses;
    }

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "country_id", nullable = false)
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
