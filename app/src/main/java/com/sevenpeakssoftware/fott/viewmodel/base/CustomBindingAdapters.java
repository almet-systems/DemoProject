package com.sevenpeakssoftware.fott.viewmodel.base;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by razir on 1/3/2017.
 */

public class CustomBindingAdapters {

    @BindingAdapter({"imageUrlCrop"})
    public static void loadImage(ImageView view, String link) {
        Picasso.with(view.getContext())
                .load(link)
                .fit()
                .centerCrop()
                .into(view);
    }


}
