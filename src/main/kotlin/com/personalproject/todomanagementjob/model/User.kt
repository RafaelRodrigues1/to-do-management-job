package com.personalproject.todomanagementjob.model

import lombok.Data
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Data
class User (
    var id: UUID,
    val name: String,
    val registration: String,
    val email: String,
    val registerDate: Date,
    val status: UserStatus,
)

enum class UserStatus {
    ATIVO, INATIVO, PENDENTE;
}
