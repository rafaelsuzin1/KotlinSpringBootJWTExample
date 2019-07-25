package com.financial.financialcontrol.model

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size


@Entity
@Table(name = "cidade")
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator::class)
class City(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,

        @NotBlank
        @Size(max = 80)
        var descricao: String,

        @Column(length = 7)
        var codCidadeIBGE: Long,

        var latitude: Float,

        var longitude: Float,

        @ManyToOne(fetch = FetchType.EAGER, optional = false)
        @JoinColumn(name = "estado_id", nullable = false)
        var state: State
)