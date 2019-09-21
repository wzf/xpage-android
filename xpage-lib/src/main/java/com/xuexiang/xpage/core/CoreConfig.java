package com.xuexiang.xpage.core;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.alibaba.fastjson.JSON;
import com.xuexiang.xpage.base.XPageActivity;
import com.xuexiang.xpage.model.PageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 全局配置类
 *
 * @author xuexiang
 * @since 2018/5/24 下午3:47
 */
public class CoreConfig {
    /**
     * Atlas支持 start
     */
    private static boolean isOpenAtlas = false;
    private static ClassLoader mBundleClassLoader = null;

    public static boolean isOpenAtlas() {
        return isOpenAtlas;
    }

    public static void setIsOpenAtlas(boolean isOpenAtlasFlag) {
        isOpenAtlas = isOpenAtlasFlag;
    }

    public static ClassLoader getBundleClassLoader() {
        return mBundleClassLoader;
    }

    public static void setBundleClassLoader(ClassLoader classLoader) {
        mBundleClassLoader = classLoader;
    }

    /**
     * Atlas支持 end
     */
    public final static String ACTION_EXIT_APP = "com.xuexiang.xpage.corepage.core.exit";
    // 本地广播退出

    private static LocalBroadcastManager mLocalBroadcastManager;
    private static Context sContext;

    /**
     * 默认初始化，配置文件在assets/corepage.json
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        sContext = context.getApplicationContext();
        CorePageManager.getInstance().init(sContext);
    }

    /**
     * 自定义初始化，配置文件信息由外部传入。
     *
     * @param context  上下文
     * @param pageJson 配置的json
     */
    public static void init(Context context, String pageJson) {
        sContext = context.getApplicationContext();
        CorePageManager.getInstance().init(sContext, pageJson);
    }

    /**
     * 自定义初始化，配置文件信息由外部传入。
     *
     * @param context      上下文
     * @param pageInfoList 配置的页面信息
     */
    public static void init(Context context, List<PageInfo> pageInfoList) {
        CoreConfig.init(context, JSON.toJSONString(pageInfoList));
    }

    /**
     * 自定义初始化，配置文件信息由外部传入。
     *
     * @param context  上下文
     * @param pageInfo 配置的页面信息
     */
    public static void init(Context context, PageInfo pageInfo) {
        List<PageInfo> list = new ArrayList<PageInfo>();
        list.add(pageInfo);
        CoreConfig.init(context, list);
    }

    public static void unInit() {
        Intent intent = new Intent();
        intent.setAction(CoreConfig.ACTION_EXIT_APP);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        getLocalBroadcastManager().sendBroadcast(intent);
        XPageActivity.unInit();
        mLocalBroadcastManager = null;
    }

    public static void readConfig(String pageJson) {
        CorePageManager.getInstance().readConfig(pageJson);
    }

    /**
     * 发送本地广播退出程序
     */
    public static void exitApp() {
        Intent intent = new Intent();
        intent.setAction(CoreConfig.ACTION_EXIT_APP);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        getLocalBroadcastManager().sendBroadcast(intent);
        XPageActivity.unInit();
    }

    /**
     * 获得LocalBroadcastManager对象
     *
     * @return LocalBroadcastManager对象
     */
    public static LocalBroadcastManager getLocalBroadcastManager() {
        if (mLocalBroadcastManager == null) {
            mLocalBroadcastManager = LocalBroadcastManager.getInstance(sContext);
        }
        return mLocalBroadcastManager;
    }

    public static Context getContext() {
        testInitialize();
        return sContext;
    }

    private static void testInitialize() {
        if (sContext == null) {
            throw new ExceptionInInitializerError("请先在全局Application中调用 PageConfig.init() 初始化！");
        }
    }
}
