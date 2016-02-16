package com.sourceit.task1.ui;

import android.view.View;

/**
 * Created by User on 11.02.2016.
 */
public abstract class OnItemClickWatcher<T> {
    public abstract void onItemClick(View v, int position, T item);
}
