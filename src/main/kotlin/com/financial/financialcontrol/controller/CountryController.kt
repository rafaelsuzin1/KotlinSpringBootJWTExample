package com.financial.financialcontrol.controller

import com.financial.financialcontrol.model.Country
import com.financial.financialcontrol.service.CountryService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/country")
class CountryController(override val service: CountryService) : JpaController<Country, Long>()


