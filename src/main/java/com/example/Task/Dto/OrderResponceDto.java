package com.example.Task.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponceDto {
    private String status;
    private Long invoiceNumber;

    public void setStatus(boolean status) {
        if (status) {
            this.status = "SUCCESS";
        }

        this.status = "FAILED";
    }
}
