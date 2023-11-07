package com.knusolution.datahub.article

import com.knusolution.datahub.system.DetailCategoryEntity
import java.time.LocalDateTime

data class ArticleDto(
    val uploadDate:LocalDateTime,
    val approval:String,
    val declineDetail:String,
    val taskFileUrl:String,
    val taskFileName:String,
    val declineFileUrl:String,
    val declineFileName:String,
    val detailCategoryId:DetailCategoryEntity
)
