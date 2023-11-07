package com.knusolution.datahub.user

import org.jetbrains.annotations.NotNull
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
@Entity
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userId:Long = 0,

    @NotNull
    @Column
    val loginId:String,

    @NotNull
    @Column
    val password:String,

    @NotNull
    @Column
    val companyName:String,

    @NotNull
    @Column
    val developerName:String,

    @NotNull
    @Column
    val contactNum:String,

    @NotNull
    @Column
    val department:String,

    @NotNull
    @Column
    val departmentName:String,

    @NotNull
    @Column
    val role:Role,
)
enum class Role{
    ADMIN,USER
}

fun UserDto.asEntity() = UserEntity(
    loginId = this.loginId,
    password = this.password,
    companyName = this.companyName,
    developerName = this.developerName,
    contactNum = this.contactNum,
    department = this.department,
    departmentName = this.departmentName,
    role = this.role
)
