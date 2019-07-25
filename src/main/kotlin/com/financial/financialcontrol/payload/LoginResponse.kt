package com.financial.financialcontrol.payload

import com.financial.financialcontrol.security.UserPrincipal

class LoginResponse(userPrincipal: UserPrincipal, private val accessToken: String) {

    private val nome: String
    private val login: String
    private val email: String
    private val tokenType = "Bearer"
    private var perfil: String? = null

    init {
        this.nome = userPrincipal.name
        this.login = userPrincipal.username
        this.email = userPrincipal.email
        for (p in userPrincipal.authorities) {
            perfil = p.authority
        }
    }

}
