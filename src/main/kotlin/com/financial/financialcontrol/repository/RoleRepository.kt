package com.financial.financialcontrol.repository

import com.financial.financialcontrol.model.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Long>
