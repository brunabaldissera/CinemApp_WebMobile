package br.upf.cinemApp.repository

import br.upf.cinemApp.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {
    fun  findByEmail(email: String): UserDetails
}