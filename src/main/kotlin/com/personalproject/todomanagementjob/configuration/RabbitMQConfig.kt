package com.personalproject.todomanagementjob.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class RabbitMQConfig {

    @Value("\${spring.rabbitmq.addresses}")
    private lateinit var addresses: String

    @Bean
    fun jsonMessageConverter(): MessageConverter? {
        val objectMapper = ObjectMapper()
        return Jackson2JsonMessageConverter(objectMapper)
    }

    @Bean
    fun connectionFactory(): ConnectionFactory? {
        val connectionFactory = CachingConnectionFactory()
        connectionFactory.setUri(addresses)
        return connectionFactory
    }

    @Bean
    fun rabbitAmqpTemplate(connectionFactory: ConnectionFactory): AmqpTemplate? {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        jsonMessageConverter()?.let { rabbitTemplate.messageConverter = it }
        rabbitTemplate.setUseDirectReplyToContainer(false)
        return rabbitTemplate
    }
}
