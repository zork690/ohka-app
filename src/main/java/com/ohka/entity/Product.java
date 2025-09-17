package com.ohka.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Getter
@Setter
@Data
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name is required")
    @Size(max = 100, message = "Product name must be less than 100 characters")
    private String name;

    @NotBlank(message = "Product description is required")
    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", inclusive = true, message = "Price must be greater than 0")
    private Double price;

    @Size(max = 1000000, message = "Image is too large")
    @Pattern(
            regexp = "^data:image/(png|jpeg|jpg);base64,[A-Za-z0-9+/=\\r\\n]+$",
            message = "Image must be a valid Base64-encoded PNG or JPEG"
    )
    @Lob
    @Column(columnDefinition = "TEXT")
    private String imageBase64;

    @NotNull(message = "Category is required")
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


}
