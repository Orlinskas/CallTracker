package com.sandiplus.calltracker.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sandiplus.calltracker.data.model.DefaultModel

@Database(
    entities = [
        DefaultModel::class
    ],
    version = 1,
    exportSchema = false
)
//@TypeConverters(TypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    // dao list
}
