package com.knusolution.datahub.application

import com.knusolution.datahub.domain.*
import org.springframework.stereotype.Service
import com.knusolution.datahub.domain.UserRepository
import com.knusolution.datahub.system.domain.*

@Service
class LoginService(
    private val userRepository: UserRepository,
    private val systemRepository: SystemRepository,
    private val baseCategoryRepository: BaseCategoryRepository,
    private val detailCategoryRepository: DetailCategoryRepository,
){

    fun registerUser(req: JoinRequest){
        val system = systemRepository.save(req.asSystemDto().asEntity())
        val user = req.asUserDto().asEntity(password = req.loginId)
        user.systems.add(system)
        userRepository.save(user)

        registerCategory(system)
    }
    private fun registerCategory(system: SystemEntity)
    {
        getBaseCategory().forEach {
            val baseCategoryEntity = it.asEntity(system)
            baseCategoryRepository.save(baseCategoryEntity)
            getDetailCategory(it.baseCategoryName).forEach {
                    detail-> detailCategoryRepository.save(detail.asEntity(baseCategoryEntity))
            }
        }
    }
    fun loginUser(req: LoginRequest): LoginResponse?
    {
        val userEntity = userRepository.findByLoginId(req.loginId)
        val userDto = userEntity?.let { it ->
            val systemIds = it.systems.toList().map { system -> system.systemId }
            if(req.password == it.password) it.asUserDto(systemIds = systemIds) else null
        }
        return userDto?.asLoginResponse(accessToken = "")
    }
    fun exitsUserByLoginId(loginId:String) = userRepository.existsByLoginId(loginId)
}