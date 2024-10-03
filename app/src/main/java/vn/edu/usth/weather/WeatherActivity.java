package vn.edu.usth.weather;

import android.os.Bundle;
import android.util.Log;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.media.MediaPlayer;
import android.os.Environment;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.widget.Toolbar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class WeatherActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private MediaPlayer mediaPlayer;
    private static final String Tag = "WeatherActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(pager);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.i(Tag, "onCreate");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_weather, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.refresh_button) {
            Toast.makeText(this, "Refreshing", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.setting_button) {
            Intent intent = new Intent(this, PrefActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void extractAndPlayMusic() {
        File musicFile = new File(Environment.getExternalStorageDirectory(), "sample.mp3");
        if (!musicFile.exists()) {
            try {
                InputStream is = getResources().openRawResource(R.raw.sample);  // Replace 'sample' with your MP3 file name
                FileOutputStream fos = new FileOutputStream(musicFile);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, length);
                }
                fos.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(musicFile.getAbsolutePath());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            if (mediaPlayer != null) {
                mediaPlayer.release();
                mediaPlayer = null;
            }
            Log.i(Tag,"Destroy");
        }
}