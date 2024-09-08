package vn.edu.usth.weather;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

public class WeatherActivity extends AppCompatActivity {
        private static final String Tag = "WeatherActivity";
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_weather);

            WeatherFragment weatherFragment = new WeatherFragment();
            getSupportFragmentManager().findFragmentById(R.id.weatherFragment);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.weatherFragment, weatherFragment);
            transaction.commit();

            ForecastFragment forecastFragment = new ForecastFragment();
            getSupportFragmentManager().findFragmentById(R.id.forecastFragment);
            FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
            transaction1.add(R.id.forecastFragment, forecastFragment);
            transaction1.commit();

            Log.i(Tag, "onCreate");
        }

        @Override
        protected void onStart(){
            super.onStart();
            Log.i(Tag,"Start");

        }

        @Override
        protected void onResume(){
            super.onResume();
            Log.i(Tag,"Resume");

        }

        @Override
        protected void onPause(){
            super.onPause();
            Log.i(Tag,"Pause");

        }

        @Override
        protected void onStop(){
            Log.i(Tag,"Stop");
            super.onStop();

        }

        @Override
        protected void onDestroy(){
            super.onDestroy();
            Log.i(Tag,"Destroy");

        }

}