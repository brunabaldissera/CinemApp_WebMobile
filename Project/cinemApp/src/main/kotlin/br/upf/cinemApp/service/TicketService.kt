package br.upf.cinemApp.service

import br.upf.cinemApp.converters.TicketConverter
import br.upf.cinemApp.dtos.*
import br.upf.cinemApp.exceptions.ClosedSessionException
import br.upf.cinemApp.exceptions.NotFoundException
import br.upf.cinemApp.model.StatusEvento
import br.upf.cinemApp.model.Tickets
import br.upf.cinemApp.repository.SessionRepository
import br.upf.cinemApp.repository.TicketRepository
import br.upf.cinemApp.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class TicketService (
    private val repository: TicketRepository,
    private val converter: TicketConverter,
    private val userRepository: UserRepository,
    private val sessionRepository: SessionRepository
){
    fun listar(): List<TicketResponseDTO>{
        return repository.findAll()
            .map(converter::toTicketResponseDTO)
    }

    fun searchForId(id: Long): TicketResponseDTO {
        val ticket = repository.findById(id)
            .orElseThrow { br.upf.cinemApp.exceptions.NotFoundException("Ticket não encontrado!") }
        return converter.toTicketResponseDTO(ticket)
    }

    fun register(dto: TicketDTO): TicketResponseDTO {
        val session = sessionRepository.findById(dto.sessao_id)
            .orElseThrow { NotFoundException("Sessão não encontrada!") }

        if (session.status == StatusEvento.ENCERRADO || session.status == StatusEvento.CANCELADO) {
            throw ClosedSessionException("Não é possível comprar um ticket para uma sessão fechada ou cancelada.")
        }
        return converter.toTicketResponseDTO(
            repository.save(converter.toTicket((dto)))
        )
    }

    fun update(id: Long, dto: TicketDTO): TicketResponseDTO {
        val ticket = repository.findById(id)
            .orElseThrow { br.upf.cinemApp.exceptions.NotFoundException("Ticket não encontrado!") }
        val session = sessionRepository.findById(dto.sessao_id)
            .orElseThrow { NotFoundException("Sessão não encontrada!") }

        val updatedTicket = Tickets(
            id = ticket.id,
            data = dto.data,
            sessao_id = sessionRepository.findById(dto.sessao_id)
                .orElseThrow { NotFoundException("Sessão não encontrada!") },
            usuario_id = userRepository.findById(dto.usuario_id)
                .orElseThrow { NotFoundException("Usuário não encontrado!") }
        )
        if (session.status == StatusEvento.ENCERRADO || session.status == StatusEvento.CANCELADO) {
            throw ClosedSessionException("Não é possível comprar um ticket para uma sessão fechada ou cancelada.")
        }
        val savedTicket = repository.save(updatedTicket)
        return converter.toTicketResponseDTO(savedTicket)
    }

    fun delete(id: Long) {
        repository.deleteById(id)
    }
}