package com.ohka.service;

import com.ohka.entity.Product;
import com.ohka.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public Optional<Product> getById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Product saveOrUpdate(Product product) {
        if (product.getId() != null && repository.existsById(product.getId())) {
            Product existing = repository.findById(product.getId())
                    .orElseThrow(() -> new RuntimeException("Product is not found"));

            existing.setName(product.getName());
            existing.setDescription(product.getDescription());
            existing.setPrice(product.getPrice());
            if (product.getImageBase64() != null && !product.getImageBase64().isBlank()) {
                existing.setImageBase64(product.getImageBase64());
            }
            return repository.save(existing);
        } else {
            return repository.save(product);
        }
    }

    public Page<Product> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Product> getFilteredProducts(
            String name,
            String category,
            Double minPrice,
            Double maxPrice,
            int page,
            int size,
            String sortBy,
            String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return repository.findAll((root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (name != null && !name.isEmpty()) {
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            if (category != null && !category.isEmpty()) {
                predicate = cb.and(predicate,
                        cb.equal(cb.lower(root.get("category").get("name")), category.toLowerCase()));
            }

            if (minPrice != null) {
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("price"), minPrice));
            }

            if (maxPrice != null) {
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("price"), maxPrice));
            }

            return predicate;
        }, pageable);
    }
}
