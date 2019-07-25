package com.financial.financialcontrol.model.audit

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass
import java.io.Serializable
import java.time.Instant

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
@JsonIgnoreProperties(value = ["added", "updated"], allowGetters = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator::class)
abstract class AuditDate: Serializable {
        @CreatedDate
        var added: Instant? = null

        @LastModifiedDate
        var updated: Instant? = null
}



