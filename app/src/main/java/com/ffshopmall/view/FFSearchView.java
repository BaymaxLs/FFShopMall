package com.ffshopmall.view;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ffshopmall.R;

/**
 * Created by Baymax on 2017/3/10.
 */

public class FFSearchView extends LinearLayout implements View.OnClickListener {

    /*
    * 删除按钮
    * */
    private ImageView icon_delete;

    /*
    * 输入框
    * */
    private EditText et_input;

    /*
    * 搜索确认按钮
    * */
    private TextView tv_search;

    /*
    * 返回按钮
    * */
    private ImageView icon_back;

    /*
    * 上下文对象
    * */
    private Context m_Context;

    /*
    * 弹出列表
    * */
    private ListView lv_tips;

    /*
    * 提示Adapter
    * */
    private ArrayAdapter<String> m_HintAdapter;

    /*
    * 自动补全Adapter
    * */
    private ArrayAdapter<String> m_AutoCompleteAdapter;

    /*
    * 搜索回调接口
    * */
    private SearchViewListener m_Listener;


    /*
    * 设置搜索回调接口
    * */
    public void setSearchListener(SearchViewListener listener) {
        m_Listener = listener;
    }

    public FFSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        m_Context = context;
        LayoutInflater.from(context).inflate(R.layout.search_view, this);
        initView();
        initEvent();
    }

    private void initView() {

        icon_back = (ImageView) findViewById(R.id.id_search_icon_back);
        et_input = (EditText) findViewById(R.id.id_search_et_input);
        tv_search = (TextView) findViewById(R.id.id_search_tv_search);
        icon_delete = (ImageView) findViewById(R.id.id_search_icon_delete);
        lv_tips = (ListView) findViewById(R.id.search_lv_tips);
    }

    private void initEvent() {

        icon_back.setOnClickListener(this);
        icon_delete.setOnClickListener(this);

        et_input.addTextChangedListener(new EditChangerListener());
        et_input.setOnClickListener(this);
        et_input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    lv_tips.setVisibility(GONE);
                    notifyStartSearching(et_input.getText().toString());
                }
                return true;
            }
        });
        lv_tips.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = lv_tips.getAdapter().getItem(i).toString();
                et_input.setText(text);
                et_input.setSelection(text.length());
                lv_tips.setVisibility(View.GONE);
                notifyStartSearching(text);
            }
        });
    }

    private void notifyStartSearching(String text) {
        if (m_Listener != null) {
            m_Listener.onSearch(et_input.getText().toString());
        }

        InputMethodManager imm = (InputMethodManager) m_Context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public void setAutoCompleteAdapter(ArrayAdapter<String> adapter) {
        this.m_AutoCompleteAdapter = adapter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_search_et_input:
                lv_tips.setVisibility(VISIBLE);
                break;
            case R.id.id_search_icon_delete:
                et_input.setText("");
                icon_delete.setVisibility(GONE);
                break;
            case R.id.id_search_icon_back:
                ((Activity) m_Context).finish();
                break;
        }

    }

    public interface SearchViewListener {

        /**
         * 更新自动补全内容
         *
         * @param text 传入补全后的文本
         */
        void onReFreshAutoComplete(String text);

        /**
         * 更新自动补全内容
         *
         * @param text 传入补全后的文本
         */
        void onSearch(String text);


    }

    private class EditChangerListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!"".equals(s.toString())) {
                icon_delete.setVisibility(VISIBLE);
                lv_tips.setVisibility(VISIBLE);
                if (m_AutoCompleteAdapter != null && lv_tips.getAdapter() != m_AutoCompleteAdapter) {
                    lv_tips.setAdapter(m_AutoCompleteAdapter);
                }
                //更新autoComplete数据
                if (m_Listener != null) {
                    m_Listener.onReFreshAutoComplete(s + "");
                }
            } else {
                icon_delete.setVisibility(GONE);
                if (m_HintAdapter != null) {
                    lv_tips.setAdapter(m_HintAdapter);
                }
                lv_tips.setVisibility(GONE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
