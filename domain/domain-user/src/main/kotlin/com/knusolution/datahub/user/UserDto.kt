package com.knusolution.datahub.user

data class UserDto(
    val loginId:String,
    val password:String,
    val companyName:String,
    val developerName:String,
    val contactNum:String,
    val department:String,
    val departmentName:String,
    val role: Role,
)
