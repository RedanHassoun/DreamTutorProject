package com.dream.dreamtutor.common;

import android.support.v4.app.Fragment;
import android.widget.Toast;

public class GeneralFragment extends Fragment
{
    protected void showProgressDialog()
    {
        // TODO
    }

    protected void hideProgressDialog()
    {
        // TODO
    }

    protected void showToastMessage(String message)
    {
        Toast.makeText(getActivity().getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }
}
