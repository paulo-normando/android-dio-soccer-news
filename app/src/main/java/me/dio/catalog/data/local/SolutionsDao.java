package me.dio.catalog.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import me.dio.catalog.domain.Solutions;

@Dao
public interface SolutionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(Solutions solutions);

    @Query("SELECT * FROM Solutions WHERE favorite = 1")
    LiveData<List<Solutions>> loadFavoriteNews();
}
