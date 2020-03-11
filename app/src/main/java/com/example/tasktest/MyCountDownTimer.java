package com.example.tasktest;

import android.app.Dialog;
import android.os.CountDownTimer;

import androidx.annotation.NonNull;

public class MyCountDownTimer extends CountDownTimer {
    private int progress;
    private long millisUntilFinished;



    MyCountDownTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long millisUntilFinished) {
//        textCounter.setText(String.valueOf(millisUntilFinished));
        int progress = (int) (millisUntilFinished/100);
//        progressBar.setProgress(progress);
        setProgress(progress);
        setMillisUntilFinished(millisUntilFinished);
    }

    @Override
    public void onFinish() {
//        textCounter.setText("Finished");
//        progressBar.setProgress(0);
        setProgress(0);
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public long getMillisUntilFinished() {
        return millisUntilFinished;
    }

    public void setMillisUntilFinished(long millisUntilFinished) {
        this.millisUntilFinished = millisUntilFinished;
    }

}
