package com.example.weatherapiwithjetpacknavigation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class WebViewFragment extends Fragment {

    WebView weather_web_view;

    public WebViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        weather_web_view = view.findViewById(R.id.weather_web_view);
        weather_web_view.setWebViewClient(new WebViewClient());
        weather_web_view.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        weather_web_view.getSettings().setDomStorageEnabled(true);
        weather_web_view.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        weather_web_view.getSettings().setLoadsImagesAutomatically(true);

        if(getArguments() != null) {
            System.out.println("URL " + getArguments().getString("url"));
            weather_web_view.loadUrl(getArguments().getString("url"));
        }else{

            weather_web_view.loadUrl("http://www.bbc.com/weather/6077243");
        }


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_web_view, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}
