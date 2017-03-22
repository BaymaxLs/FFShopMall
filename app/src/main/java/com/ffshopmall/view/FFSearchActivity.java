package com.ffshopmall.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.ffshopmall.R;

/**
 * Created by Baymax on 2017/3/9.
 */

public class FFSearchActivity extends Activity  implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search);

        initView();

        initEvent();
    }


    private void initView() {

    }

    private void initEvent() {

    }

    @Override
    public void onClick(View v) {

    }
}
