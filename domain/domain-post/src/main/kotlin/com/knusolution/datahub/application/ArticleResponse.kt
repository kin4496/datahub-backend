package com.knusolution.datahub.application

import com.knusolution.datahub.domain.ArticleDto

data class ArticleResponse(
    val allPage:Int,
    val page:Int,
    val articles: List<ArticleDto>,
)