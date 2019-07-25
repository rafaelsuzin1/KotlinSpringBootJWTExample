package com.financial.financialcontrol.repository

import com.financial.financialcontrol.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, Long> {

     fun findByUsernameOrEmail(username: String, email: String): Optional<User>
}
