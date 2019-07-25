package com.financial.financialcontrol.repository

import com.financial.financialcontrol.model.State
import org.springframework.data.jpa.repository.JpaRepository

interface StateRepository : JpaRepository<State, Long>
