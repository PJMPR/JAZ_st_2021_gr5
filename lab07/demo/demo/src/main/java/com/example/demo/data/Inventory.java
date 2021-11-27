package com.example.demo.data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
public class Inventory {
    private int inventoryId;
    private Timestamp lastUpdate;
    private Film filmByFilmId;
    private Store storeByStoreId;
    private Collection<Rental> rentalsByInventoryId;

    @Id
    @Column(name = "inventory_id")
    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
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

        Inventory inventory = (Inventory) o;

        if (inventoryId != inventory.inventoryId) return false;
        if (lastUpdate != null ? !lastUpdate.equals(inventory.lastUpdate) : inventory.lastUpdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = inventoryId;
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "film_id", referencedColumnName = "film_id", nullable = false)
    public Film getFilmByFilmId() {
        return filmByFilmId;
    }

    public void setFilmByFilmId(Film filmByFilmId) {
        this.filmByFilmId = filmByFilmId;
    }

    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "store_id", nullable = false)
    public Store getStoreByStoreId() {
        return storeByStoreId;
    }

    public void setStoreByStoreId(Store storeByStoreId) {
        this.storeByStoreId = storeByStoreId;
    }

    @OneToMany(mappedBy = "inventoryByInventoryId")
    public Collection<Rental> getRentalsByInventoryId() {
        return rentalsByInventoryId;
    }

    public void setRentalsByInventoryId(Collection<Rental> rentalsByInventoryId) {
        this.rentalsByInventoryId = rentalsByInventoryId;
    }
}
