package com.knusolution.datahub.system

import org.springframework.data.jpa.repository.JpaRepository

interface DetailCategoryRepository:JpaRepository<DetailCategoryEntity,Long> {
}