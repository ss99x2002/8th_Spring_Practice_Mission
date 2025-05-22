package umc.study.web.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class UserMissionRequestDto {

    @Getter
    @Setter
    public static class RegisterDto {
        @NotNull(message = "유저 ID는 필수입니다.")
        private Long userId;
    }
}