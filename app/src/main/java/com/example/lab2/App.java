package com.example.lab2;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    public static final int DB_VERSION = 1;

    private static App instance;
    private static Realm realm;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        RealmConfiguration configuration = new RealmConfiguration.Builder(getApplicationContext())
                .name("app.realm")
                .setModules(Realm.getDefaultModule())
                .schemaVersion(DB_VERSION)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
        realm = Realm.getInstance(configuration);
    }

    public static Realm getRealm() {
        return realm;
    }

    public static App getInstance() {
        return instance;
    }
}
