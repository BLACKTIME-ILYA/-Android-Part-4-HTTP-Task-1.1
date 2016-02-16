package com.sourceit.task1.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sourceit.task1.R;

import java.util.LinkedList;

/**
 * Created by User on 15.02.2016.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> implements View.OnClickListener {

    private LinkedList<String> objects;
    private OnItemClickWatcher<String> watcher;

    public MyRecyclerAdapter(LinkedList<String> objects, OnItemClickWatcher<String> watcher) {
        this.objects = objects;
        this.watcher = watcher;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.object, parent, false);
        return new ViewHolder(v, watcher, objects);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(objects.get(position));
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    @Override
    public void onClick(View v) {
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;

        public ViewHolder(View item, final OnItemClickWatcher<String> watcher, final LinkedList<String> objects) {
            super(item);
            name = (TextView) item.findViewById(R.id.object_name);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    watcher.onItemClick(v, getAdapterPosition(), objects.get(getAdapterPosition()));
                }
            });
        }
    }
}
