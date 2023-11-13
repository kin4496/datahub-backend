package com.knusolution.datahub.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository:JpaRepository<UserEntity,Long> {
    fun existsByLoginId(loginId:String):Boolean
    fun findByLoginId(loginId: String):UserEntity?
}