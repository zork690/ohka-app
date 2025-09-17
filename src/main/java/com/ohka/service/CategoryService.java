package com.ohka.service;

import com.ohka.entity.Category;
import com.ohka.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public List<Category> getAll() {
        return repository.findAll();
    }

    public Optional<Category> getById(Long id) {
        return repository.findById(id);
    }

    public Category create(Category category) {
        return repository.save(category);
    }

    public Category update(Long id, Category updated) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setName(updated.getName());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Category do not found"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
