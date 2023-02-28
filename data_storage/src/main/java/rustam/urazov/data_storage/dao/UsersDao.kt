package rustam.urazov.data_storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import rustam.urazov.data_storage.entity.UserEntity

@Dao
interface UsersDao {

    @Query("SELECT * FROM ${UserEntity.TABLE_NAME} WHERE email LIKE :email")
    suspend fun selectByEmail(email: String): List<UserEntity>

    @Insert
    suspend fun insertUser(userEntity: UserEntity)

}