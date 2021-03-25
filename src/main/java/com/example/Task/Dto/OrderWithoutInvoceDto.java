package com.example.Task.Dto;

import com.example.Task.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderWithoutInvoceDto {
    private Long id;
    private String date;
    private Double amount;

}
