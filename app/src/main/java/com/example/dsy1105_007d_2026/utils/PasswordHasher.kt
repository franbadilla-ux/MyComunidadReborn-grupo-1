package com.example.dsy1105_007d_2026.utils

import java.security.MessageDigest
import java.security.SecureRandom
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

object PasswordHasher {
    private const val ALGORITHM = "PBKDF2WithHmacSHA1"
    private const val ITERATIONS = 65_536
    private const val KEY_LENGTH_BITS = 256
    private const val SALT_BYTES = 16

    fun hash(password: String): String {
        val salt = ByteArray(SALT_BYTES).also { SecureRandom().nextBytes(it) }
        val hash = derive(password, salt, ITERATIONS)
        return "$ITERATIONS:${salt.toHex()}:${hash.toHex()}"
    }

    fun verify(password: String, stored: String): Boolean {
        val partes = stored.split(":")
        if (partes.size != 3) return false
        val iteraciones = partes[0].toIntOrNull() ?: return false
        val salt = partes[1].hexToBytes() ?: return false
        val esperado = partes[2].hexToBytes() ?: return false
        val calculado = derive(password, salt, iteraciones)
        return MessageDigest.isEqual(esperado, calculado)
    }

    private fun derive(password: String, salt: ByteArray, iteraciones: Int): ByteArray {
        val spec = PBEKeySpec(password.toCharArray(), salt, iteraciones, KEY_LENGTH_BITS)
        return SecretKeyFactory.getInstance(ALGORITHM).generateSecret(spec).encoded
    }

    private fun ByteArray.toHex(): String = joinToString("") { "%02x".format(it) }

    private fun String.hexToBytes(): ByteArray? {
        if (length % 2 != 0) return null
        val resultado = ByteArray(length / 2)
        for (i in resultado.indices) {
            resultado[i] = substring(i * 2, i * 2 + 2).toIntOrNull(16)?.toByte() ?: return null
        }
        return resultado
    }
}