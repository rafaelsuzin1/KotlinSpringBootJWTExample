package com.financial.financialcontrol.service

import com.financial.financialcontrol.exception.AppException
import com.financial.financialcontrol.exception.NotFoundException
import com.financial.financialcontrol.model.Country
import com.financial.financialcontrol.repository.CountryRepository
import org.springframework.stereotype.Service

@Service
class CountryService(override val repository: CountryRepository) : JpaService<Country, Long>() {
    override fun validateForSave(entity: Country): Boolean {
        return true
    }

    override fun validateForSave(entities: List<Country>): Boolean {
        return true
    }

    override fun messageIfNotPassInSaveValidade(): String {
        return ""
    }

    override fun findByIdValidated(id: Long): Country {
        return repository.findById(id).orElseThrow { NotFoundException("Country $id not found.") }
    }
}