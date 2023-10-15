package br.upf.cinemApp.converters

import br.upf.cinemApp.dtos.SessionDTO
import br.upf.cinemApp.dtos.SessionResponseDTO
import br.upf.cinemApp.model.Sessions
import org.springframework.stereotype.Component

@Component
class SessionConverter (
    private val ticketConverter: TicketConverter,
    private val userConverter: UserConverter
){
    fun toSession(dto: SessionDTO): Sessions{
        return Sessions(
                filmname = dto.filmname,
                data = dto.data,
                initdata = dto.initdata,
                enddata = dto.enddata,
                description = dto.description,
                status = dto.status,
                inscritos = listOf()
        )
    }
    fun toSessionResponseDTO(session: Sessions): SessionResponseDTO{
        val inscritosDTOs = session.inscritos.map { ticketConverter.toTicketResponseDTO(it)}
        return SessionResponseDTO(
                id = session.id,
                filmname = session.filmname,
                data = session.data,
                initdata = session.initdata,
                enddata = session.enddata,
                description = session.description,
                status = session.status,
                inscritos = inscritosDTOs
        )
    }
}