package com.example.eurovisionapp;

import android.content.ClipData;
import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;

public class MyDragListener implements View.OnDragListener {

    @Override
    public boolean onDrag(View v, DragEvent event) {
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                break;
            case DragEvent.ACTION_DROP:
                ClipData.Item item = event.getClipData().getItemAt(0);
                String droppedText = item.getText().toString();
                int pos1 = 5, pos2 = 6;

                while(droppedText.charAt(pos2) != '-') {
                    pos2 = pos2 + 1;
                }
                pos2 -= 1;

                String country = droppedText.substring(pos1, pos2);

                ((TextView) v).setText(country);
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                break;
            default:
                break;
        }
        return true;
    }
}

