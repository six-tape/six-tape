package cl.sixtape.model.user

import java.util.UUID

interface UserRepository {
    suspend fun allUsers(): List<User>
    suspend fun userByUsername(username: String): User?
    suspend fun userByEmail(email: String): User?
    suspend fun userById(id: UUID): User?
    suspend fun addUser(user: User)
    suspend fun removeUser(userId: UUID)
}