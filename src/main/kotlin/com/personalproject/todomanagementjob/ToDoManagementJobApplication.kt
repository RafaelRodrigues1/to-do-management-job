package com.personalproject.todomanagementjob

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class ToDoManagementJobApplication

fun main(args: Array<String>) {
    runApplication<ToDoManagementJobApplication>(*args)
}
