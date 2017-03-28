package com.example.rent.bazasamochodow.details;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rent.bazasamochodow.Car;
import com.example.rent.bazasamochodow.MotoDatabaseOpenHelper;
import com.example.rent.bazasamochodow.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DetailsFragment extends Fragment {

private static final String CAR_ID_KEY = "car_id_key";

    @BindView(R.id.make_and_model)
    TextView makeAndModel;

    @BindView(R.id.detail_image_view)
    ImageView detailImage;

    @BindView(R.id.year)
    TextView year;

    private MotoDatabaseOpenHelper openHelper;
    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openHelper = new MotoDatabaseOpenHelper(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String carId = getArguments().getString(CAR_ID_KEY);
        unbinder = ButterKnife.bind(this, view);
        Car car = openHelper.getCarWithId(carId);
        makeAndModel.setText(car.getMake() + " " + car.getModel());
        year.setText("Rocznik: " + car.getYear());
        Glide.with(getActivity()).load(car.getImage()).into(detailImage);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public static Fragment getInstance (String carId) {
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle arguments = new Bundle();
        arguments.putString(CAR_ID_KEY, carId);
        detailsFragment.setArguments(arguments);
        return detailsFragment;
    }
}
