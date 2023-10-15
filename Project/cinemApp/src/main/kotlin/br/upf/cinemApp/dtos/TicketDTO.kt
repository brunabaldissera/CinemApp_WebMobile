package br.upf.cinemApp.dtos

import br.upf.cinemApp.model.Sessions
import br.upf.cinemApp.model.User
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.NotNull
import java.time.LocalDate
import java.time.LocalDateTime

data class TicketDTO (
        @field:NotNull(message = "Ticket sempre deve ter uma data")
        val data: LocalDateTime,
        @field:NotNull(message = "Deve haver uma sessão associada")
        val sessao_id: Long,
        @field:NotNull(message = "Deve haver um usuário associado")
        val usuario_id: Long
)