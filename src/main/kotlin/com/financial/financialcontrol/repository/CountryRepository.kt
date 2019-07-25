package com.financial.financialcontrol.repository

import com.financial.financialcontrol.model.Country
import org.springframework.data.jpa.repository.JpaRepository

interface CountryRepository : JpaRepository<Country, Long>
