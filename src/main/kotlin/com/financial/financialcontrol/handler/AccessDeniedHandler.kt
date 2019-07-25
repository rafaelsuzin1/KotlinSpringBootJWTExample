package com.financial.financialcontrol.handler

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.io.IOException
import java.io.OutputStream
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.HashMap

class AccessDeniedHandler : org.springframework.security.web.access.AccessDeniedHandler {

    @Throws(IOException::class)
    override fun handle(request: HttpServletRequest, response: HttpServletResponse, accessDeniedException: AccessDeniedException) {
        response.status = HttpStatus.FORBIDDEN.value()

        val data = HashMap<String, Any>()
        data["timestamp"] = LocalDateTime.now().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ"))
        data["status"] = HttpStatus.FORBIDDEN.value()
        data["message"] = "Você não tem permissão para acessar esse recurso."
        data["path"] = request.servletPath

        val out = response.outputStream
        val mapper = ObjectMapper()
        mapper.enable(SerializationFeature.INDENT_OUTPUT)
        mapper.writeValue(out, data)
        out.flush()
    }
}
