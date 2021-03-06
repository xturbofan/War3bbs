/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2015 Umeng, Inc
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.umeng.commm.ui.fragments;

import android.view.View;

import com.umeng.comm.core.beans.CommUser;
import com.umeng.comm.core.constants.Constants;
import com.umeng.comm.core.constants.HttpProtocol;
import com.umeng.comm.core.utils.ResFinder;
import com.umeng.common.ui.presenter.impl.ActiveUserFgPresenter;
import com.umeng.common.ui.presenter.impl.RelativeUserFgPresenter;
import com.umeng.common.ui.util.BroadcastUtils;
import com.umeng.common.ui.widgets.RefreshLayout;

import java.util.List;

/**
 * 相关用户Fragment
 */
public class RelativeUserFragment extends RecommendUserFragment {

    private View mBackView = null;

    @Override
    protected void initWidgets() {
        super.initWidgets();
        List<CommUser> users = getArguments().getParcelableArrayList(Constants.TAG_USERS);
        mAdapter.addData(users);
        mRefreshLvLayout.setRefreshing(true);
        String nextPageUrl = getArguments().getString(HttpProtocol.NAVIGATOR_KEY);
        mPresenter.setNextPageUrl(nextPageUrl);
        mRootView.findViewById(ResFinder.getId("umeng_comm_save_bt")).setVisibility(View.GONE);
        mBackView = mRootView.findViewById(ResFinder.getId("umeng_comm_setting_back"));
        mBackView.setVisibility(View.VISIBLE);
        mBackView.setOnClickListener(this);
        mTitleTextView.setText(ResFinder.getString("umeng_comm_relation_user"));
    }

    @Override
    protected ActiveUserFgPresenter createPresenters() {
        return new RelativeUserFgPresenter(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mBackView) {
            getActivity().finish();
        }
    }
}
