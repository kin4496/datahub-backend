package com.knusolution.datahub.domain

import com.knusolution.datahub.system.domain.DetailCategoryEntity
import java.time.LocalDateTime

data class ArticleDto(
    val uploadDate:LocalDateTime,
    val approval:String,
    val declineDetail:String,
    val taskFileUrl:String,
    val taskFileName:String,
    val declineFileUrl:String,
    val declineFileName:String,
    val detailCategoryId: DetailCategoryEntity
)
