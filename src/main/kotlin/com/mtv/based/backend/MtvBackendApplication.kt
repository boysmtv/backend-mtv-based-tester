package com.mtv.based.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MtvBackendApplication {
	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			runApplication<MtvBackendApplication>(*args)
		}
	}
}
