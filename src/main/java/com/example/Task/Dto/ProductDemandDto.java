package com.example.Task.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDemandDto {
    private Long id;
    private Long quantity;

   public ProductDemandDto(Object[] objects) {
       this.id = (Long) objects[0];
       this.quantity = (Long) objects[1];
   }
}
