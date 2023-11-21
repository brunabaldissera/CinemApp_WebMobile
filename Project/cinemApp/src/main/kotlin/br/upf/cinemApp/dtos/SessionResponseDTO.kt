package br.upf.cinemApp.dtos

import br.upf.cinemApp.model.StatusEvento
import br.upf.cinemApp.model.Tickets
import java.time.LocalDate
import java.time.LocalDateTime

data class SessionResponseDTO(
        val id: Long?,
        val filmname: String,
        val data: LocalDate,
        val description : String,
        val status: StatusEvento,
        val inscritos: List<TicketResponseDTO>
)
