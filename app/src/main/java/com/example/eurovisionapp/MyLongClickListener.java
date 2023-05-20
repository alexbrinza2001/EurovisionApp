package com.example.eurovisionapp;

import android.content.ClipData;
import android.view.View;
import android.widget.TextView;

public class MyLongClickListener implements View.OnLongClickListener {
    @Override
    public boolean onLongClick(View v) {
        TextView textView = (TextView) v;
        ClipData data = ClipData.newPlainText("", textView.getText());
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
        v.startDrag(data, shadowBuilder, v, 0);
        return true;
    }
}

