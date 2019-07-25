package com.financial.financialcontrol.config

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("Authorization", "User-Agent", "Keep-Alive", "Content-Type", "accept", "origin", "X-Requested-Wit")
                .allowedMethods("HEAD", "PUT", "GET", "POST", "DELETE", "OPTIONS", "PATCH")
                .allowedOrigins("*")
                .maxAge(3600)
    }

    override fun extendMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        converters?.stream()?.filter { c -> c is MappingJackson2HttpMessageConverter }?.forEach { c ->
            (c as MappingJackson2HttpMessageConverter).objectMapper.registerModule(Hibernate5Module()
                    .enable(Hibernate5Module.Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS))

            executeOnStartUp(c.objectMapper)

        }
    }

    /*
     * Caso queira montar um objeto para ver o JSON do mesmo, assim ficam mais fáceis os testes.
     */
    fun executeOnStartUp(objectMapper: ObjectMapper) {
        try {
            println(
                    objectMapper.writeValueAsString(
                            ""
                    )
            )
        } catch (e: JsonProcessingException) {
            e.printStackTrace()
        }

    }
}
