package com.sevenpeakssoftware.fott.viewmodel.fragment;

import android.content.Context;
import android.databinding.ObservableInt;

import com.sevenpeakssoftware.fott.api.ApiProvider;
import com.sevenpeakssoftware.fott.api.NetworkUtils;
import com.sevenpeakssoftware.fott.api.response.ApiResponse;
import com.sevenpeakssoftware.fott.models.Article;
import com.sevenpeakssoftware.fott.ui.State;
import com.sevenpeakssoftware.fott.viewmodel.base.BaseViewModel;

import java.lang.ref.WeakReference;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by razir on 1/3/2017.
 */

public class FeedViewModel extends BaseViewModel {

    public ObservableInt state = new ObservableInt();
    private WeakReference<ActionListener> listener;
    private boolean dataLoaded;

    public FeedViewModel(Context context, ActionListener listener) {
        super(context);
        this.listener = new WeakReference<>(listener);
    }

    public interface ActionListener {
        void onDataLoaded(List<Article> data);

        void onLoadError();
    }

    public int getStateInt() {
        return state.get();
    }

    public void load() {
        loadFromNetwork(false);
    }

    public void loadFromNetwork(boolean showError) {

        Subscriber<ApiResponse<List<Article>>> subscriber = new Subscriber<ApiResponse<List<Article>>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                if (showError) {
                    state.set(State.STATE_ERROR);
                }
                if (listener.get() != null) {
                    listener.get().onLoadError();
                }
            }

            @Override
            public void onNext(ApiResponse<List<Article>> wrapper) {
                state.set(State.STATE_LOADED);
                dataLoaded = true;
                if (listener != null && listener.get() != null) {
                    listener.get().onDataLoaded(wrapper.getData());
                }
            }
        };

        subscription.add(subscriber);
        Observable<ApiResponse<List<Article>>> observable = ApiProvider.getInstance().getApi().getFeed();
        if (NetworkUtils.isNetworkAvailable(getContext())) {
            if (!dataLoaded) {
                state.set(State.STATE_LOADING);
            }
            observable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscriber);

        } else {
            if (showError) {
                state.set(State.STATE_ERROR);
            }
            if (listener.get() != null) {
                listener.get().onLoadError();
            }
        }

    }


}
