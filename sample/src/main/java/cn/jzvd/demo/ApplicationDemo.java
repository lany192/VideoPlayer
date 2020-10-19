package cn.jzvd.demo;

import android.app.Application;
import android.content.Context;

import com.danikula.videocache.HttpProxyCacheServer;

/**
 * 能不能干掉这个类，非常想干掉这个类，可以不可以移到MainActivity
 * Created by Nathen
 * On 2015/12/01 11:29
 */
public class ApplicationDemo extends Application {

    private HttpProxyCacheServer proxy;

    public static HttpProxyCacheServer getProxy(Context context) {
        ApplicationDemo app = (ApplicationDemo) context.getApplicationContext();
        return app.proxy == null ? (app.proxy = app.newProxy()) : app.proxy;
    }

    private HttpProxyCacheServer newProxy() {
        return new HttpProxyCacheServer(this);
    }
}
