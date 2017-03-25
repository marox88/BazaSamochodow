package com.example.rent.bazasamochodow;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.TextView;

/**
 * Created by RENT on 2017-03-25.
 */

public class AutoCompleteAdapter extends CursorAdapter implements Filterable {

    public AutoCompleteAdapter(Context context, Cursor cursor) {
        super(context, cursor, false);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.single_autocomplete_item,parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText(cursor.getString(cursor.getColumnIndex(CarsTableContract.COLUMN_MAKE)));
    }


}
