package com.example.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.soccernews.domain.News;

import java.util.ArrayList;
import java.util.List;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news;

    public NewsViewModel() {
        this.news = new MutableLiveData<>();

        //TODO remover mock de noticias,

        List<News> news = new ArrayList<>();
        news.add(new News("Paysandu vence Remo no domingo","Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit..."));
        news.add(new News("Paysandu joga no sábado","Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit..."));
        news.add(new News("Copa do mundo feminina inicia","Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit..."));

        this.news.setValue(news);
    }

    public LiveData<List<News>> getNews() {
        return news;
    }
}