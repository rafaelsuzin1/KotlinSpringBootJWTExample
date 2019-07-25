package com.financial.financialcontrol.model

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size
import java.util.Objects

@Entity
@Table(name = "country")
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator::class)
class Country (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,

        @NotBlank
        @Size(max = 3)
        var sigla: String,

        @NotBlank
        @Size(max = 80)
        var descricao: String
)