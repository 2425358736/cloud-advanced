package com.cloud.system.controller;

import com.cloud.common.core.domain.AuthToken;
import com.cloud.system.rpc.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 刘志强
 * @date 2020/8/11 15:44
 */

@RestController
@Slf4j
@CrossOrigin
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthToken login(String userName, String password) {
        return authService.getToken("Basic Y29uc3VtZXI6MTIzNDU2",userName, password, "password");
    }
}
