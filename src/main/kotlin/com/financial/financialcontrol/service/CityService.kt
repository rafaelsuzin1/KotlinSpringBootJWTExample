package com.financial.financialcontrol.service

import com.financial.financialcontrol.exception.AppException
import com.financial.financialcontrol.exception.NotFoundException
import com.financial.financialcontrol.model.City
import com.financial.financialcontrol.repository.CityRepository
import org.springframework.stereotype.Service

@Service
class CityService(override val repository: CityRepository) : JpaService<City, Long>() {
    override fun validateForSave(entity: City): Boolean {
        return true
    }

    override fun validateForSave(entities: List<City>): Boolean {
        return true
    }

    override fun messageIfNotPassInSaveValidade(): String {
        return ""
    }

    override fun findByIdValidated(id: Long): City {
        return repository.findById(id).orElseThrow { NotFoundException("Country $id not found.") }
    }
}