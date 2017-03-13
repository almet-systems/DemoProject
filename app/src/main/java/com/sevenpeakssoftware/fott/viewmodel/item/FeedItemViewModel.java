package com.sevenpeakssoftware.fott.viewmodel.item;

import android.content.Context;
import android.databinding.ObservableField;
import android.text.format.DateFormat;

import com.sevenpeakssoftware.fott.R;
import com.sevenpeakssoftware.fott.models.Article;
import com.sevenpeakssoftware.fott.viewmodel.base.BaseViewModel;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by razir on 1/4/2017.
 */

public class FeedItemViewModel extends BaseViewModel {
    private static DateTimeFormatter full24 = DateTimeFormat.forPattern("dd MMMM, hh:mm");
    private static DateTimeFormatter full12 = DateTimeFormat.forPattern("dd MMMM, HH:mm a");

    private static DateTimeFormatter short24 = DateTimeFormat.forPattern("hh:mm");
    private static DateTimeFormatter short12 = DateTimeFormat.forPattern("HH:mm a");

    public ObservableField<Article> article = new ObservableField<>();

    public FeedItemViewModel(Context context, Article article) {
        super(context);
        this.article.set(article);
    }

    public void setArticle(Article article) {
        this.article.set(article);
    }

    public String getDate(DateTime dateTime) {
        boolean format24 = DateFormat.is24HourFormat(getContext());

        DateTime todayStart = DateTime.now().withTime(0, 0, 0, 0);
        DateTime todayEnd = DateTime.now().plusDays(1).withTime(0, 0, 0, 0);

        if (dateTime.isAfter(todayStart) && dateTime.isBefore(todayEnd)) {
            if (format24) {
                return getContext().getString(R.string.feed_today, short24.print(dateTime));
            } else {
                return getContext().getString(R.string.feed_today, short12.print(dateTime));
            }
        } else {
            if (format24) {
                return full24.print(dateTime);
            } else {
                return full12.print(dateTime);
            }
        }

    }
}
