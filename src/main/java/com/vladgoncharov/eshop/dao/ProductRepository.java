package com.vladgoncharov.eshop.dao;

import com.vladgoncharov.eshop.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findFirstByTitle(String title);
    Product findFirstById(Long id);

    @Modifying
    @Query("update Product p set p.title = ?1, p.price = ?2 where p.id = ?3")
    void setUserInfoById(String firstname, String lastname, Integer userId);

}
