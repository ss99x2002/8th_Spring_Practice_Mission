package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.UserMissionConverter;
import umc.study.service.mission.UserMissionCommandService;
import umc.study.web.dto.request.UserMissionRequestDto;
import umc.study.web.dto.response.UserMissionResponseDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/userMissions")
public class UserMissionRestController {

    private final UserMissionCommandService userMissionCommandService;

    @GetMapping("/{userId}/in_progress")
    public ApiResponse<UserMissionResponseDto.UserMissionListDto> getInProgressMission(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ApiResponse.onSuccess(
                UserMissionConverter.toUserMissionListDto(userMissionCommandService.findInProgressMissions(userId, page, size))
        );
    }

    @PostMapping("/challenge")
    public ApiResponse<UserMissionResponseDto.RegisterResultDto> postChallengeMission(
            @RequestBody @Valid UserMissionRequestDto.RegisterDto request) {

        return ApiResponse.onSuccess(
                UserMissionConverter.toUserMissionRegisterResultDto(userMissionCommandService.challengeMission(request))
        );
    }

    @PostMapping("/completed")
    public ApiResponse<UserMissionResponseDto.CompletedResultDto> postCompletedMission(
            @RequestBody @Valid UserMissionRequestDto.CompletedDto request) {
        return ApiResponse.onSuccess(
                UserMissionConverter.toUserMissionCompletedResultDto(userMissionCommandService.completeMission(request))
        );
    }
}