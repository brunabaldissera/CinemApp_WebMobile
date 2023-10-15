package br.upf.cinemApp.converters

import br.upf.cinemApp.dtos.TicketDTO
import br.upf.cinemApp.dtos.TicketResponseDTO
import br.upf.cinemApp.model.Sessions
import br.upf.cinemApp.model.Tickets
import br.upf.cinemApp.model.User
import br.upf.cinemApp.repository.SessionRepository
import br.upf.cinemApp.repository.UserRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Component

@Component
class TicketConverter (
    private val sessionRepository: SessionRepository,
    private val userRepository: UserRepository
    ) {
        fun toTicket(dto: TicketDTO): Tickets {
            val sessao = sessionRepository.findById(dto.sessao_id)
                .orElseThrow { EntityNotFoundException("Sessão não encontrada!") }

            val user = userRepository.findById(dto.usuario_id)
                .orElseThrow { EntityNotFoundException("Usuário não encontrado!") }

            return Tickets(
                sessao_id = sessao,
                usuario_id = user,
                data = dto.data
            )
        }

    fun toTicketResponseDTO(tickets: Tickets): TicketResponseDTO {
        return TicketResponseDTO(
            id = tickets.id,
            data = tickets.data,
            sessao_id = tickets.sessao_id.id,
            usuario_id = tickets.usuario_id.id
        )
    }
}
