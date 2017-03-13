package com.sevenpeakssoftware.fott.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.rxbinding.support.v4.widget.RxSwipeRefreshLayout;
import com.sevenpeakssoftware.fott.R;
import com.sevenpeakssoftware.fott.databinding.FragmentFeedBinding;
import com.sevenpeakssoftware.fott.models.Article;
import com.sevenpeakssoftware.fott.ui.State;
import com.sevenpeakssoftware.fott.ui.adapter.FeedAdapter;
import com.sevenpeakssoftware.fott.ui.base.BaseFragment;
import com.sevenpeakssoftware.fott.ui.custom.LayoutDivider;
import com.sevenpeakssoftware.fott.viewmodel.fragment.FeedViewModel;

import java.util.List;

/**
 * Created by razir on 1/3/2017.
 */

public class FeedFragment extends BaseFragment implements FeedViewModel.ActionListener, FeedAdapter.ItemClickListener {

    FragmentFeedBinding binding;
    FeedViewModel viewModel;
    FeedAdapter feedAdapter;

    public static FeedFragment newInstance() {
        Bundle args = new Bundle();
        FeedFragment fragment = new FeedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFeedBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.fott_feed);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        feedAdapter = new FeedAdapter(getContext(), this);
        binding.recyclerView.setAdapter(feedAdapter);
        viewModel = new FeedViewModel(getContext(), this);
        setBaseViewModel(viewModel);
        binding.setViewModel(viewModel);
        RxSwipeRefreshLayout.refreshes(binding.swipeRefresh).subscribe(aVoid -> {
            viewModel.loadFromNetwork(false);
        });
        binding.recyclerView.addItemDecoration(new LayoutDivider(getContext(), R.layout.divider_layout));
        viewModel.load();
    }

    @Override
    public void onDataLoaded(List<Article> data) {
        binding.swipeRefresh.setRefreshing(false);
        feedAdapter.setData(data);
    }

    @Override
    public void onLoadError() {
        binding.swipeRefresh.setRefreshing(false);
        if(viewModel.getStateInt()== State.STATE_LOADED) {
            Snackbar snackbar = Snackbar.make(binding.getRoot(), R.string.feed_problems_while_loading, Snackbar.LENGTH_SHORT);
            snackbar.show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding.swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onItemClick(Article article) {
        //Show details
    }
}
