package com.financial.financialcontrol.controller

import com.financial.financialcontrol.model.City
import com.financial.financialcontrol.model.Country
import com.financial.financialcontrol.service.CityService
import com.financial.financialcontrol.service.CountryService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/city")
class CityController(override val service: CityService) : JpaController<City, Long>()


