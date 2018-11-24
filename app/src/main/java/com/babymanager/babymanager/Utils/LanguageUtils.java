package com.babymanager.babymanager.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import java.util.Locale;

public class LanguageUtils {
    private Context context;

    public LanguageUtils(Context context) {
        this.context = context;
    }

    public void setLocal(Context context, String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Locale.getDefault().getDisplayLanguage();
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        context.getResources().updateConfiguration(configuration,context.getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = context.getSharedPreferences(Constant.SETTINGS,context.MODE_PRIVATE).edit();
        editor.putString(Constant.MY_LANG,lang);
        editor.apply();
    }

    public void loadLocale(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.SETTINGS,context.MODE_PRIVATE);
        String language = sharedPreferences.getString(Constant.MY_LANG,"");
        setLocal(context,language);
    }
}
