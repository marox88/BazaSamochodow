package com.example.rent.bazasamochodow.add;

import android.content.DialogInterface;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rent.bazasamochodow.Car;
import com.example.rent.bazasamochodow.CarBuilder;
import com.example.rent.bazasamochodow.MotoDatabaseOpenHelper;
import com.example.rent.bazasamochodow.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.onClick;

public class AddNewCarActivity extends AppCompatActivity {

    private MotoDatabaseOpenHelper databaseOpenHelper;

    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.year)
    EditText year;

    @BindView(R.id.model)
    EditText model;

    @BindView(R.id.make)
    EditText make;
    private String imageUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_car);
        ButterKnife.bind(this);
        databaseOpenHelper = new MotoDatabaseOpenHelper(this);
    }

    @OnClick(R.id.add_car)
    void onAddCarButtonClick() {
        Car car = new CarBuilder()
                .setMake(make.getText().toString())
                .setModel(model.getText().toString())
                .setYear(Integer.parseInt(year.getText().toString()))
                .setImage(imageUrl)
                .createCar();
        boolean added = databaseOpenHelper.insertCar(car);
        if(added) {
            Toast.makeText(this, "Dodano nowy samochód", Toast.LENGTH_LONG). show();
        }

    }

    @OnClick(R.id.add_image)
    void setAddImageButtonClick () {
        View promptView = LayoutInflater.from(this).inflate(R.layout.dialog_prompt, null);
        EditText urlEditText = (EditText) promptView.findViewById(R.id.url_edittext);

        new AlertDialog.Builder(this)
                .setView(promptView)
                .setPositiveButton("OK", (dialog, which) -> {
                        imageUrl = urlEditText.getText().toString();
        Glide.with(AddNewCarActivity.this)
                .load(imageUrl)
                .into(image);



    }).show();


    }
}
