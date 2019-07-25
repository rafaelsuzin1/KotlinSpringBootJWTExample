package com.financial.financialcontrol.repository

import com.financial.financialcontrol.model.City
import org.springframework.data.jpa.repository.JpaRepository

interface CityRepository : JpaRepository<City, Long>
