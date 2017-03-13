package com.sevenpeakssoftware.fott.viewmodel.base;

import android.content.Context;

import java.lang.ref.WeakReference;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by razir on 1/3/2017.
 */

public abstract class BaseViewModel {

    protected WeakReference<Context> context;
    protected CompositeSubscription subscription = new CompositeSubscription();

    public BaseViewModel(Context context) {
        this.context = new WeakReference<>(context);
    }

    public void destroy() {
        context = null;
        subscription.unsubscribe();
    }

    protected Context getContext() {
        return context.get();
    }


}
