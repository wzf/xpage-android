package com.xuexiang.xpage.base;

import android.widget.AdapterView;
import android.widget.ListView;

import com.xuexiang.xpage.R;

/**
 * 带ListView的基础fragment
 *
 * @author xuexiang
 * @since 2018/5/24 下午3:36
 */
public abstract class XPageListFragment extends XPageFragment implements AdapterView.OnItemClickListener {
    ListView mListView;
    @Override
    protected int getLayoutId() {
        return R.layout.xpage_fragment_listview;
    }

    @Override
    protected void initViews() {
        mListView = findViewById(R.id.lv_simple);
        mListView.setOnItemClickListener(this);
        initData();
    }

    protected abstract void initData();

    @Override
    protected void initListeners() {

    }

    protected ListView getListView() {
        return mListView;
    }
}
