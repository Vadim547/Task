package com.example.Task.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private Long categoryId;
    private String description;
    private Double price;
    private String photo;

    private ProductDto(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.categoryId = builder.categoryId;
        this.description = builder.description;;
        this.price = builder.price;
        this.photo = builder.photo;
    }

    public static class Builder {
        private Long id;
        private String name;
        private Long categoryId;
        private String description;
        private Double price;
        private String photo;

        public Builder(Long id, Long categoryId) {
            this.id = id;
            this.categoryId = categoryId;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setPrice(Double price) {
            this.price = price;
            return this;
        }

        public Builder setPhoto(String photo) {
            this.photo = photo;
            return this;
        }

        public ProductDto build() {
           return new ProductDto(this);
        }

    }

}
