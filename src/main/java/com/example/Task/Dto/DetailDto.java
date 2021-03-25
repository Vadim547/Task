package com.example.Task.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailDto {
    private Long id;
    private Long orderId;
    private Long productId;
    private Integer quantity;
}
