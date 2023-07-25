package com.personalproject.todomanagementjob.business.`interface`

import com.personalproject.todomanagementjob.model.Email
import org.springframework.stereotype.Component

interface EmailBusiness<T> {

    fun buildEmail(emailContentObject: T): Email
    fun setDadosInEmailBody(emailContentObject: T, emailBody: String): String

    val EMAIL_BODY_PARAMETER_NAME: String
    val EMAIL_TITLE: String
}
