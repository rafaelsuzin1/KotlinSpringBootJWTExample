package com.financial.financialcontrol.payload

import javax.validation.constraints.NotBlank

class LoginRequest(
        @NotBlank
        var loginOuEmail: String,

        @NotBlank
        var senha: String
)