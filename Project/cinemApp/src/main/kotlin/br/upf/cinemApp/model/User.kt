package br.upf.cinemApp.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
data class User(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val nome: String,
        val senha: String,
        val email: String,
        @Enumerated(value= EnumType.STRING)
        val role: UserRole =  UserRole.USER,
        val cidade: String,
        val telefone: String,
        val cpf: String
): UserDetails{
        override fun getAuthorities(): MutableList<SimpleGrantedAuthority> =
                if (role == UserRole.ADMIN) mutableListOf(
                        SimpleGrantedAuthority("ROLE_ADMIN"),
                        SimpleGrantedAuthority("ROLE_USER")
                )
                else mutableListOf(SimpleGrantedAuthority("ROLE_USER"))
        override fun getPassword() = senha
        override fun getUsername()= email
        override fun isAccountNonExpired() = true
        override fun isAccountNonLocked() = true
        override fun isCredentialsNonExpired() = true
        override fun isEnabled() = true
}
