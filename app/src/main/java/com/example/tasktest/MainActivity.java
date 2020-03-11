package com.example.tasktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.pg_bar_my)
    ProgressBar pgBar;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.start_timer)
    Button startTimer;
    @BindView(R.id.tv_time)
    TextView tv_time;

    private Unbinder unbinder;

    private Integer count =1;
    private int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        unbinder = ButterKnife.bind(this);
    }

    @OnTextChanged(R.id.et_name)
    public void setSite(Editable editable) {
        String time = editable.toString();
        if (!time.equals("")){
            progress = Integer.parseInt(time);
        }else {
            progress = 0;
        }
    }

    @OnClick({R.id.start_timer})
    public void startTimer() {
        count = 1;
        pgBar.setVisibility(View.VISIBLE);
        pgBar.setProgress(0);
        pgBar.setMax(progress);
        new MyTask().execute(progress);
    }

   protected class MyTask extends AsyncTask<Integer, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Integer... params) {
            for (; count <= params[0]; count++) {
                try {
                    Thread.sleep(1000);
                    publishProgress(count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pgBar.setProgress(values[0]);
            tv_time.setText(String.valueOf(progress--));
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            pgBar.setVisibility(View.GONE);
            startTimer.setText(R.string.restart);
            tv_time.setText(String.valueOf(0));
            Toast.makeText(MainActivity.this, R.string.timer_is_stop, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
