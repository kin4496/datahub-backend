package com.knusolution.datahub.login.controller

import com.knusolution.datahub.login.domain.JoinRequest
import com.knusolution.datahub.login.domain.LoginRequest
import com.knusolution.datahub.login.service.LoginService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController(
    private val loginService: LoginService
){
    @GetMapping("/users")
    fun getUser() = loginService.getUsers()
    @PostMapping("/join/user")
    fun registerUser(@RequestBody req: JoinRequest){
        if(!loginService.exitsUserByLoginId(req.loginId))
            loginService.registerUser(req)
    }
    @PostMapping("/users")
    fun loginUser(@RequestBody req:LoginRequest) = loginService.loginUser(req)
}