package rustam.urazov.data_storage

import androidx.room.Database
import androidx.room.RoomDatabase
import rustam.urazov.data_storage.dao.UsersDao
import rustam.urazov.data_storage.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class UsersDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "UsersDb"
    }

    abstract fun getUsersDao(): UsersDao
}