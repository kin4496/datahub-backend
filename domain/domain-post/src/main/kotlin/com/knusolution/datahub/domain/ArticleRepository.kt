package com.knusolution.datahub.domain

import org.springframework.data.jpa.repository.JpaRepository

interface ArticleRepository: JpaRepository<ArticleEntity,Long> {
}