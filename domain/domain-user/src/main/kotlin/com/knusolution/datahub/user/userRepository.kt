package com.knusolution.datahub.user

import org.springframework.data.jpa.repository.JpaRepository

interface userRepository:JpaRepository<UserEntity,Long> {
}