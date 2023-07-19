package com.personalproject.todomanagementjob.business

import com.personalproject.todomanagementjob.mapper.ParameterMapper
import com.personalproject.todomanagementjob.model.Email
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EmailBusiness {

    @Autowired
    private lateinit var parameterMapper: ParameterMapper

    private val TEXTO_EMAIL_SOLICITACAO_CONFIRMACAO: String = "TEXTO_EMAIL_SOLICITACAO_CONFIRMACAO"

    private val titleSolicitacaoConfirmacaoCadastro: String = "Confirme seu Endereço de E-mail"

    private val TEXTO_EMAIL_CONFIRMACAO: String = "TEXTO_EMAIL_CONFIRMACAO"

    private val titleConfirmacaoCadastro: String = "Confirme seu Endereço de E-mail"

    fun buildEmailSolicitacaoConfirmacaoCadastro(): Email {
        return buildEmail(
            titleSolicitacaoConfirmacaoCadastro,
            parameterMapper.findParameterValueByName(TEXTO_EMAIL_SOLICITACAO_CONFIRMACAO)
        )
    }

    fun buildEmailConfirmacaoCadastro(): Email {
        return buildEmail(
            titleConfirmacaoCadastro,
            parameterMapper.findParameterValueByName(TEXTO_EMAIL_CONFIRMACAO)
        )
    }

    private fun buildEmail(title: String, text: String): Email {
        return Email(
            title = title,
            text = text
        )
    }
}
