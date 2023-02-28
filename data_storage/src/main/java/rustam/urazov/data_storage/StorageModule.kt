package rustam.urazov.data_storage

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import rustam.urazov.data_storage.entity.UserEntity
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StorageModule {

    @Provides
    @Singleton
    fun provideUsersDatabase(@ApplicationContext context: Context): UsersDatabase =
        Room.databaseBuilder(context, UsersDatabase::class.java, UsersDatabase.DB_NAME)
            .allowMainThreadQueries()
            .build()

    @Provides
    @Singleton
    fun provideUsersDao(db: UsersDatabase) = db.getUsersDao()

    @Provides
    fun provideUserEntity() = UserEntity()

}