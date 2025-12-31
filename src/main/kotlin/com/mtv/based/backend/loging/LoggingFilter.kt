package com.mtv.based.backend.loging

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import java.nio.charset.StandardCharsets
import jakarta.servlet.Filter
import jakarta.servlet.annotation.WebFilter

@Component
@WebFilter("/*")
class LoggingFilter : Filter {

    private val log = LoggerFactory.getLogger(LoggingFilter::class.java)

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val req = if (request is HttpServletRequest) ContentCachingRequestWrapper(request) else request
        val res = if (response is HttpServletResponse) ContentCachingResponseWrapper(response) else response

        chain.doFilter(req, res)

        if (req is ContentCachingRequestWrapper && res is ContentCachingResponseWrapper) {
            val requestBody = String(req.contentAsByteArray, StandardCharsets.UTF_8)
            val responseBody = String(res.contentAsByteArray, StandardCharsets.UTF_8)

            log.info("API Request: ${req.method} ${req.requestURI}")
            log.info("Response Status: ${res.status}")
            log.info("Request Body: $requestBody")
            log.info("Response Body: $responseBody")

            res.copyBodyToResponse()
        }
    }
}
