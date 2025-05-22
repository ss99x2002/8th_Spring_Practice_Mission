package umc.study.service.store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.converter.StoreConverter;
import umc.study.domain.store.FoodCategory;
import umc.study.domain.store.Region;
import umc.study.domain.store.Store;
import umc.study.repository.foodcategory.FoodCategoryRepository;
import umc.study.repository.region.RegionRepository;
import umc.study.repository.store.StoreRepository;
import umc.study.web.dto.request.StoreRequestDto;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService{

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    public Store registerStore(StoreRequestDto.RegisterDto request) {
        Region region =  regionRepository.findById(request.getRegionId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 지역입니다."));
        FoodCategory category = foodCategoryRepository.findById(request.getFoodCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));
        Store newStore = StoreConverter.toStore(request, region, category);
        newStore.setCategory(category);
        return storeRepository.save(newStore);
    }
}
