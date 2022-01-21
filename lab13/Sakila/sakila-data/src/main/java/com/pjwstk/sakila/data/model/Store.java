package com.pjwstk.sakila.data.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
public class Store {
    private Long storeId;
    private Timestamp lastUpdate;
    private Collection<Customer> customers;
    private Collection<Inventory> inventories;
    private Collection<Staff> staff;
    private Staff staffByManager;
    private Address address;

    @Id
    @Column(name = "store_id")
    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
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

        Store store = (Store) o;

        if (storeId != null ? !storeId.equals(store.storeId) : store.storeId != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(store.lastUpdate) : store.lastUpdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = storeId != null ? storeId.hashCode() : 0;
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "store")
    public Collection<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Collection<Customer> customers) {
        this.customers = customers;
    }

    @OneToMany(mappedBy = "store")
    public Collection<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(Collection<Inventory> inventories) {
        this.inventories = inventories;
    }

    @OneToMany(mappedBy = "store")
    public Collection<Staff> getStaff() {
        return staff;
    }

    public void setStaff(Collection<Staff> staff) {
        this.staff = staff;
    }

    @ManyToOne
    @JoinColumn(name = "manager_staff_id", referencedColumnName = "staff_id", nullable = false)
    public Staff getStaffByManager() {
        return staffByManager;
    }

    public void setStaffByManager(Staff staffByManager) {
        this.staffByManager = staffByManager;
    }

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id", nullable = false)
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
