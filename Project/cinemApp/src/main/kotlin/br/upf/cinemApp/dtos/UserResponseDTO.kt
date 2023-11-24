package br.upf.cinemApp.dtos

data class UserResponseDTO(val id: Long? = null,
                           val nome: String,
                           val cidade: String,
                           val telefone: String,
                           val cpf: String,
)
