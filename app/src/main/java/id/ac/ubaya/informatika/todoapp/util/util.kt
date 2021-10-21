package id.ac.ubaya.informatika.todoapp.util

import android.content.Context
import androidx.room.Room
import id.ac.ubaya.informatika.todoapp.model.TodoDatabase

val DB_NAME = "tododb"

fun buildDB(context: Context):TodoDatabase {
    val db = Room.databaseBuilder(context,
            TodoDatabase::class.java, DB_NAME).build()
    return db
}
