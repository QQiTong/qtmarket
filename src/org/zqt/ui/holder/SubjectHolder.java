package org.zqt.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import org.zqt.R;
import org.zqt.bean.SubjectInfo;
import org.zqt.http.image.ImageLoader;
import org.zqt.ui.activity.BaseActivity;
import org.zqt.utils.UIUtils;

/**
 * Created by zqt on 2014/6/7.
 */
public class SubjectHolder extends BaseHolder<SubjectInfo> {

	private ImageView iv;
	private TextView tv;

	@Override
	protected View initView() {
		View view = UIUtils.inflate(R.layout.subject_item);
		iv = (ImageView) view.findViewById(R.id.item_icon);
		tv = (TextView) view.findViewById(R.id.item_txt);
		return view;
	}

	@Override
	public void refreshView() {
		SubjectInfo data = getData();
		String des = data.getDes();
		String url = data.getUrl();
		tv.setText(des);
		iv.setTag(url);
		ImageLoader.load(iv, url);
	}

	@Override
	public void recycle() {
		recycleImageView(iv);
	}
}
