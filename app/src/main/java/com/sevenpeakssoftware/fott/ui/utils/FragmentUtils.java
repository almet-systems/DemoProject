package com.sevenpeakssoftware.fott.ui.utils;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by razir on 1/3/2017.
 */

public class FragmentUtils {

    private static final String FRAGMENT_TAG = "Content";

    public static void changeFragment(FragmentActivity activity, int contentFrame,
                                      Fragment fr, boolean addToBackStack) {
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        ft.replace(contentFrame, fr, FRAGMENT_TAG);
        if (addToBackStack) {
            ft.addToBackStack(null);
        }

        ft.commitAllowingStateLoss();
    }

}
