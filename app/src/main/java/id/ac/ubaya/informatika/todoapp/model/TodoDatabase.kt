package id.ac.ubaya.informatika.todoapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.ac.ubaya.informatika.todoapp.util.MIGRATION_1_2

@Database(entities = arrayOf(Todo::class), version = 2)
abstract class TodoDatabase:RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        @Volatile private var instance: TodoDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context:Context) = Room.databaseBuilder(
            context.applicationContext,
            TodoDatabase::class.java,
            "tododb"
        )
                .addMigrations(MIGRATION_1_2)
                .build()

        operator fun invoke(context:Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

    }
}