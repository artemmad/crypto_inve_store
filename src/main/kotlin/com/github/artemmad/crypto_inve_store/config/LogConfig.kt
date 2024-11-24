package com.github.artemmad.crypto_inve_store.config

import jakarta.servlet.http.HttpServletRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.util.AntPathMatcher
import org.springframework.web.filter.CommonsRequestLoggingFilter

@Configuration
class LogConfig {
    @Bean
    fun logFilter(): CommonsRequestLoggingFilter {
        val filter: CommonsRequestLoggingFilter = CustomRequestLoggingFilter()
        filter.setIncludeQueryString(true)
        filter.setIncludePayload(true)
        filter.setMaxPayloadLength(MAX_PAYLOAD_LENGTH)
        filter.setIncludeHeaders(true)
        filter.setAfterMessagePrefix("REQUEST DATA: ")
        return filter
    }

    private class CustomRequestLoggingFilter : CommonsRequestLoggingFilter() {
        override fun shouldLog(request: HttpServletRequest): Boolean {
            val requestURI = request.requestURI
            val method = request.method

            // Проверяем, соответствует ли запрос одному из исключенных шаблонов
            val isExcludedUrl = EXCLUDED_PATTERNS.stream()
                .anyMatch { pattern: String? ->
                    PATH_MATCHER.match(
                        pattern!!, requestURI
                    )
                }

            // Проверяем, исключен ли метод
            val isExcludedMethod = EXCLUDED_METHODS.contains(method)

            return !(isExcludedUrl || isExcludedMethod)
        }
    }

    companion object {
        private val PATH_MATCHER = AntPathMatcher()

        // Список шаблонов URL для исключения из логирования
        private val EXCLUDED_PATTERNS = listOf(
            "/actuator/**"
        )

        // Список HTTP-методов для исключения из логирования (опционально)
        private val EXCLUDED_METHODS = setOf(
            "OPTIONS"
        )

        private const val MAX_PAYLOAD_LENGTH = 100000
    }
}
