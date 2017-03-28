package com.example.rent.bazasamochodow.listing;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rent.bazasamochodow.CarsTableContract;
import com.example.rent.bazasamochodow.R;

import static butterknife.ButterKnife.findById;

/**
 * Created by RENT on 2017-03-27.
 */

public class RecyclerViewCursorAdapter extends RecyclerView.Adapter<RecyclerViewCursorAdapter.ViewHolder> {

    private OnCarItemClickListener onCarItemClickListener;

    public void setOnCarItemClickListener(OnCarItemClickListener onCarItemClickListener) {
        this.onCarItemClickListener = onCarItemClickListener;
    }

    private Cursor cursor;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        cursor.moveToPosition(position);
        String imageUrl = cursor.getString((cursor.getColumnIndex(CarsTableContract.COLUMN_IMAGE)));
        String make = cursor.getString((cursor.getColumnIndex(CarsTableContract.COLUMN_MAKE)));
        String model = cursor.getString((cursor.getColumnIndex(CarsTableContract.COLUMN_MODEL)));
        int year = cursor.getInt((cursor.getColumnIndex(CarsTableContract.COLUMN_YEAR)));

        holder.year.setText("Rocznik: " + year);
        holder.makeAndModel.setText(make + " " + model);
        Glide.with(holder.imageView.getContext()).load(imageUrl).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (onCarItemClickListener != null)
                    cursor.moveToPosition(position);
                onCarItemClickListener.onCarItemClick(String.valueOf(cursor.getInt(0)));
            }
        });
    }

    public Cursor getCursor() {
        return cursor;
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return cursor != null ? cursor.getCount() :0 ;
    }



    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView makeAndModel;
        private TextView year;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = findById(itemView, R.id.image);
            makeAndModel = (TextView) itemView.findViewById(R.id.make_and_model);
            year = findById (itemView, R.id.year);
        }
    }


}
