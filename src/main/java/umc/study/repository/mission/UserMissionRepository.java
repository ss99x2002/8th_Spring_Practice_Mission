package umc.study.repository.mission;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.UserMission;

import java.util.Optional;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    boolean existsByUserIdAndMission_MissionIdAndStatus(Long userId, Long missionId, MissionStatus status);
    Optional<UserMission> findByUserIdAndMission_MissionId(Long userId, Long missionId);
}
