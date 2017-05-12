package com.ffshopmall.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.ffshopmall.R;
import com.ffshopmall.adapter.SearchAdapter;
import com.ffshopmall.model.Shopbean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Baymax on 2017/3/9.
 */

public class FFSearchActivity extends Activity  implements FFSearchView.SearchViewListener{

    /**

     * 搜索结果列表view

     */

    private ListView lvResults;



    /**

     * 搜索view

     */

    private SearchView searchView;





    /**

     * 热搜框列表adapter

     */

    private ArrayAdapter<String> hintAdapter;



    /**

     * 自动补全列表adapter

     */

    private ArrayAdapter<String> autoCompleteAdapter;



    /**

     * 搜索结果列表adapter

     */

    private SearchAdapter resultAdapter;



    private List<Shopbean> dbData;



    /**

     * 热搜版数据

     */

    private List<String> hintData;



    /**

     * 搜索过程中自动补全数据

     */

    private List<String> autoCompleteData;



    /**

     * 搜索结果的数据

     */

    private List<Shopbean> resultData;



    /**

     * 默认提示框显示项的个数

     */

    private static int DEFAULT_HINT_SIZE = 4;



    /**

     * 提示框显示项的个数

     */

    private static int hintSize = DEFAULT_HINT_SIZE;



    /**

     * 设置提示框显示项的个数

     *

     * @param hintSize 提示框显示个数

     */

    public static void setHintSize(int hintSize) {

//        FFSearchView.hintSize = hintSize;

    }
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


    /**

     * 获取db 数据

     */

    private void getDbData() {

        int size = 100;

        dbData = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {

//            dbData.add(new Bean(R.drawable.icon, "android开发必备技能" + (i + 1), "Android自定义view——自定义搜索view", i * 20 + 2 + ""));

        }

    }



//    /**
//
//     * 获取热搜版data 和adapter
//
//     */
//
//    private void getHintData() {
//
//        hintData = new ArrayList<>(hintSize);
//
//        for (int i = 1; i <= hintSize; i++) {
//
//            hintData.add("热搜版" + i + "：Android自定义View");
//
//        }
//
//        hintAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, hintData);
//
//    }
    /**

     * 获取自动补全data 和adapter

     */

    private void getAutoCompleteData(String text) {

        if (autoCompleteData == null) {

            //初始化

            autoCompleteData = new ArrayList<>(hintSize);

        } else {

            // 根据text 获取auto data

            autoCompleteData.clear();

            for (int i = 0, count = 0; i < dbData.size()

                    && count < hintSize; i++) {

                if (dbData.get(i).getShopName().contains(text.trim())) {

                    autoCompleteData.add(dbData.get(i).getShopName());

                    count++;

                }

            }

        }

        if (autoCompleteAdapter == null) {

            autoCompleteAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, autoCompleteData);

        } else {

            autoCompleteAdapter.notifyDataSetChanged();

        }

    }

//    /**
//
//     * 获取搜索结果data和adapter
//
//     */
//
//    private void getResultData(String text) {
//
//        if (resultData == null) {
//
//            // 初始化
//
//            resultData = new ArrayList<>();
//
//        } else {
//
//            resultData.clear();
//
//            for (int i = 0; i < dbData.size(); i++) {
//
//                if (dbData.get(i).getShopName().contains(text.trim())) {
//
//                    resultData.add(dbData.get(i));
//
//                }
//
//            }
//
//        }
//
//        if (resultAdapter == null) {
//
//            resultAdapter = new SearchAdapter(this, resultData, R.layout.);
//
//        } else {
//
//            resultAdapter.notifyDataSetChanged();
//
//        }
//
//    }

    public void onClick(View v) {

    }

    @Override
    public void onReFreshAutoComplete(String text) {
        getAutoCompleteData(text);
    }

    @Override
    public void onSearch(String text) {
//        getResultData(text);
        lvResults.setVisibility(View.VISIBLE);
        if (lvResults.getAdapter() == null) {
            lvResults.setAdapter(resultAdapter);
        } else {
            resultAdapter.notifyDataSetChanged();
        }
        Toast.makeText(this, "完成搜素", Toast.LENGTH_SHORT).show();
    }
}
