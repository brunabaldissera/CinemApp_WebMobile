package br.upf.cinemApp.service

import br.upf.cinemApp.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthorizationService (
    val repositorio: UserRepository
    ) : UserDetailsService {

        override fun loadUserByUsername(email: String) = repositorio.findByEmail(email)
    }