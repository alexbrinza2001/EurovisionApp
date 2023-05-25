package com.example.eurovisionapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

public class Tab3 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab3, container, false);

        ImageButton backButton = view.findViewById(R.id.tab3_back);

        backButton.setOnClickListener(v -> {startActivity(new Intent(getActivity(), MainActivity.class));});

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View rootView = getView();

        WebView webView = rootView.findViewById(R.id.tab3_page);

        webView.getSettings().setJavaScriptEnabled(true);

        WebViewClient webViewClient = new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
            }
        };

        webView.setWebViewClient(webViewClient);

        webView.loadUrl("https://eurovisionworld.com/eurovision/2021");

    }
}