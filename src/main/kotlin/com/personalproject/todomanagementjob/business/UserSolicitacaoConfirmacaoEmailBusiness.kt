package com.personalproject.todomanagementjob.business

import com.personalproject.todomanagementjob.business.`interface`.EmailBusiness
import com.personalproject.todomanagementjob.mapper.ParameterMapper
import com.personalproject.todomanagementjob.model.Email
import com.personalproject.todomanagementjob.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserSolicitacaoConfirmacaoEmailBusiness: EmailBusiness<User> {

    @Autowired
    private lateinit var parameterMapper: ParameterMapper

    override fun buildEmail(user: User): Email {
        var emailBody: String = parameterMapper.findParameterValueByName(EMAIL_BODY_PARAMETER_NAME)
        emailBody = setDadosInEmailBody(user, emailBody)
        return Email(
            title = EMAIL_TITLE,
            text = emailBody,
            receiver = user
        )
    }

    override fun setDadosInEmailBody(user: User, emailBody: String): String {
        var email: String = emailBody
        email = email.replace("{userName}", user.name)
        email = email.replace("{userRegistration}", user.registration)
        return email
    }

    override val EMAIL_BODY_PARAMETER_NAME: String = "TEXTO_EMAIL_SOLICITACAO_CONFIRMACAO"
    override val EMAIL_TITLE: String = "Confirme seu Endereço de E-mail"
}
