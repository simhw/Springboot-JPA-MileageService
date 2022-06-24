package com.example.triplemileageservice.service.point;

import com.example.triplemileageservice.model.Point;
import com.example.triplemileageservice.model.User;
import com.example.triplemileageservice.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GetPointListService {
    private PointRepository pointRepository;

    @Autowired
    GetPointListService(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    @Transactional
    public List<Point> getAllPointList() {
        return pointRepository.findAll();
    }

    @Transactional
    public List<Point> getUserPointList(User principal) {
        return pointRepository.findAllByUser(principal);
    }

    @Transactional
    public int getUserTotalPoint(User principal) {
        int total = 0;
        List<Point> pointList = pointRepository.findAllByUser(principal);
        for (Point point: pointList) {
            total += point.getAmount();
        }
        return total;
    }
}
