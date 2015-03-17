package org.zqt.ui.holder;

import android.view.View;
import android.widget.TextView;
import org.zqt.R;
import org.zqt.bean.CategoryInfo;
import org.zqt.ui.activity.BaseActivity;
import org.zqt.utils.UIUtils;

/**
 * Created by zqt on 2014/6/7.
 */
public class CategoryTitleHolder extends BaseHolder<CategoryInfo> {
	private TextView mTextView;

	@Override
	protected View initView() {
		View view = UIUtils.inflate(R.layout.category_item_title);
		mTextView = (TextView) view.findViewById(R.id.tv_title);
		return view;
	}

	@Override
	public void refreshView() {
		mTextView.setText(getData().getTitle());
	}
}
