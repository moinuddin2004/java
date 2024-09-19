package com.example.webDemo.repo;

import com.example.webDemo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface productRepo  extends JpaRepository<Product,Integer> {

}
