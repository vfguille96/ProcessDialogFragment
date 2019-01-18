package com.example.progressdialogfragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.DialogFragment;
import android.app.Fragment;
import android.widget.Toast;


public class TestFragment extends Fragment implements ProgressDialogFragment.ProgressDialogFragmentListener {

    private static final String TAG = "TestFragment";

    @Override
    public void onStart() {
        super.onStart();
        TestTask testTask = new TestTask();
        testTask.execute();
    }

    public class TestTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            showProgress();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(1000);
                publishProgress(30);

                Thread.sleep(1000);
                publishProgress(60);

                Thread.sleep(1000);
                publishProgress(90);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            updateProgress(values);
        }

        @Override
        protected void onPostExecute(Void unused) {
            Toast.makeText(getContext(), "Ya ha terminao, cohone.", Toast.LENGTH_SHORT).show();

            hideProgress();
        }
    }

    // Progress関係
    private void showProgress() {
        Bundle args = new Bundle();
        args.putString(ProgressDialogFragment.TITLE, "Barrote de progreso");
        args.putString(ProgressDialogFragment.MESSAGE, "Ya termina, pisha...");
        args.putBoolean(ProgressDialogFragment.CANCELABLE, false);
        args.putInt(ProgressDialogFragment.MAX, 100);
        ProgressDialogFragment dialog = ProgressDialogFragment.newInstance();
        dialog.setArguments(args);
        dialog.show(getFragmentManager(), TAG);
    }

    private void updateProgress(Integer... values) {
        ProgressDialogFragment progress = getProgressDialogFragment();
        if (progress == null) {
            return;
        }
        progress.updateProgress(values[0]);
    }

    private void hideProgress() {
        ProgressDialogFragment progress = getProgressDialogFragment();
        if (progress == null) {
            return;
        }
        progress.dismissAllowingStateLoss();
    }

    private ProgressDialogFragment getProgressDialogFragment() {
        Fragment fragment = getFragmentManager().findFragmentByTag(TAG);
        return (ProgressDialogFragment) fragment;
    }

    // ProgressDialogFragmentListener
    @Override
    public void onProgressCancelled() {
    }
}