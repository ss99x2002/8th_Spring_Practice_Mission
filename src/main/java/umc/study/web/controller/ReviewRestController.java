package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.ReviewConverter;
import umc.study.domain.review.Review;
import umc.study.service.review.ReviewCommandService;
import umc.study.web.dto.request.ReviewRequestDto;
import umc.study.web.dto.response.ReviewResponseDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;

    @GetMapping("/users/{userId}")
    public ApiResponse<ReviewResponseDto.UserReviewListResult> getUserReviewList(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Slice<Review> reviewList = reviewCommandService.findUserReviewList(userId, page, size);
        return ApiResponse.onSuccess(ReviewConverter.toUserReviewResultDto(reviewList));
    }


    @PostMapping("/")
    public ApiResponse<ReviewResponseDto.RegisterResultDto> postRegisterStore(
            @RequestBody @Valid ReviewRequestDto.RegisterDto request) {
        Review review = reviewCommandService.registerReview(request);
        return ApiResponse.onSuccess(ReviewConverter.toReviewRegisterResultDto(review));
    }


}
