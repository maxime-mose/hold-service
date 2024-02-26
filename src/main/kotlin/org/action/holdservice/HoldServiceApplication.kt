package org.action.holdservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HoldServiceApplication

fun main(args: Array<String>) {
    runApplication<HoldServiceApplication>(*args)
}
