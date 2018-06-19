package com.example.mlabsystem2.experiment3;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;


public class MyService extends Service {

        private double mRandomNum;
        private boolean isON;
        private final int MIN=0;
        private final int MAX=100;
        public Handler h = new Handler();

        class MyServiceBinder extends Binder {
            private MyService getService(){
                return MyService.this;
            }
        }

        private IBinder mBinder= new MyServiceBinder();


        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {

            //Log.d("THread id","Thread id: "+ Thread.currentThread().getId());
            isON=true;
              //runnableCode.run();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        startRandnumGen();
                    }
                 }).start();
            return START_NOT_STICKY;
        }


//    private Runnable runnableCode = new Runnable() {
//        @Override
//        public void run() {
//            // Do something here on the main thread
//            startRandnumGen();
//            //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
//
//            Log.d("Handlers", "Called on main thread");
//            h.postDelayed(runnableCode, 1000);
//        }
//    };

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return mBinder;
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            stopRandomnumGen();
            Toast.makeText(this,"Service destroyed",Toast.LENGTH_SHORT).show();

        }


        private void startRandnumGen(){
            while (isON=true){
                try{
                    Thread.sleep(2000);
                    if(isON){
                        mRandomNum = new Random().nextInt(MAX)+MIN;
                        //Toast.makeText(this,"Random number:"+ Double.toString(mRandomNum),Toast.LENGTH_SHORT).show();
                        Log.d("Random number:",Double.toString(mRandomNum) );
                    }
                }catch (InterruptedException e){
                    //Toast.makeText(this,"Thread interrupted",Toast.LENGTH_SHORT).show();
                    Log.d("Thread status:", "Interrupted");
                }
            }

        }


        private void stopRandomnumGen(){
            isON=false;
        }

        public double getRandomNum(){
            return mRandomNum;

        }
}
