package com.financial.financialcontrol.service

import com.financial.financialcontrol.exception.AppException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

import java.io.Serializable
import java.util.ArrayList
import java.util.Optional

abstract class JpaService<T, ID : Serializable> {

    protected abstract val repository: JpaRepository<T, ID>

    abstract fun validateForSave(entity: T): Boolean
    abstract fun validateForSave(entities: List<T>): Boolean

    abstract fun messageIfNotPassInSaveValidade(): String

    fun <S : T> save(entity: S): S {
        try {
            if (!validateForSave(entity))
                throw AppException(messageIfNotPassInSaveValidade())
            return repository.save(entity)
        } catch (e: Exception) {
            throw AppException(e.message)
        }

    }

    fun <S : T> saveAll(entities: Iterable<S>): List<S> {
        try {
            if (entities == null)
                return ArrayList()

            if (!validateForSave(entities as List<T>))
                throw AppException(messageIfNotPassInSaveValidade())

            return repository.saveAll(entities)
        } catch (e: Exception) {
            throw AppException(e.message)
        }

    }

    fun delete(`object`: T) {
        repository.delete(`object`)
    }

    fun delete(id: ID) {
        repository.deleteById(id)
    }

    fun findById(id: ID): Optional<T> {
        return repository.findById(id)
    }

    abstract fun findByIdValidated(id: ID): T

    fun existsById(id: ID): Boolean {
        return repository.existsById(id)
    }

    fun findAll(): List<T> {
        return repository.findAll()
    }

    fun findAll(pageable: Pageable): Page<T> {
        return repository.findAll(pageable)
    }

    fun findAll(page: Int, size: Int): Page<T> {
        return repository.findAll(PageRequest.of(page, size))
    }

    fun count(): Long {
        return repository.count()
    }

}
