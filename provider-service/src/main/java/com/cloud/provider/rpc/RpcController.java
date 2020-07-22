package com.cloud.provider.rpc;

import com.cloud.provider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 刘志强
 * @date 2020/7/16 15:21
 */
@RestController
@CrossOrigin
@RequestMapping("/rpc")
public class RpcController {

    private final UserService userService;

    public RpcController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("getUserInfo")
    public Map<String,String> getUserInfo() {
        return userService.getUserInfo();
    }
}
