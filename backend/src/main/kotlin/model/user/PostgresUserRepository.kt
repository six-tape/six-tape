package cl.sixtape.model.user

class PostgresUserRepository : UserRepository {
    override suspend fun allUsers(): List<User> {
        TODO("Not yet implemented")
    }
}