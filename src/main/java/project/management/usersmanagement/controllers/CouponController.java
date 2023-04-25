package project.management.usersmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.management.usersmanagement.Repository.CouponRepo;
import project.management.usersmanagement.Repository.UserRepository;
import project.management.usersmanagement.dto.CouponViewDto;
import project.management.usersmanagement.dto.OrderViewDto;
import project.management.usersmanagement.entities.User;
import project.management.usersmanagement.mapper.CouponMapper;

import java.util.List;

@RestController
@RequestMapping("/coupons")
public class CouponController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CouponMapper couponMapper;
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CouponViewDto>> getOrdersByUser(@PathVariable("userId") Long userId) {
        User user = userRepository.getUserById(userId);
        return ResponseEntity.ok().body(couponMapper.toDto(user.getCoupons()));
    }
    }
