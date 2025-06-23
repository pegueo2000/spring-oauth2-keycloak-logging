package org.rsu.sec.rsusecureapp.repository;

import org.rsu.sec.rsusecureapp.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {

}
