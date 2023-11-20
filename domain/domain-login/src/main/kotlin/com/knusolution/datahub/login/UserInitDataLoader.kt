package com.knusolution.datahub.login

import com.knusolution.datahub.user.Role
import com.knusolution.datahub.user.UserDto
import com.knusolution.datahub.user.UserRepository
import com.knusolution.datahub.user.asEntity
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class UserInitDataLoader(
    private val userRepository: UserRepository,
) : CommandLineRunner{
    override fun run(vararg args: String?) {
        val user = UserDto(
            loginId = "admin",
            companyName = "",
            developerName = "",
            contactNum = "",
            department = "",
            departmentName = "",
            role = Role.ADMIN
        )
        userRepository.save(user.asEntity(password = "admin"))
    }
}