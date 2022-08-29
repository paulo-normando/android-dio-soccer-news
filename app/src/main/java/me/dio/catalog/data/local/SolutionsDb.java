package me.dio.catalog.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import me.dio.catalog.domain.Solutions;

@Database(entities = {Solutions.class}, version = 1)
public abstract class SolutionsDb extends RoomDatabase {
    public abstract SolutionsDao newsDao();
}
