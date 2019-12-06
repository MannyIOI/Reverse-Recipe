package com.ethiopia.addisababa.manny.reverserecipe2;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;

/**
 * Created by Amanuel Teferi on 5/25/2018.
 */

public class OnSwipeTouchListener implements View.OnTouchListener {

    private final GestureDetector gestureDetector;

    public OnSwipeTouchListener(Context context) {

        Log.d("swipe","fling");
        gestureDetector = new GestureDetector(context, new GestureListener());
        Log.d("swipe","fling");
    }

    public void onSwipLeft(){
        // Do something
    }
    public void onSwipeRight(){

    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
    private final class GestureListener extends SimpleOnGestureListener{
        private static final int SWIPE_DISTANCE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;


        public boolean onFling(MotionEvent e1, MotionEvent e2, float vx, float vy){
            float dx = e2.getX() - e1.getX();
            float dy = e2.getY() - e1.getY();
//            if(Math.abs(dx) > Math.abs(dy) && Math.abs(dx) > SWIPE_DISTANCE_THRESHOLD && Math.abs(vx) > SWIPE_VELOCITY_THRESHOLD){
//                if(dx > 0){
//                    onSwipeRight();
//                }
//                else{
//                    onSwipLeft();
//                }
//                return true;
//            }
//            return false;
            return true;
        }
    }
}
