package com.example.demo.data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;

@Entity
public class Staff {
    private int staffId;
    private String firstName;
    private String lastName;
    private byte[] picture;
    private String email;
    private boolean active;
    private String username;
    private String password;
    private Timestamp lastUpdate;
    private Collection<Payment> paymentsByStaffId;
    private Collection<Rental> rentalsByStaffId;
    private Address addressByAddressId;
    private Store storeByStoreId;
    private Collection<Store> storesByStaffId;

    @Id
    @Column(name = "staff_id")
    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
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
    @Column(name = "picture")
    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "active")
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

        Staff staff = (Staff) o;

        if (staffId != staff.staffId) return false;
        if (active != staff.active) return false;
        if (firstName != null ? !firstName.equals(staff.firstName) : staff.firstName != null) return false;
        if (lastName != null ? !lastName.equals(staff.lastName) : staff.lastName != null) return false;
        if (!Arrays.equals(picture, staff.picture)) return false;
        if (email != null ? !email.equals(staff.email) : staff.email != null) return false;
        if (username != null ? !username.equals(staff.username) : staff.username != null) return false;
        if (password != null ? !password.equals(staff.password) : staff.password != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(staff.lastUpdate) : staff.lastUpdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = staffId;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(picture);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "staffByStaffId")
    public Collection<Payment> getPaymentsByStaffId() {
        return paymentsByStaffId;
    }

    public void setPaymentsByStaffId(Collection<Payment> paymentsByStaffId) {
        this.paymentsByStaffId = paymentsByStaffId;
    }

    @OneToMany(mappedBy = "staffByStaffId")
    public Collection<Rental> getRentalsByStaffId() {
        return rentalsByStaffId;
    }

    public void setRentalsByStaffId(Collection<Rental> rentalsByStaffId) {
        this.rentalsByStaffId = rentalsByStaffId;
    }

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id", nullable = false)
    public Address getAddressByAddressId() {
        return addressByAddressId;
    }

    public void setAddressByAddressId(Address addressByAddressId) {
        this.addressByAddressId = addressByAddressId;
    }

    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "store_id", nullable = false)
    public Store getStoreByStoreId() {
        return storeByStoreId;
    }

    public void setStoreByStoreId(Store storeByStoreId) {
        this.storeByStoreId = storeByStoreId;
    }

    @OneToMany(mappedBy = "staffByManagerStaffId")
    public Collection<Store> getStoresByStaffId() {
        return storesByStaffId;
    }

    public void setStoresByStaffId(Collection<Store> storesByStaffId) {
        this.storesByStaffId = storesByStaffId;
    }
}
