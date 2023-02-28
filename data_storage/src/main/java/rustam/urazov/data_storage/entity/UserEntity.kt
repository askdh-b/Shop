package rustam.urazov.data_storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import rustam.urazov.core.extension.empty

@Entity
data class UserEntity(
    @PrimaryKey val userId: Int = 0,
    val firstName: String = String.empty(),
    val lastName: String = String.empty(),
    val email: String = String.empty(),
    val password: String = String.empty()
) {

    companion object {
        const val TABLE_NAME = "Users"
    }

}