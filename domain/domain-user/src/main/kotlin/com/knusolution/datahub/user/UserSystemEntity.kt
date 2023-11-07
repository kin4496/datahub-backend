package com.knusolution.datahub.user

import com.knusolution.datahub.system.SystemEntity
import javax.persistence.*

data class UserSystemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userSystemId:Long = 0,

    @ManyToMany(fetch = FetchType.LAZY) // 지연 로딩 설정
    @JoinColumn(name = "systemId")
    val systemId: SystemEntity,

    @ManyToMany(fetch = FetchType.LAZY) // 지연 로딩 설정
    @JoinColumn(name = "userId")
    val userId: UserEntity
)
