package com.financial.financialcontrol.model

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import org.hibernate.annotations.NaturalId

import javax.persistence.*

@Entity
@Table(name = "roles")
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator::class)
class Role (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,

        @Enumerated(EnumType.STRING)
        @NaturalId
        @Column(length = 60)
        var name: RoleName
){

    constructor(name: RoleName) : this(null, name) {
        this.name = name
    }

}
