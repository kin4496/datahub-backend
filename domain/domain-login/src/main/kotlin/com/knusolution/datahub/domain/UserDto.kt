package com.knusolution.datahub.domain

data class UserDto(
    val loginId:String,
    val companyName:String,
    val developerName:String,
    val contactNum:String,
    val department:String,
    val departmentName:String,
    val role: Role,
)

fun UserEntity.asUserDto() = UserDto(
    loginId = loginId,
    companyName = companyName,
    developerName = developerName,
    contactNum = contactNum,
    department = department,
    departmentName = departmentName,
    role = role,
)