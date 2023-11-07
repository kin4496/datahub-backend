package com.knusolution.datahub.system

import org.springframework.data.jpa.repository.JpaRepository

interface BaseCategoryRepository:JpaRepository<BaseCategoryEntity,Long> {
}