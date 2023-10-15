package br.upf.cinemApp.service

import br.upf.cinemApp.converters.UserConverter
import br.upf.cinemApp.dtos.UserDTO
import br.upf.cinemApp.dtos.UserResponseDTO
import br.upf.cinemApp.repository.UserRepository
import br.upf.cinemApp.validation.UserValidation
import org.springframework.stereotype.Service


@Service
class UserService(
    private val repository: UserRepository,
    private val converter: UserConverter
) {
    fun listar(): List<UserResponseDTO>{
        return repository.findAll()
            .map(converter::toUserResponseDTO)
    }
    fun searchForId(id: Long): UserResponseDTO {
        val usuario = repository.findById(id)
            .orElseThrow { br.upf.cinemApp.exceptions.NotFoundException("Usuário não encontrado!") }
        return converter.toUserResponseDTO(usuario)
    }
    fun register(dto: UserDTO): UserResponseDTO {
        UserValidation.validate(dto)
        return converter.toUserResponseDTO(
            repository.save(converter.toUser(dto)))
    }
    fun update(id: Long, dto: UserDTO): UserResponseDTO {
        val usuario = repository.findById(id)
            .orElseThrow { br.upf.cinemApp.exceptions.NotFoundException("Usuário não encontrado!") }
            .copy(
                nome = dto.nome,
                cidade = dto.cidade,
                telefone = dto.telefone,
                cpf = dto.cpf
            )
        return converter.toUserResponseDTO(repository.save(usuario))
    }
    fun delete(id: Long) {
        repository.deleteById(id)
    }
}