package com.zane.androidupnpdemo.service;

import android.content.Intent;
import android.os.IBinder;

import com.zane.androidupnpdemo.upnp.AndroidJettyServletContainer;

import org.fourthline.cling.UpnpServiceConfiguration;
import org.fourthline.cling.android.AndroidUpnpServiceConfiguration;
import org.fourthline.cling.android.AndroidUpnpServiceImpl;
import org.fourthline.cling.controlpoint.ControlPoint;
import org.fourthline.cling.model.meta.LocalDevice;
import org.fourthline.cling.registry.Registry;
import org.fourthline.cling.transport.impl.AsyncServletStreamServerConfigurationImpl;
import org.fourthline.cling.transport.impl.AsyncServletStreamServerImpl;
import org.fourthline.cling.transport.spi.NetworkAddressFactory;
import org.fourthline.cling.transport.spi.StreamServer;

/**
 * 说明：
 * 作者：zhouzhan
 * 日期：17/6/28 16:11
 */

public class ClingUpnpService extends AndroidUpnpServiceImpl {
    private LocalDevice mLocalDevice = null;

    @Override
    public void onCreate() {
        super.onCreate();

        //LocalBinder instead of binder
        binder = new LocalBinder();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

//    @Override
//    protected UpnpServiceConfiguration createConfiguration() {
//        return new FixedAndroidUpnpServiceConfiguration();
//    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public LocalDevice getLocalDevice() {
        return mLocalDevice;
    }

//    将 ActionCallback 放入 DefaultUpnpServiceConfiguration 中定义的线程池 ClingExecutor 执行，
//    执行完毕回调 ActionCallback 中定义的 success 或 failure 函数。
//    AndroidUpnpServiceConfiguration extends DefaultUpnpServiceConfiguration

//    private class FixedAndroidUpnpServiceConfiguration extends AndroidUpnpServiceConfiguration {
//        @Override
//        public StreamServer createStreamServer(NetworkAddressFactory networkAddressFactory) {
//            // Use Jetty, start/stop a new shared instance of JettyServletContainer
//            return new AsyncServletStreamServerImpl(
//                    new AsyncServletStreamServerConfigurationImpl(
//                            AndroidJettyServletContainer.INSTANCE,
//                            networkAddressFactory.getStreamListenPort()
//                    )
//            );
//        }
//    }

    public UpnpServiceConfiguration getConfiguration() {
        return upnpService.getConfiguration();
    }

    public Registry getRegistry() {
        return upnpService.getRegistry();
    }

    public ControlPoint getControlPoint() {
        return upnpService.getControlPoint();
    }

    public class LocalBinder extends Binder {
        public ClingUpnpService getService() {
            return ClingUpnpService.this;
        }
    }
}
