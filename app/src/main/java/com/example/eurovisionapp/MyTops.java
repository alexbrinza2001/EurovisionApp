package com.example.eurovisionapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyTops extends AppCompatActivity {

    private ContestAdapter contestAdapter;

    //0 = my tops, 1 = users tops
    public static int whereAmI = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tops_page);

        ImageButton myTopsButton = findViewById(R.id.my_tops_back);

        myTopsButton.setOnClickListener(v -> startActivity(new Intent(MyTops.this, MainActivity.class)));

        Context context = this;
        EurovisionDatabase eurovisionDatabase = new EurovisionDatabase(context);
        SQLiteDatabase db = eurovisionDatabase.getReadableDatabase();
        String[] projection = {"image", "year", "city"};

        @SuppressLint("Recycle") Cursor cursor = db.query("contest_info", projection, null, null, null, null, null);

        List<EurovisionContest> dataList = new ArrayList<EurovisionContest>();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                byte[] image = cursor.getBlob(cursor.getColumnIndexOrThrow("image"));
                String city = cursor.getString(cursor.getColumnIndexOrThrow("city"));
                int year = cursor.getInt(cursor.getColumnIndexOrThrow("year"));

                EurovisionContest item = new EurovisionContest();

                item.setCity(city);
                item.setImage(image);
                item.setYear(year);

                dataList.add(item);

            } while (cursor.moveToNext());
        }

        RecyclerView recyclerView = findViewById(R.id.contests);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) recyclerView.getLayoutParams();
        layoutParams.verticalBias = 0.0f;
        recyclerView.setLayoutManager(layoutManager);

        contestAdapter = new ContestAdapter(dataList, context);

        recyclerView.setAdapter(contestAdapter);
        recyclerView.setBackgroundColor(2);

        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                List<Button> buttons = contestAdapter.getButtonList();

                buttons.get(0).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (whereAmI == 0){
                            startActivity(new Intent(MyTops.this, Top2023.class));
                        }
                        else{
                            startActivity(new Intent(MyTops.this, Overall2023.class));
                        }
                    }
                });

                buttons.get(1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (whereAmI == 0){
                            startActivity(new Intent(MyTops.this, Top2022.class));
                        }
                        else{
                            startActivity(new Intent(MyTops.this, Overall2022.class));
                        }
                    }
                });

                buttons.get(2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (whereAmI == 0){
                            startActivity(new Intent(MyTops.this, Top2021.class));
                        }
                        else{
                            startActivity(new Intent(MyTops.this, Overall2021.class));
                        }
                    }
                });

                recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

    }
}