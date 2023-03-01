package rustam.urazov.data_storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import rustam.urazov.data_storage.entity.UserEntity

@Dao
interface UsersDao {

    @Query("SELECT * FROM ${UserEntity.TABLE_NAME} WHERE email LIKE :email OR firstName LIKE :firstName")
    suspend fun selectByEmailOrFirstName(email: String, firstName: String): List<UserEntity>

    @Query("SELECT * FROM ${UserEntity.TABLE_NAME} WHERE firstName LIKE :firstName AND password LIKE :password")
    suspend fun auth(firstName: String, password: String): List<UserEntity>

    @Insert
    suspend fun insertUser(userEntity: UserEntity)

}