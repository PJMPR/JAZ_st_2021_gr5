package com.example.demo.data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
public class Address {
    private int addressId;
    private String address;
    private String address2;
    private String district;
    private String postalCode;
    private String phone;
    private Timestamp lastUpdate;
    private City city;
    private Collection<Customer> customers;
    private Collection<Staff> staff;
    private Collection<Store> stores;

    @Id
    @Column(name = "address_id")
    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "address2")
    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @Basic
    @Column(name = "district")
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Basic
    @Column(name = "postal_code")
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

        Address address1 = (Address) o;

        if (addressId != address1.addressId) return false;
        if (address != null ? !address.equals(address1.address) : address1.address != null) return false;
        if (address2 != null ? !address2.equals(address1.address2) : address1.address2 != null) return false;
        if (district != null ? !district.equals(address1.district) : address1.district != null) return false;
        if (postalCode != null ? !postalCode.equals(address1.postalCode) : address1.postalCode != null) return false;
        if (phone != null ? !phone.equals(address1.phone) : address1.phone != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(address1.lastUpdate) : address1.lastUpdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = addressId;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (address2 != null ? address2.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "city_id", nullable = false)
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @OneToMany(mappedBy = "address")
    public Collection<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Collection<Customer> customers) {
        this.customers = customers;
    }

    @OneToMany(mappedBy = "addressByAddressId")
    public Collection<Staff> getStaff() {
        return staff;
    }

    public void setStaff(Collection<Staff> staff) {
        this.staff = staff;
    }

    @OneToMany(mappedBy = "addressByAddressId")
    public Collection<Store> getStores() {
        return stores;
    }

    public void setStores(Collection<Store> stores) {
        this.stores = stores;
    }
}
