package com.knusolution.datahub.system

data class SystemDto(
    val systemName:String
)
data class BaseCategoryDto(
    val baseCategoryName:String,
)
data class DetailCategoryDto(
    val detailCategoryName:String,
)

fun getBaseCategory() = baseCategories.map{BaseCategoryDto(baseCategoryName = it)}
fun getDetailCategory(baseCategoryName: String) = detailCategories
    .getOrDefault(baseCategoryName, listOf())
    .map { DetailCategoryDto(detailCategoryName = it) }

val baseCategories = listOf(
    "표준 정의서",
    "구축 정의서",
    "진단 보고서",
)

val detailCategories = mapOf(
    "표준 정의서" to listOf("DB 표준용어","DB 표준 도메인","DB 표준단어","DB 표준코드"),
    "구축 정의서" to listOf("데이터베이스 정의서", "테이블 정의서", "컬럼 정의서", "ERD 또는 관계정의서"),
    "진단 보고서" to listOf("구조진단 보고서", "표준진단 보고서", "값진단 보고서", "업무규칙 정의서"),
)