package com.personalproject.todomanagementjob.model

import lombok.Builder

@Builder
class Email (
    val title: String,
    val text: String,
    val receiver: User? = null
)
