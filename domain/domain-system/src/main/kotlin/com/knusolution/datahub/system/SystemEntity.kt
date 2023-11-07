package com.knusolution.datahub.system

import org.jetbrains.annotations.NotNull
import javax.persistence.*

@Entity
data class SystemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val systemId:Long = 0,

    @NotNull
    @Column
    val systemName:String,
)

fun SystemDto.asEntity() = SystemEntity(systemName = this.systemName)
