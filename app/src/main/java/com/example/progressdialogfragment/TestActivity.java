package com.example.progressdialogfragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;

public class TestActivity extends Activity implements ProgressDialogFragment.ProgressDialogFragmentListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TestFragment
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new TestFragment();
        fragmentTransaction.add(android.R.id.content, fragment, "test");
        fragmentTransaction.commit();
    }

    // ProgressDialogFragmentListener
    @Override
    public void onProgressCancelled() {
        android.app.Fragment fragment = getFragmentManager().findFragmentByTag("test");
        if (fragment instanceof ProgressDialogFragment.ProgressDialogFragmentListener) {
            ((ProgressDialogFragment.ProgressDialogFragmentListener) fragment).onProgressCancelled();
        }
    }
}