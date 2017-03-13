package com.sevenpeakssoftware.fott.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sevenpeakssoftware.fott.databinding.ItemFeedBinding;
import com.sevenpeakssoftware.fott.models.Article;
import com.sevenpeakssoftware.fott.viewmodel.item.FeedItemViewModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.NO_POSITION;

/**
 * Created by razir on 1/3/2017.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.Holder> {
    private List<Article> data = new ArrayList<>();
    private WeakReference<LayoutInflater> inflater;
    private WeakReference<ItemClickListener> listener;

    public FeedAdapter(Context context, ItemClickListener listener) {
        this.inflater = new WeakReference<>(LayoutInflater.from(context));
        this.listener = new WeakReference<>(listener);
    }

    public interface ItemClickListener {
        void onItemClick(Article article);
    }

    public void setData(List<Article> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemFeedBinding binding = ItemFeedBinding.inflate(inflater.get(), parent, false);
        final Holder holder = new Holder(binding);
        binding.root.setOnClickListener(view -> {
            int pos = holder.getAdapterPosition();
            if (pos != NO_POSITION) {
                if (listener != null && listener.get() != null) {
                    listener.get().onItemClick(data.get(pos));
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        ItemFeedBinding binding;

        Holder(ItemFeedBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Article article) {
            if (binding.getViewModel() == null) {
                binding.setViewModel(new FeedItemViewModel(itemView.getContext(), article));
            } else {
                binding.getViewModel().setArticle(article);
            }
            binding.executePendingBindings();
        }
    }
}
