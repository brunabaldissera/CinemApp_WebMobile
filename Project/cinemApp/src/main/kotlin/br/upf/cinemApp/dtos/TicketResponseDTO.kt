package br.upf.cinemApp.dtos

import br.upf.cinemApp.model.Sessions
import br.upf.cinemApp.model.User
import java.time.LocalDateTime

data class TicketResponseDTO(
    val id: Long?,
    val data: LocalDateTime,
    val usuario_id: Long?,
    val sessao_id: Long?
)