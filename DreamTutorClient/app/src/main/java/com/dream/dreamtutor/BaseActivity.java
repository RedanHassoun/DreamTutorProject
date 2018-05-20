package com.dream.dreamtutor;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by Redan on 12/1/2016.
 */
public class BaseActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener
{
    protected FragmentManager manager;
    private ProgressBar progressBar=null;
    private static final String TAG= "base_activity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base);


        overridePendingTransition(0, 0);

        initViews();

    }


    private void initViews()
    {

    }

    protected void initFragment(Fragment fragment)
    {
        manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, fragment)
                .commit();
    }

    protected void initFragment(Fragment fragment,Bundle arguments)
    {
        manager = getSupportFragmentManager();
        fragment.setArguments(arguments);

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, fragment)
                .commit();
    }

    @Override
    public void onBackStackChanged()
    {
        if (manager.getBackStackEntryCount() > 0)
            manager.getFragments().get(manager.getBackStackEntryCount() - 1).onResume();

    }

    public void replaceFragment(Fragment fragment, String fragmentName, Bundle arguments, boolean addToBackStack)
    {
        fragment.setArguments(arguments);

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in, R.anim.slide_out);
        transaction.replace(R.id.content_frame, fragment);
        if(addToBackStack)
            transaction.addToBackStack(fragmentName);
        transaction.commit();
    }


    public void addFragment(Fragment fragment,String fragmentName,Bundle arguments,boolean addToBackStack)
    {
        fragment.setArguments(arguments);

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in, R.anim.slide_out);
        transaction.add(R.id.content_frame, fragment);
        if(addToBackStack)
            transaction.addToBackStack(fragmentName);
        transaction.commit();
    }

    public void showProgressBar()
    {
        if(progressBar == null){
            progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        }

        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar()
    {
        if(progressBar == null){
            progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        }

        progressBar.setVisibility(View.GONE);
    }

    protected void showToastMessage(String message)
    {
        Toast.makeText(BaseActivity.this, message,
                Toast.LENGTH_SHORT).show();
    }
}