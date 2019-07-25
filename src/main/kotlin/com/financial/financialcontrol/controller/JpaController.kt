package com.financial.financialcontrol.controller

import com.financial.financialcontrol.service.JpaService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

import javax.validation.Valid
import java.io.Serializable

abstract class JpaController<T, ID : Serializable> {

    protected abstract val service: JpaService<T, ID>

    val all: Collection<T>
        @GetMapping
        get() = service.findAll()

    @Transactional(rollbackFor = [Throwable::class])
    @PostMapping
    open fun save(@Valid @RequestBody `object`: T): ResponseEntity<*> {
        return ResponseEntity.ok(service.save(`object`))
    }

    @Transactional(rollbackFor = [Throwable::class])
    @PutMapping("/{id}")
    open fun update(@PathVariable(value = "id") id: ID, @Valid @RequestBody `object`: T): ResponseEntity<*> {
        return ResponseEntity.ok(service.save(`object`))
    }

    @Transactional(rollbackFor = [Throwable::class])
    @PostMapping("/list")
    open fun save(@Valid @RequestBody `object`: List<T>): ResponseEntity<*> {
        return ResponseEntity.ok(service.saveAll(`object`))
    }

    @Transactional(rollbackFor = [Throwable::class])
    @PutMapping("/list")
    open fun update(@Valid @RequestBody `object`: List<T>): ResponseEntity<*> {
        return ResponseEntity.ok(service.saveAll(`object`))
    }

    @Transactional(rollbackFor = [Throwable::class])
    @DeleteMapping("/{id}")
    open fun removeById(@PathVariable(value = "id") id: ID): ResponseEntity<*> {
        service.delete(id)
        return ResponseEntity.ok("Removido com sucesso")

    }

    @GetMapping("/{id}")
    fun findOne(@PathVariable(value = "id") id: ID): T {
        return service.findByIdValidated(id)
    }

    @GetMapping("/getAllPageable")
    fun getAll(@RequestParam("page") page: Int, @RequestParam("size") size: Int): Page<T> {
        return service.findAll(page, size)
    }

    @GetMapping("/count")
    fun count(): Long {
        return service.count()
    }

    @GetMapping("/exists/{id}")
    fun exists(@PathVariable id: ID): Boolean {
        return service.existsById(id)
    }
}
