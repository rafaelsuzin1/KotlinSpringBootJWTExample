package com.financial.financialcontrol.model

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
@Table(name = "state")
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator::class)
class State(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,

        @NotBlank
        @Size(max = 3)
        var sigla: String,

        @ManyToOne(fetch = FetchType.EAGER, optional = false)
        @JoinColumn(name = "pais_id", nullable = false)
        var country: Country
)