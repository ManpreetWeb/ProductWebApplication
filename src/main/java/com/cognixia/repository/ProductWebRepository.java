package com.cognixia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.model.ProductWeb;

@Repository
public interface ProductWebRepository extends JpaRepository<ProductWeb, Integer> {

	@Query("select p from ProductWeb p where category LIKE %?1%")
	List<ProductWeb> findByCategory(String category);

	@Query("select p from ProductWeb p where p.code LIKE %?1%  and p.inStock is true and p.quantity > 0 ")
	List<ProductWeb> fndByCode(String code);

	@Query("select p from ProductWeb p where rating=5")
	List<ProductWeb> getMaxRating();

	@Modifying
	@Query("delete from ProductWeb p where rating = 1")
	void deleteByRating();
}
