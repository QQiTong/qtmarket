package org.zqt.ui.fragment;

import java.util.List;

import org.zqt.bean.SubjectInfo;
import org.zqt.http.protocol.SubjectProtocol;
import org.zqt.ui.adapter.DefaultAdapter;
import org.zqt.ui.holder.BaseHolder;
import org.zqt.ui.holder.SubjectHolder;
import org.zqt.ui.widget.LoadingPage.LoadResult;
import org.zqt.ui.widget.BaseListView;
import android.view.View;
import android.widget.AbsListView;
import org.zqt.utils.UIUtils;

public class SubjectFragment extends BaseFragment {
	private BaseListView mListView;
	private List<SubjectInfo> mDatas;
	private SubjectAdapter mAdapter;

	@Override
	protected LoadResult load() {
		SubjectProtocol protocol = new SubjectProtocol();
		mDatas = protocol.load(0);
		return check(mDatas);
	}

	@Override
	protected View createLoadedView() {
		mListView = new BaseListView(UIUtils.getContext());
		mAdapter = new SubjectAdapter(mListView, mDatas);
		mListView.setAdapter(mAdapter);
		return mListView;
	}

	class SubjectAdapter extends DefaultAdapter<SubjectInfo> {

		public SubjectAdapter(AbsListView listView, List<SubjectInfo> datas) {
			super(listView, datas);
		}

		@Override
		public boolean hasMore() {
			return true;
		}

		@Override
		public BaseHolder getHolder() {
			return new SubjectHolder();
		}

		/** 加载更多 */
		@Override
		public List<SubjectInfo> onLoadMore() {
			SubjectProtocol protocol = new SubjectProtocol();
			return protocol.load(getData().size());
		}

		@Override
		public void onItemClickInner(int position) {
			UIUtils.showToastSafe(getItem(position).getDes());
		}
	}
}
