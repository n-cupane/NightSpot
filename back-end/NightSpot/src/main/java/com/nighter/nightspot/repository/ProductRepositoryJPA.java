package com.nighter.nightspot.repository;

import com.nighter.nightspot.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepositoryJPA extends JpaRepository<Product, Long> {
}
