package com.knusolution.datahub.login.domain

import com.knusolution.datahub.system.SystemDto
import com.knusolution.datahub.user.UserDto

data class RegisterRequestDto(
    val user: UserDto,
    val systemDto:SystemDto
)
