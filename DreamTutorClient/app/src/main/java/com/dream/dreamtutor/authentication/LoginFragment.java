package com.dream.dreamtutor.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.dream.dreamtutor.R;
import com.dream.dreamtutor.common.GeneralFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginFragment extends GeneralFragment implements View.OnClickListener
{
    private static final String TAG = "fragment_login";
    private final int RC_SIGN_IN = 10023;

    public static LoginFragment newInstance()
    {
        LoginFragment fragment = new LoginFragment();

        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.google_sign_in_button:
                Log.d(TAG,"loging in ...");
                /* Login */
                Intent signInIntent = ((AuthenticationActivity) getActivity())
                                                                .getGoogleSignInClient()
                                                                .getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
                break;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Log.d(TAG,"before inflating ");
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        Log.d(TAG,"after inflating ");

        // Set the dimensions of the sign-in button.
        SignInButton signInButton = view.findViewById(R.id.google_sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        signInButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "onActivityResult | result code = " +requestCode);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task)
    {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            ((AuthenticationActivity)getActivity()).updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            e.printStackTrace();
            // TODO : show error message
            ((AuthenticationActivity)getActivity()).updateUI(null);
        }
    }
}
