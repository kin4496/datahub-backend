package com.knusolution.datahub.controll

import com.knusolution.datahub.domain.JoinRequest
import com.knusolution.datahub.domain.LoginRequest
import com.knusolution.datahub.application.LoginService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController(
    private val loginService: LoginService
){
    @PostMapping("/join/user")
    fun registerUser(@RequestBody req: JoinRequest){
        if(!loginService.exitsUserByLoginId(req.loginId))
            loginService.registerUser(req)
    }
    @PostMapping("/users")
    fun loginUser(@RequestBody req: LoginRequest) = loginService.loginUser(req)
}