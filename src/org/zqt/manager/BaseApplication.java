package org.zqt.manager;

import android.app.Application;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

/**
 * Created by zqt on 2014/7/11.
 */
public class BaseApplication extends Application {
	/** 全局Context，原理是因为Application类是应用最先运行的，所以在我们的代码调用时，该值已经被赋值过了 */
	private static BaseApplication mInstance;
	/** 主线程ID */
	private static int mMainThreadId = -1;
	/** 主线程 */
	private static Thread mMainThread;
	/** 主线程Handler */
	private static Handler mMainThreadHandler;
	/** 主线程Looper */
	private static Looper mMainLooper;

	@Override
	public void onCreate() {
		//android.os.Process.myTid()  获取调用进程的id
		//android.os.Process.myUid() 获取 该进程的用户id
		//android.os.Process.myPid() 获取进程的id
		mMainThreadId = android.os.Process.myTid();
		mMainThread = Thread.currentThread();
		mMainThreadHandler = new Handler();
		mMainLooper = getMainLooper();
		mInstance = this;
		super.onCreate();
//		mMainThreadHandler.sendMessage(msg)
//		HandlerThread
//		Looper.prepare()
	}

	public static BaseApplication getApplication() {
		return mInstance;
	}

	/** 获取主线程ID */
	public static int getMainThreadId() {
		return mMainThreadId;
	}

	/** 获取主线程 */
	public static Thread getMainThread() {
		return mMainThread;
	}

	/** 获取主线程的handler */
	public static Handler getMainThreadHandler() {
		return mMainThreadHandler;
	}

	/** 获取主线程的looper */
	public static Looper getMainThreadLooper() {
		return mMainLooper;
	}
}
