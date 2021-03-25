package com.example.Task.controller;

import com.example.Task.Dto.DetailDto;
import com.example.Task.service.DetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detail")
public class DetailController {
    DetailService detailService;

    public DetailController(DetailService detailService) {
        this.detailService = detailService;
    }
    @PostMapping("/add")
    public ResponseEntity<DetailDto> addDetail(@RequestBody DetailDto detailDto) {
        return detailService.addDetail(detailDto);
    }


}
