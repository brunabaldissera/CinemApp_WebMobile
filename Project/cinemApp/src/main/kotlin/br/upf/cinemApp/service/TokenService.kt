package br.upf.cinemApp.service

import br.upf.cinemApp.model.User
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.time.LocalDateTime
import java.time.ZoneOffset

@Service
class TokenService {
    @Value("\${api.security.token.secret}")
    private lateinit var secret: String

    fun generateToken(usuario: User): String{
        return try{
            val algorithm = Algorithm.HMAC256(secret)
            JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(usuario.email)
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm)
        } catch (exception: JWTCreationException){
            throw RuntimeException("Erro na geração do token!", exception)
        }
    }
    fun validateToken(token: String) :String{
        return try {
            val algorithm = Algorithm.HMAC256( secret)
            JWT.require(algorithm)
                    .withIssuer( "auth-api")
                    .build()
                    .verify(token)
                    .subject
        } catch (exception: JWTVerificationException) {
            ""
        }

    }
    private fun genExpirationDate() =
            LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00")) // Para sincronizar com o UTC horário de Brasília
}