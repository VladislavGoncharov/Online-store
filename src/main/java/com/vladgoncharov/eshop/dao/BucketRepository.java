package com.vladgoncharov.eshop.dao;

import com.vladgoncharov.eshop.Entity.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BucketRepository extends JpaRepository<Bucket, Long> {
}
