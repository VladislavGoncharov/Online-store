package com.vladgoncharov.eshop.dao;

import com.vladgoncharov.eshop.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findFirstByTitle(String title);

}
