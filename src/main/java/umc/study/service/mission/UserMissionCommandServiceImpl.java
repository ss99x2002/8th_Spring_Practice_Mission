package umc.study.service.mission;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.converter.UserMissionConverter;
import umc.study.domain.mapping.UserMission;
import umc.study.domain.mission.Mission;
import umc.study.domain.user.User;
import umc.study.repository.mission.MissionRepository;
import umc.study.repository.mission.UserMissionRepository;
import umc.study.repository.user.UserRepository;
import umc.study.web.dto.request.UserMissionRequestDto;
import umc.study.web.dto.response.UserMissionResponseDto;

@Service
@RequiredArgsConstructor
public class UserMissionCommandServiceImpl implements UserMissionCommandService {

    private final UserRepository userRepository;
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;

    @Override
    public UserMission challengeMission(Long missionId, UserMissionRequestDto.RegisterDto dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 미션입니다."));

        UserMission userMission = UserMissionConverter.toUserMission(user, mission);

        return userMissionRepository.save(userMission);
    }
}