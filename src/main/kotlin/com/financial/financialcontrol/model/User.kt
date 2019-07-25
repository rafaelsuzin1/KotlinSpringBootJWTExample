package com.financial.financialcontrol.model

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.financial.financialcontrol.model.audit.AuditDate
import org.hibernate.annotations.NaturalId
import java.time.Instant
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
@Table(name = "users", uniqueConstraints = [UniqueConstraint(columnNames = ["username"]), UniqueConstraint(columnNames = ["email"])])
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator::class)
class User : AuditDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @NotBlank
    @Size(max = 40)
    var name: String

    @NotBlank
    @Size(max = 15)
    var username: String

    @NaturalId
    @NotBlank
    @Size(max = 40)
    @Email
    var email: String

    @JsonIgnore
    @NotBlank
    @Size(max = 100)
    var password: String

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = [JoinColumn(name = "user_id")], inverseJoinColumns = [JoinColumn(name = "role_id")])
    var roles: MutableSet<Role>? = null

    constructor(name: String, username: String, email: String, password: String)  {
        this.name = name
        this.username = username
        this.email = email
        this.password = password
    }

    fun add(role: Role): User {
        if (roles == null)
            roles = HashSet()
        roles!!.add(role)
        return this
    }
}