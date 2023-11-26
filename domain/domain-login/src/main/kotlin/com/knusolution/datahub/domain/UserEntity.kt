package com.knusolution.datahub.domain

import com.knusolution.datahub.system.domain.SystemEntity
import org.jetbrains.annotations.NotNull
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@Table(name = "User")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userId:Long = 0,

    @NotNull
    @Column
    val loginId:String,

    @NotNull
    @Column
    val password:String,

    @NotNull
    @Column
    val companyName:String,

    @NotNull
    @Column
    val developerName:String,

    @NotNull
    @Column
    val contactNum:String,

    @NotNull
    @Column
    val department:String,

    @NotNull
    @Column
    val departmentName:String,

    @NotNull
    @Column
    val role: Role,

    @ManyToMany
    @JoinTable(name = "user_system",
        joinColumns = [JoinColumn(name = "userId")],
        inverseJoinColumns = [JoinColumn(name = "systemId")]
    )
    var systems: MutableSet<SystemEntity> = HashSet()
)
enum class Role{
    ADMIN,USER
}

fun UserDto.asEntity(password: String) =
    UserEntity(
        loginId = this.loginId,
        password = password,
        companyName = this.companyName,
        developerName = this.developerName,
        contactNum = this.contactNum,
        department = this.department,
        departmentName = this.departmentName,
        role = this.role
    )
