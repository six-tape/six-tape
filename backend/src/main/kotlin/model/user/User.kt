package cl.sixtape.model.user

import cl.sixtape.serializers.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

/**
 * A user
 *
 * @property username the username of the user
 * @property password the hashed password of the user
 * @property email the email of the user
 */
@Serializable
data class User (
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val username: String,
    val password: String,
    val email: String,
){
    override fun toString(): String {
        return username
    }
}