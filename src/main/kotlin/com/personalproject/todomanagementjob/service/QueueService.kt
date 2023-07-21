package com.personalproject.todomanagementjob.service

import com.personalproject.todomanagementjob.model.Email
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class QueueService {

    @Autowired
    private lateinit var rabbitTemplate: AmqpTemplate

    fun enviaParaFila(messageToSend: Any, queueName: String) {
        rabbitTemplate.convertAndSend(queueName, messageToSend)
    }
}
