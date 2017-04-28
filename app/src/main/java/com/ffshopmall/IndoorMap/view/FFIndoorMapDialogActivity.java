package com.ffshopmall.IndoorMap.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.ffshopmall.R;

/**
 * Created by Administrator on 2017/4/28.
 */

public class FFIndoorMapDialogActivity extends DialogFragment {

    private View mView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        mView = inflater.inflate(R.layout.indoormap_dialog,container,false);

        return mView;
    }

}
