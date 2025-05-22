package umc.study.service.mission;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.converter.MissionConverter;
import umc.study.domain.mission.Mission;
import umc.study.domain.store.Store;
import umc.study.repository.mission.MissionRepository;
import umc.study.repository.store.StoreRepository;
import umc.study.web.dto.request.MissionRequestDto;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    @Override
    public Mission registerMission(MissionRequestDto.RegisterDto request) {
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));
        Mission mission = MissionConverter.toMission(request, store);
        return missionRepository.save(mission);
    }
}
