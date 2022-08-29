package me.dio.catalog.ui.favorites;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import me.dio.catalog.data.SolutionsRepository;
import me.dio.catalog.domain.Solutions;

public class FavoritesViewModel extends ViewModel {

    public FavoritesViewModel() {

    }

    public LiveData<List<Solutions>> loadFavoriteNews() {
        return SolutionsRepository.getInstance().getLocalDb().newsDao().loadFavoriteNews();
    }

    public void saveNews(Solutions solutions) {
        AsyncTask.execute(() -> SolutionsRepository.getInstance().getLocalDb().newsDao().save(solutions));
    }

}