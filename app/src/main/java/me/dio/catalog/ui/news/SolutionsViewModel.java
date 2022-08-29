package me.dio.catalog.ui.news;

import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import me.dio.catalog.data.SolutionsRepository;
import me.dio.catalog.domain.Solutions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SolutionsViewModel extends ViewModel {

    public enum State {
        DOING, DONE, ERROR;
    }

    private final MutableLiveData<List<Solutions>> news = new MutableLiveData<>();
    private final MutableLiveData<State> state = new MutableLiveData<>();

    public SolutionsViewModel() {
        this.findNews();
    }

    public void findNews() {
        state.setValue(State.DOING);
        SolutionsRepository.getInstance().getRemoteApi().getNews().enqueue(new Callback<List<Solutions>>() {
            @Override
            public void onResponse(@NonNull Call<List<Solutions>> call, @NonNull Response<List<Solutions>> response) {
                if (response.isSuccessful()) {
                    news.setValue(response.body());
                    state.setValue(State.DONE);
                } else {
                    state.setValue(State.ERROR);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Solutions>> call, Throwable error) {
                error.printStackTrace();
                state.setValue(State.ERROR);
            }
        });
    }

    public void saveNews(Solutions solutions) {
        AsyncTask.execute(() -> SolutionsRepository.getInstance().getLocalDb().newsDao().save(solutions));
    }

    public LiveData<List<Solutions>> getNews() {
        return this.news;
    }

    public LiveData<State> getState() {
        return this.state;
    }
}