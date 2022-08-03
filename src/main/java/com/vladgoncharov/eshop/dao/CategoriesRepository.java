package com.vladgoncharov.eshop.dao;

import com.vladgoncharov.eshop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Category,Long> {
    Category findFirstByTitle(String title);
}
