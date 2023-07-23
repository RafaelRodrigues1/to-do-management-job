package com.personalproject.todomanagementjob.scheduler

import com.personalproject.todomanagementjob.business.`interface`.EmailBusiness
import com.personalproject.todomanagementjob.mapper.UserMapper
import com.personalproject.todomanagementjob.model.Email
import com.personalproject.todomanagementjob.model.User
import com.personalproject.todomanagementjob.model.UserStatus
import com.personalproject.todomanagementjob.service.QueueService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class UserSolicitacaoConfirmacaoScheduler {

    @Autowired
    private lateinit var userMapper: UserMapper

    @Autowired
    private lateinit var emailBusiness: EmailBusiness<User>

    @Autowired
    private lateinit var queueService: QueueService

    @Value("\${queue.envio-email}")
    private lateinit var envioEmailQueue: String

    @Scheduled(fixedDelay = 5, timeUnit = TimeUnit.SECONDS)
    fun enviaSolicitacaoConfirmacaoUserPendente() {
        val userPendente: User? = userMapper.findOneUserByStatus(UserStatus.PENDENTE)
        userPendente?.let {
            processaEnvioSolicitacaoConfirmacaoUsuario(it)
        } ?: println("Não há usuários para solicitação de confirmação de cadastro!")
    }

    private fun processaEnvioSolicitacaoConfirmacaoUsuario(user: User) {
        val email: Email = emailBusiness.buildEmail(user)
        queueService.enviaParaFila(email, envioEmailQueue)
        userMapper.updateStatusUser(user.id.toString(), UserStatus.PROCESSANDO)
    }
}
