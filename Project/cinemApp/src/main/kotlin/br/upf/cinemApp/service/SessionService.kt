package br.upf.cinemApp.service

import br.upf.cinemApp.converters.SessionConverter
import br.upf.cinemApp.dtos.SessionDTO
import br.upf.cinemApp.dtos.SessionResponseDTO
import br.upf.cinemApp.exceptions.NotFoundException
import br.upf.cinemApp.exceptions.SessionDataException
import br.upf.cinemApp.repository.SessionRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class SessionService (private val repository: SessionRepository,
    private val converter: SessionConverter){
    fun listar(
        nomeSessao: String?,
        paginacao: Pageable): Page<SessionResponseDTO> {
        val sessao = if (nomeSessao == null) {
            repository.findAll(paginacao)
        } else {
            repository.findByFilmname(nomeSessao, paginacao)
        }
        return sessao
            .map(converter::toSessionResponseDTO)
    }
    fun searchForId(id: Long): SessionResponseDTO{
        val sessao = repository.findById(id)
            .orElseThrow { NotFoundException("Evento não encontrado!")}
        return converter.toSessionResponseDTO(sessao)
    }
    fun register(dto: SessionDTO): SessionResponseDTO{
        if (dto.enddata <= dto.initdata) {
            throw SessionDataException("A data final deve ser maior que a data inicial.")
        }
        return converter.toSessionResponseDTO(
            repository.save(converter.toSession((dto)))
        )
    }
    fun update(id: Long, dto: SessionDTO): SessionResponseDTO{
        val sessao = repository.findById(id)
            .orElseThrow { NotFoundException("Evento não encontrado!")}
            .copy(
                filmname = dto.filmname,
                data = dto.data,
                initdata = dto.initdata,
                enddata = dto.enddata,
                description = dto.description,
                status = dto.status
            )
            if (dto.enddata <= dto.initdata) {
                throw SessionDataException("A data final deve ser maior que a data inicial.")
            }
        return converter.toSessionResponseDTO(repository.save(sessao))
    }
    fun deletar(id: Long) {
        repository.deleteById(id)
    }
}