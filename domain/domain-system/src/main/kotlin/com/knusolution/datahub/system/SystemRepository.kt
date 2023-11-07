package com.knusolution.datahub.system

import org.springframework.data.jpa.repository.JpaRepository

interface SystemRepository:JpaRepository<SystemEntity,Long> {
}