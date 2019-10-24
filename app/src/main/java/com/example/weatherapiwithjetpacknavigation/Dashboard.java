package com.example.weatherapiwithjetpacknavigation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Dashboard extends Fragment {

    TextView txt_name,weather_name,temp_min,the_temp,temp_max,humidity,predictability,more_info;
    ImageView weather_img;
    Controller con;
    RecyclerView recyclerView ;
    public NavController navController;
    public int geoId,bbcId;
    String url;

    public Dashboard() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txt_name= view.findViewById(R.id.txt_dashname);
        weather_img = view.findViewById(R.id.img_weather);
        weather_name = view.findViewById(R.id.weather_name);
        temp_min = view.findViewById(R.id.temp_min);
        the_temp = view.findViewById(R.id.the_temp);
        temp_max = view.findViewById(R.id.temp_max);
        humidity = view.findViewById(R.id.humidity);
        predictability = view.findViewById(R.id.predictability);
        recyclerView = view.findViewById(R.id.recyclerview);
        more_info = view.findViewById(R.id.more_info);
        navController = Navigation.findNavController(getActivity(),R.id.host_frag);

        Getdataservice service = RetroFitInstance.getRetrofitInstance().create(Getdataservice.class);


        if(getArguments() != null) {
            geoId = getArguments().getInt("geoId");
            bbcId = getArguments().getInt("bbcId");
            System.out.println("Bundle " + getArguments().getInt("geoId"));
        }else{
            geoId = 3534;
            bbcId = 6077243;
        }
        Call<Weather> call = service.getWeather(geoId);
        //System.out.println("Call : " + call);
        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {

                Weather real = response.body();
                ArrayList<ConsolidatedWeather> conArray = new ArrayList<>(real.getConsolidatedWeather());

                System.out.println("Weather API Body : " + real);

                txt_name.setText(real.getTitle().toString());
                url = real.getSources().get(0).getUrl() + bbcId;
                setupData(conArray);

                System.out.println("Response : " + conArray.get(0).getWeatherStateName());

            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

                System.out.println("Error : "+t.getMessage());

            }
        });


    }

    public void setupData(ArrayList<ConsolidatedWeather> arrWeather){

        setupImage(imageString(arrWeather.get(0).getWeatherStateAbbr()),weather_img);
        weather_name.setText(arrWeather.get(0).getWeatherStateName().toString());
        temp_min.setText(String.format("%.2f",arrWeather.get(0).getMinTemp()));
        the_temp.setText(String.format("%.2f",arrWeather.get(0).getTheTemp()));
        temp_max.setText(String.format("%.2f",arrWeather.get(0).getMaxTemp()));
        humidity.setText("Humidity: " + arrWeather.get(0).getHumidity().toString() + "%");
        predictability.setText("Predictability: " + arrWeather.get(0).getPredictability().toString() + "%");

        System.out.println("Response From SetupData: " + String.format(String.format("%.2f",arrWeather.get(0).getTheTemp())));
        initView(arrWeather);

        more_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.getString("url",url);
                System.out.println("Response : " + url);
                navController.navigate(R.id.webViewFragment,bundle);
            }
        });

    }

    public String imageString(String abbr){

        return "https://www.metaweather.com/static/img/weather/png/" + abbr + ".png";

    }

    public void setupImage(String url, ImageView imgV){

        Glide.with(getActivity().getApplicationContext()).asBitmap().load(url).into(imgV);

    }


    public void initView(ArrayList<ConsolidatedWeather> wearray)
    {
        wearray.remove(0);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);

        recyclerView.setLayoutManager(layoutManager);
        Recyadapter adapter = new Recyadapter(wearray,getActivity().getApplicationContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


}
