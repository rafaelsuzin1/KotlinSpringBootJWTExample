package com.financial.financialcontrol.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class ResourceNotFoundException(val resourceName: String, val fieldName: String, val fieldValue: Any) :
        RuntimeException(String.format("%s não encontrado com %s : '%s'", resourceName, fieldName, fieldValue))
