package umc.study.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.study.apiPayload.ApiResponse;
import umc.study.web.dto.response.StoreResponseDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {
    @PostMapping("/")
    public ApiResponse<StoreResponseDto> registerStore() {
        return null;
    }
}
