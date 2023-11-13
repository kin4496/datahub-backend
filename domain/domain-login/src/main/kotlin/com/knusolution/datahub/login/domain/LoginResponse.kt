package com.knusolution.datahub.login.domain

import com.knusolution.datahub.user.Role
import com.knusolution.datahub.user.UserDto

data class LoginResponse(
    val accessToken:String,
    val user:UserDto,
)

fun UserDto.asLoginResponse(accessToken: String) = LoginResponse(
    accessToken = accessToken,
    user = this
)
