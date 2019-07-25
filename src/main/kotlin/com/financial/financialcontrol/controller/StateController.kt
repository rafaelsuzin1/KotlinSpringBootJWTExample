package com.financial.financialcontrol.controller

import com.financial.financialcontrol.model.Country
import com.financial.financialcontrol.model.State
import com.financial.financialcontrol.service.CountryService
import com.financial.financialcontrol.service.StateService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/state")
class StateController(override val service: StateService) : JpaController<State, Long>()


