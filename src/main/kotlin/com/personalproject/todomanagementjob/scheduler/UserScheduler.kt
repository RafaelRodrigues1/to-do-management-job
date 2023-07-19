package com.personalproject.todomanagementjob.scheduler

import com.personalproject.todomanagementjob.mapper.UserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class UserScheduler {

    @Autowired
    private lateinit var userMapper: UserMapper

    @Scheduled(fixedDelay = 3, timeUnit = TimeUnit.SECONDS)
    fun getusers() {
        println(userMapper.selectAllUsers().stream().map{user -> user.name}.reduce{a, b -> "$a, $b"}.get())
    }
}
