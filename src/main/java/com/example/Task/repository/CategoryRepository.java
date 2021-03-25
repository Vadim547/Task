package com.example.Task.repository;

import com.example.Task.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
//    @Query("FROM Category c where c.product.id = :productId")
    Optional<Category> findByProductId(Long productId);
}
