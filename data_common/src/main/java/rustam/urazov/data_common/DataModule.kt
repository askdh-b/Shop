package rustam.urazov.data_common

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import rustam.urazov.data_common.repository.UserRepository
import rustam.urazov.data_common.repository.UserRepositoryImpl
import rustam.urazov.data_storage.UsersDatabase
import rustam.urazov.data_storage.entity.UserEntity
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideUserRepository(userRepository: UserRepositoryImpl): UserRepository = userRepository

    @Provides
    @Singleton
    fun provideUsersDatabase(@ApplicationContext context: Context): UsersDatabase =
        Room.databaseBuilder(context, UsersDatabase::class.java, UsersDatabase.DB_NAME)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideUsersDao(db: UsersDatabase) = db.getUsersDao()

    @Provides
    fun provideUserEntity() = UserEntity()

}