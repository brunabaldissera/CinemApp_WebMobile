package br.upf.cinemApp.converters

import br.upf.cinemApp.dtos.UserDTO
import br.upf.cinemApp.dtos.UserResponseDTO
import br.upf.cinemApp.model.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Component
class UserConverter {
    fun toUserResponseDTO(user : User): UserResponseDTO {
        return UserResponseDTO(
            id = user.id,
            nome = user.nome,
            cidade = user.cidade,
            telefone = user.telefone,
            cpf = user.cpf,
            email = user.email
        )
    }
    fun toUser(dto: UserDTO): User {
        return User(
            nome = dto.nome,
            cidade = dto.cidade,
            telefone = dto.telefone,
            cpf = dto.cpf,
            email = dto.email,
            senha = BCryptPasswordEncoder().encode(dto.senha)
        )
    }
}