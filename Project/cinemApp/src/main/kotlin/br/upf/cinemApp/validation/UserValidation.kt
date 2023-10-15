package br.upf.cinemApp.validation

import br.upf.cinemApp.dtos.UserDTO
import br.upf.cinemApp.exceptions.ValidationException
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import java.util.regex.Pattern

class UserValidation {
    companion object {
        private val EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$"

        fun validate(user: UserDTO) {
            validateNome(user.nome)
            validateSenha(user.senha)
            validateEmail(user.email)
            validateCidade(user.cidade)
            validateTelefone(user.telefone)
            validateCpf(user.cpf)
        }

        private fun validateNome(nome: String?) {
            if (nome.isNullOrBlank()) {
                throw ValidationException("O nome é obrigatório")
            }
        }

        private fun validateSenha(senha: String) {
            if (senha.isNullOrBlank()) {
                throw ValidationException("A senha é obrigatória")
            }
        }

        private fun validateEmail(@NotBlank email: String) {
            val pattern = Pattern.compile(EMAIL_REGEX)
            val matcher = pattern.matcher(email)

            if (!matcher.matches()) {
                throw ValidationException("O email deve ser válido")
            }
        }

        private fun validateCidade(cidade: String) {
            if (cidade.isNullOrBlank()) {
                throw ValidationException("A cidade é obrigatória")
            }
        }

        private fun validateTelefone(telefone: String) {
            if (telefone.isNullOrBlank()) {
                throw ValidationException("O telefone é obrigatório")
            }
        }

        private fun validateCpf(cpf: String) {
            if (cpf.isNullOrBlank()) {
                throw ValidationException("O CPF é obrigatório")
            }
        }
    }
}