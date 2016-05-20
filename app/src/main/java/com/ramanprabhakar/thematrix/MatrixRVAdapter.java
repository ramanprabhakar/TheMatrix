package com.ramanprabhakar.thematrix;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Raman on 5/19/2016.
 */
public class MatrixRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;
    int matrixSize;

    public MatrixRVAdapter(Context mContext, int matrixSize) {
        this.mContext = mContext;
        this.matrixSize = matrixSize;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.layout_grid_item, parent, false);
        MatrixViewHolder vh = new MatrixViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

//        Random rand = new Random();
        Integer random = (int) (Math.random()*3) + 1;
        MatrixViewHolder viewHolder = (MatrixViewHolder)holder;
        viewHolder.tvGridItem.setText(random.toString());
    }


    @Override
    public int getItemCount() {
        return matrixSize;
    }

    public class MatrixViewHolder extends RecyclerView.ViewHolder {

        TextView tvGridItem;

        public MatrixViewHolder(View itemView) {
            super(itemView);;
            tvGridItem = (TextView)itemView.findViewById(R.id.tv_grid_item);

        }
    }

}
