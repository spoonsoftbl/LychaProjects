package com.spoon.corelib.application;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;

import org.joda.time.DateTime;

/**
 * Created by Lycha on 9/5/2015.
 */
public class BaseApplication extends Application {

    protected static BaseApplication mLastInstance = null;

    /**
     * Get access to last instance (current one) of ThisApplication object
     * @return current instance of Application
     */
    public static BaseApplication get() {
        return mLastInstance;
    }

    /**
     * Shortcut to get application's context
     * @return current application's context
     */
    public static Context getMainContext() {
        return get().getApplicationContext();
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        mLastInstance = this;
    }

    public static PackageInfo getPackageInfo() {
        try {
            return mLastInstance.getPackageManager().getPackageInfo(mLastInstance.getPackageName(), 0);
        }
        catch (final Exception ex) {
            return null;
        }
    }

    public static String getVersionName() {
        return getPackageInfo().versionName;
    }

    public static int getVersionCode() {
        return getPackageInfo().versionCode;
    }

    public static DateTime getFirstInstallationTime() {
        return new DateTime(getPackageInfo().firstInstallTime);
    }

    public static DateTime getLastUpdateTime() {
        return new DateTime(getPackageInfo().lastUpdateTime);
    }

}
