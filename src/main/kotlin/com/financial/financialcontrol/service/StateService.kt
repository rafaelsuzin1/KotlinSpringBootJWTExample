package com.financial.financialcontrol.service

import com.financial.financialcontrol.exception.AppException
import com.financial.financialcontrol.exception.NotFoundException
import com.financial.financialcontrol.model.State
import com.financial.financialcontrol.repository.StateRepository
import org.springframework.stereotype.Service

@Service
class StateService(override val repository: StateRepository) : JpaService<State, Long>() {
    override fun validateForSave(entity: State): Boolean {
        return true
    }

    override fun validateForSave(entities: List<State>): Boolean {
        return true
    }

    override fun messageIfNotPassInSaveValidade(): String {
        return ""
    }

    override fun findByIdValidated(id: Long): State {
        return repository.findById(id).orElseThrow { NotFoundException("State $id not found.") }
    }
}