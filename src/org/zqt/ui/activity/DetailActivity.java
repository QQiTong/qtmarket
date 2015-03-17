package org.zqt.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import org.zqt.R;
import org.zqt.bean.AppInfo;
import org.zqt.http.protocol.DetailProtocol;
import org.zqt.ui.holder.AppDetailBottomHolder;
import org.zqt.ui.holder.AppDetailDesHolder;
import org.zqt.ui.holder.AppDetailInfoHolder;
import org.zqt.ui.holder.AppDetailSafeHolder;
import org.zqt.ui.holder.AppDetailScreenHolder;
import org.zqt.ui.widget.LoadingPage;
import org.zqt.ui.widget.LoadingPage.LoadResult;
import org.zqt.utils.StringUtils;
import org.zqt.utils.UIUtils;

public class DetailActivity extends BaseActivity {
	public static final String PACKAGENAME = "PACKAGENAME";

	private AppInfo mAppInfo;
	private String mPackageName;
	private ActionBar mActionBar;
	//各区域布局及其holder
	private FrameLayout mInfoLayout, mSafeLayout, mDesLayout, mBottomLayout;
	private HorizontalScrollView mScreenLayout;
	private AppDetailInfoHolder mInfoHolder;
	private AppDetailSafeHolder mSafeHolder;
	private AppDetailScreenHolder mScreenHolder;
	private AppDetailDesHolder mDesHolder;
	private AppDetailBottomHolder mBottomHolder;

	@Override
	protected void onDestroy() {
		if (mBottomHolder != null) {
			mBottomHolder.stopObserver();
		}
		super.onDestroy();
	}

	@Override
	protected void init() {
		Intent i = getIntent();
		if (i != null) {//从意图中获取packageName用户从服务端获取数据
			mPackageName = i.getStringExtra(PACKAGENAME);
		}
	}

	/** 初始化actionBar */
	protected void initActionBar() {
		mActionBar = getSupportActionBar();
		mActionBar.setTitle(R.string.app_detail);
		//应用图标来返回主页
		mActionBar.setHomeButtonEnabled(true);
		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setDisplayShowHomeEnabled(true);
	}

	/** 初始化布局 */
	protected void initView() {
		LoadingPage page = new LoadingPage(this) {
			@Override
			public LoadResult load() {
				return DetailActivity.this.load();
			}

			@Override
			public View createLoadedView() {
				return DetailActivity.this.createLoadedView();
			}
		};
		setContentView(page);
		page.show();
	}

	/** 加载数据 */
	private LoadResult load() {
		DetailProtocol protocol = new DetailProtocol();
		protocol.setPackageName(mPackageName);
		mAppInfo = protocol.load(0);
		if (mAppInfo == null || StringUtils.isEmpty(mAppInfo.getPackageName())) {
			return LoadResult.ERROR;
		}
		return LoadResult.SUCCEED;
	}

	/** 获取数据后的显示的View */
	private View createLoadedView() {
		View view = UIUtils.inflate(R.layout.activity_detail);
		// 添加信息区域
		mInfoLayout = (FrameLayout) view.findViewById(R.id.detail_info);
		mInfoHolder = new AppDetailInfoHolder();
		mInfoHolder.setData(mAppInfo);
		mInfoLayout.addView(mInfoHolder.getRootView());
		// 添加安全区域
		mSafeLayout = (FrameLayout) view.findViewById(R.id.detail_safe);
		mSafeHolder = new AppDetailSafeHolder();
		mSafeHolder.setData(mAppInfo);
		mSafeLayout.addView(mSafeHolder.getRootView());
		// 截图区域
		mScreenLayout = (HorizontalScrollView) view.findViewById(R.id.detail_screen);
		mScreenHolder = new AppDetailScreenHolder();
		mScreenHolder.setData(mAppInfo);
		mScreenLayout.addView(mScreenHolder.getRootView());
		// 介绍区域
		mDesLayout = (FrameLayout) view.findViewById(R.id.detail_des);
		mDesHolder = new AppDetailDesHolder();
		mDesHolder.setData(mAppInfo);
		mDesLayout.addView(mDesHolder.getRootView());
		// 底部区域
		mBottomLayout = (FrameLayout) view.findViewById(R.id.bottom_layout);
		mBottomHolder = new AppDetailBottomHolder();
		mBottomHolder.setData(mAppInfo);
		mBottomLayout.addView(mBottomHolder.getRootView());
		mBottomHolder.startObserver();
		return view;
	}

	/** actionBar的点击事件 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
