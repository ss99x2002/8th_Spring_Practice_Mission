package umc.study.service.review;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.converter.ReviewConverter;
import umc.study.domain.review.Review;
import umc.study.domain.store.Store;
import umc.study.domain.user.User;
import umc.study.repository.review.ReviewRepository;
import umc.study.repository.store.StoreRepository;
import umc.study.repository.user.UserRepository;
import umc.study.web.dto.request.ReviewRequestDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public Review registerReview(ReviewRequestDto.RegisterDto request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));
        Review newReview = ReviewConverter.toReview(request,user,store);
        Review savedReview = reviewRepository.save(newReview);
        updateStoreAverageRate(store); // 사용자가 새롭게 평점 입력하면, store 평점이 업데이트 되어야함.
        return savedReview;
    }

    private void updateStoreAverageRate(Store store) {
        List<Review> reviews = reviewRepository.findAllByStore(store);
        BigDecimal avg = reviews.stream()
                .map(Review::getScore)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(reviews.size()), 2, RoundingMode.HALF_UP);

        store.setAverageRate(avg);
        storeRepository.save(store);
    }
}
