package com.example.LearningManagementSystem.controller;

import com.example.LearningManagementSystem.apiclient.FeignClientPayment;
import com.example.LearningManagementSystem.apiclient.PaymentClient;
import com.example.LearningManagementSystem.dto.CourseDto;
import com.example.LearningManagementSystem.dto.PaymentResponse;
import com.example.LearningManagementSystem.exception.ErrorResponse;
import com.example.LearningManagementSystem.service.service.CourseService;
import feign.FeignException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final PaymentClient paymentClient;
    private final FeignClientPayment feignClientPayment;


    @PostMapping
    public ResponseEntity<CourseDto> createCourse(@RequestBody @Valid CourseDto courseDto) {
        return ResponseEntity.ok(courseService.createCourse(courseDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable Long id, @RequestBody CourseDto courseDto) {
        return ResponseEntity.ok(courseService.updateCourse(id, courseDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok("Course deleted successfully");
    }


    @GetMapping("/payment/{paymentId}")
    public ResponseEntity<?> getPayment(@PathVariable Long paymentId) {
        try {
            PaymentResponse response = feignClientPayment.getPaymentById(paymentId);
            return ResponseEntity.ok(response);
        }
        catch (FeignException.NotFound e) {
            ErrorResponse error = new ErrorResponse();
            error.setStatus(HttpStatus.NOT_FOUND);
            error.setMessage("Payment not found for ID: " + paymentId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        catch (FeignException e) {
            ErrorResponse error = new ErrorResponse();
            error.setStatus(HttpStatus.BAD_GATEWAY);
            error.setMessage("Error calling Payment Service: " + e.status());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(error);
        }
        catch (Exception e) {
            ErrorResponse error = new ErrorResponse();
            error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            error.setMessage("Unexpected error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/payment")
    public ResponseEntity<List<PaymentResponse>> fetchAllPayment() {
        List<PaymentResponse> response = feignClientPayment.fetchAll();
        return ResponseEntity.ok(response);
    }
}
