package com.ccr.anumberprogressbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ccr.library.ANumberProgressBar;
import com.ccr.library.OnProgressBarChangeListener;
import com.ccr.library.TextThumbSeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements OnProgressBarChangeListener{

    private Timer timer;

    private ANumberProgressBar bnp;
    private TextThumbSeekBar seekBar;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar=findViewById(R.id.sb_progress);
        bnp = (ANumberProgressBar)findViewById(R.id.progress1);
        bnp.setProgressBarChangeListener(this);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bnp.incrementProgressBy(1);
                        seekBar.setText("");
                    }
                });
            }
        }, 1000, 100);
    }

    @Override
    public void onProgressChange(int currentProgress, int maxProgress) {
        if(currentProgress == maxProgress) {
            Toast.makeText(getApplicationContext(), "完成", Toast.LENGTH_SHORT).show();
            bnp.setProgress(0);
        }
    }
}
