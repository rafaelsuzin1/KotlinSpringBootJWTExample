package com.financial.financialcontrol.security

import io.jsonwebtoken.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

import java.util.Date

@Component
class JwtTokenProvider {

    @Value("\${app.jwtSecret}")
    private val jwtSecret: String? = null

    @Value("\${app.jwtExpirationInMs}")
    private val jwtExpirationInMs: Int = 0

    fun generateToken(authentication: Authentication): String {

        val userPrincipal = authentication.principal as UserPrincipal

        val now = Date()
        val expiryDate = Date(now.time + jwtExpirationInMs)

        return Jwts.builder().setSubject(java.lang.Long.toString(userPrincipal.id!!)).setIssuedAt(Date())
                .setExpiration(expiryDate).signWith(SignatureAlgorithm.HS512, jwtSecret).compact()
    }

    fun getUserIdFromJWT(token: String): Long? {
        val claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).body

        return java.lang.Long.parseLong(claims.subject)
    }

    fun validateToken(authToken: String): Boolean {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken)
            return true
        } catch (ex: SignatureException) {
            logger.error("Invalid signature JWT")
        } catch (ex: MalformedJwtException) {
            logger.error("Token JWT invalid")
        } catch (ex: ExpiredJwtException) {
            logger.error("Token JWT expired")
        } catch (ex: UnsupportedJwtException) {
            logger.error("Token JWT not suported")
        } catch (ex: IllegalArgumentException) {
            logger.error("JWT is empty.")
        }

        return false
    }

    companion object {

        private val logger = LoggerFactory.getLogger(JwtTokenProvider::class.java)
    }
}
