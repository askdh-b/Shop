package rustam.urazov.data_storage.dao

import androidx.room.Dao
import androidx.room.Insert
import rustam.urazov.data_storage.entity.UserEntity

@Dao
interface UsersDao {

    @Insert
    suspend fun insertUser(userEntity: UserEntity)

}