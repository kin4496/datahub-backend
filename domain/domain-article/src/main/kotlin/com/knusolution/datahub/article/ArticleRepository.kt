package com.knusolution.datahub.article

import org.springframework.data.jpa.repository.JpaRepository

interface ArticleRepository: JpaRepository<ArticleEntity,Long> {
}