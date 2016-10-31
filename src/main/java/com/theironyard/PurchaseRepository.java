package com.theironyard;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by michaelplott on 10/24/16.
 */
public interface PurchaseRepository extends PagingAndSortingRepository<Purchase, Integer> {
    Page<Purchase> findByCategory(Pageable pageable, String category);
    List<Purchase> findAll();
}
