package br.upf.cinemApp.exceptions

import java.lang.RuntimeException

class NotFoundException(override val message : String) : RuntimeException() {
}