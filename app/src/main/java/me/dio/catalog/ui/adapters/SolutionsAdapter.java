package me.dio.catalog.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import me.dio.catalog.R;
import me.dio.catalog.databinding.NewsItemBinding;
import me.dio.catalog.domain.Solutions;

public class SolutionsAdapter extends RecyclerView.Adapter<SolutionsAdapter.ViewHolder> {

    private final List<Solutions> solutions;
    private final FavoriteListener favoriteListener;

    public SolutionsAdapter(List<Solutions> solutions, FavoriteListener favoriteListener) {
        this.solutions = solutions;
        this.favoriteListener = favoriteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        NewsItemBinding binding = NewsItemBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();

        Solutions solutions = this.solutions.get(position);
        holder.binding.tvTitle.setText(solutions.title);
        holder.binding.tvDescription.setText(solutions.description);
        Picasso.get().load(solutions.image).fit().into(holder.binding.ivThumbnail);
        // Implementação da funcionalidade de "Abrir Link":
        holder.binding.btOpenLink.setOnClickListener(view -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(solutions.link));
            context.startActivity(i);
        });
        // Implementação da funcionalidade de "Compartilhar":
        holder.binding.ivShare.setOnClickListener(view -> {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_TEXT, solutions.link);
            context.startActivity(Intent.createChooser(i, "Share"));
        });
        // Implementação da funcionalidade de "Favoritar" (o evento será instanciado pelo Fragment):
        holder.binding.ivFavorite.setOnClickListener(view -> {
            solutions.favorite = !solutions.favorite;
            this.favoriteListener.onFavorite(solutions);
            notifyItemChanged(position);
        });
        int favoriteColor = solutions.favorite ? R.color.favorite_active : R.color.favorite_inactive;
        holder.binding.ivFavorite.setColorFilter(context.getResources().getColor(favoriteColor));
    }

    @Override
    public int getItemCount() {
        return this.solutions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final NewsItemBinding binding;

        public ViewHolder(NewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface FavoriteListener {
        void onFavorite(Solutions solutions);
    }
}
