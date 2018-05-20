package com.dream.dreamtutor.authentication;

import android.os.Bundle;
import android.util.Log;
import com.dream.dreamtutor.BaseActivity;
import com.dream.dreamtutor.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class AuthenticationActivity extends BaseActivity
{
    private static final String TAG = "authentication_activity";
    private GoogleSignInClient googleSignInClient = null;

    public GoogleSignInClient getGoogleSignInClient()
    {
        return googleSignInClient;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"after getting instance");
        configureGoogleSignIn();
        initFragment(new LoginFragment());
    }


    @Override
    public void onStart()
    {
        super.onStart();

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }

    public void updateUI(GoogleSignInAccount account)
    {
        if(account != null){
            // TODO :  open main activity

            // TEMP ////////////////
            Log.d(TAG,"email: "+account.getEmail());
            try{
                Log.d(TAG,"display name: "+account.getDisplayName());
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                Log.d(TAG,"Photo URL: "+account.getPhotoUrl());
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                Log.d(TAG,"ID: "+account.getId());
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                Log.d(TAG,"ID token: "+account.getIdToken());
            }catch (Exception e){
                e.printStackTrace();
            }
            ////////////////////////////////
        }else {
            showToastMessage(getString(R.string.you_are_not_signed_in));
        }
    }

    private void configureGoogleSignIn()
    {
        /* Configure sign-in to request the user's ID, email address, and basic
            profile. ID and basic profile are included in DEFAULT_SIGN_IN. */
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);
    }

}
