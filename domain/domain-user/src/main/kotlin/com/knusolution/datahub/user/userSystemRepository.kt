package com.knusolution.datahub.user

import org.springframework.data.jpa.repository.JpaRepository

interface userSystemRepository:JpaRepository<UserSystemEntity,Long> {
}