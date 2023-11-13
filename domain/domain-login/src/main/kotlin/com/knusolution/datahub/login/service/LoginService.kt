package com.knusolution.datahub.login.service

import com.knusolution.datahub.login.domain.*
import com.knusolution.datahub.system.SystemRepository
import com.knusolution.datahub.system.asEntity
import com.knusolution.datahub.user.*
import org.springframework.stereotype.Service
import com.knusolution.datahub.user.UserRepository
@Service
class LoginService(
    private val userRepository: UserRepository,
    private val systemRepository:SystemRepository
){
    fun getUsers() = userRepository.findAll()
    fun registerUser(req: JoinRequest){
        val system = systemRepository.save(req.asSystemDto().asEntity())
        val user = req.asUserDto().asEntity(password = req.loginId)
        user.systems.add(system)
        userRepository.save(user)
    }

    fun loginUser(req:LoginRequest):LoginResponse?
    {
        val userEntity = userRepository.findByLoginId(req.loginId)
        val userDto = userEntity?.let {
            val systems = it.systems
            if(req.password == it.password) it.asUserDto() else null
        }
        return userDto?.asLoginResponse(accessToken = "")
    }
    fun exitsUserByLoginId(loginId:String) = userRepository.existsByLoginId(loginId)
}