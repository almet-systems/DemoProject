package com.sevenpeakssoftware.fott.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sevenpeakssoftware.fott.viewmodel.base.BaseViewModel;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by razir on 1/3/2017.
 */

public abstract class BaseFragment extends Fragment {
    protected BaseViewModel baseViewModel;
    protected CompositeSubscription subscription = new CompositeSubscription();

    @Nullable
    @Override
    public abstract View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (baseViewModel != null)
            baseViewModel.destroy();

        if (subscription != null)
            subscription.unsubscribe();
    }

    protected void setBaseViewModel(BaseViewModel model) {
        this.baseViewModel = model;
    }

}
