package com.knusolution.datahub.system

import org.jetbrains.annotations.NotNull
import javax.persistence.*
@Entity
@Table(name = "DetailCategory")
data class DetailCategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val detailCategoryId:Long = 0,

    @NotNull
    @Column
    val detailCategoryName:String,

    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩 설정
    @JoinColumn(name = "baseCategoryId")
    val baseCategoryId:BaseCategoryEntity
)
