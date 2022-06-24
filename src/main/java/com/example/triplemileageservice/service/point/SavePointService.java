package com.example.triplemileageservice.service.point;

import com.example.triplemileageservice.model.Point;
import com.example.triplemileageservice.model.PointStatus;
import com.example.triplemileageservice.model.User;
import com.example.triplemileageservice.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SavePointService {
    private PointRepository pointRepository;

    @Autowired
    SavePointService(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    @Transactional
    public void savePoint(User principal, int amount, PointStatus status) {
        Point point = new Point();
        point.setUser(principal);
        point.setAmount(amount);
        point.setStatus(status);

        pointRepository.save(point);
    }
}
