package com.personalproject.todomanagementjob.scheduler

import com.personalproject.todomanagementjob.business.EmailBusiness
import com.personalproject.todomanagementjob.mapper.UserMapper
import com.personalproject.todomanagementjob.model.Email
import com.personalproject.todomanagementjob.model.User
import com.personalproject.todomanagementjob.model.UserStatus
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class UserScheduler {

    @Autowired
    private lateinit var userMapper: UserMapper

    @Autowired
    private lateinit var emailBusiness: EmailBusiness

    @Autowired
    private lateinit var rabbitTemplate: AmqpTemplate

    @Value("\${queue.envio-solicitacao-confirmacao-cadastro}")
    private lateinit var envioSolicitacaoConfirmacaoCadastroQueue: String

    @Value("\${queue.envio-confirmacao-cadastro}")
    private lateinit var envioConfirmacaoCadastroQueue: String

    @Scheduled(fixedDelay = 30, timeUnit = TimeUnit.SECONDS)
    fun getusers() {
        val listusersPendentes: List<User> = userMapper.findAllUsersByStatus(UserStatus.PENDENTE)
        if(!listusersPendentes.isNullOrEmpty())
            listusersPendentes.forEach{ user -> processaEnvioSolicitacaoConfirmacaoUsuario(user) }
        else
            println("Não há usuários para solicitação de confirmação de cadastro!")
    }

    private fun processaEnvioSolicitacaoConfirmacaoUsuario(user: User) {
        val email: Email = emailBusiness.buildEmailSolicitacaoConfirmacaoCadastro()
        email.receiver = user
        enviaParaFilaEmail(email, envioSolicitacaoConfirmacaoCadastroQueue)
        userMapper.updateStatusUser(user.id.toString(), UserStatus.PROCESSANDO)
    }

    private fun enviaParaFilaEmail(email: Email, queueName: String) {
        rabbitTemplate.convertAndSend(queueName, email)
    }
}
