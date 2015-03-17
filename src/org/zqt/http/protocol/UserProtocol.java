package org.zqt.http.protocol;

import org.zqt.bean.UserInfo;
import org.zqt.utils.LogUtils;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class UserProtocol extends BaseProtocol<List<UserInfo>> {

	@Override
	protected String getKey() {
		return "user";
	}

	@Override
	protected List<UserInfo> parseFromJson(String json) {
		try {
			List<UserInfo> list = new ArrayList<UserInfo>();
			JSONObject obj = new JSONObject(json);
			UserInfo info = new UserInfo();
			info.setName(obj.getString("name"));
			info.setEmail(obj.getString("email"));
			info.setUrl(obj.getString("url"));
			list.add(info);
			return list;
		} catch (Exception e) {
			LogUtils.e(e);
			return null;
		}
	}
}
