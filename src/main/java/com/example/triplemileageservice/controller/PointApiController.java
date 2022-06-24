package com.example.triplemileageservice.controller;

import com.example.triplemileageservice.model.Point;
import com.example.triplemileageservice.model.User;
import com.example.triplemileageservice.repository.UserRepository;
import com.example.triplemileageservice.service.point.GetPointListService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class PointApiController {
    private final GetPointListService getPointListService;
    private final UserRepository userRepository;

    @GetMapping("/points")
    public List<Point> getAllPointList() {
        return getPointListService.getAllPointList();
    }

    @GetMapping("/points/{id}")
    public List<Point> getUserPointList(@PathVariable String id) {
        User user = userRepository.findById(id).orElseThrow(NullPointerException::new);
        return getPointListService.getUserPointList(user);
    }
}
