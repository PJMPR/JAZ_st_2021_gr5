package com.example.demo.repositories;
import com.example.demo.model.Category;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class CategoryInsertRepo {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void insertCategory(Category category) {
        entityManager.createNativeQuery("INSERT INTO Category(name) VALUES (?)")
                .setParameter(1, category.getName())
                .executeUpdate();
    }
}
