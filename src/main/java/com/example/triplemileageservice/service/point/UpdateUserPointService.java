package com.example.triplemileageservice.service.point;

import com.example.triplemileageservice.model.User;
import com.example.triplemileageservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UpdateUserPointService {
    private final UserRepository userRepository;

    @Autowired
    UpdateUserPointService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void updateUserPoint(User principal, int amount) {
        User user = userRepository.findById(principal.getId()).orElseThrow(IllegalArgumentException::new);
        int point = principal.getPoint() + amount;
        user.setPoint(point);
    }
}
