package rustam.urazov.data_storage

import androidx.room.RoomDatabase
import rustam.urazov.data_storage.dao.UsersDao

abstract class UsersDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "UsersDb"
    }

    abstract fun getUsersDao(): UsersDao
}