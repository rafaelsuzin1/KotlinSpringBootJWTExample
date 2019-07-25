package com.financial.financialcontrol.security

import com.financial.financialcontrol.exception.ResourceNotFoundException
import com.financial.financialcontrol.model.User
import com.financial.financialcontrol.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CustomUserDetailsService : UserDetailsService {

    @Autowired
    internal var userRepository: UserRepository? = null

    @Transactional
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(usernameOrEmail: String): UserDetails {
        val user = userRepository!!.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow { UsernameNotFoundException("User not found with user name or e-mail : $usernameOrEmail") }

        return UserPrincipal.create(user)
    }

    @Transactional
    fun loadUserById(id: Long): UserDetails {
        val user = userRepository!!.findById(id).orElseThrow { ResourceNotFoundException("User", "id", id!!) }
        return UserPrincipal.create(user)
    }
}