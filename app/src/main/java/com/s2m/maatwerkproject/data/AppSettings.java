package com.s2m.maatwerkproject.data;

import com.s2m.maatwerkproject.data.models.Setting;

import java.util.ArrayList;
import java.util.List;

public final class AppSettings {

    public static final int PHONE_NUMBER_SETTING_ID = 101;
    public static final int EMAIL_SETTING_ID = 102;
    public static final int PASSWORD_SETTING_ID = 103;
    public static final int FONT_SETTING_ID = 201;
    public static final int BACKGROUND_SETTING_ID = 202;
    public static final int SOUND_SETTING_ID = 301;
    public static final int VIBRATION_SETTING_ID = 302;
    public static final int LED_SETTING_ID = 303;

    private static final Setting[] SETTINGS = new Setting[]{
        new Setting("Change phone number", PHONE_NUMBER_SETTING_ID, Setting.SettingType.Account),
        new Setting("Change email address", EMAIL_SETTING_ID, Setting.SettingType.Account),
        new Setting("Change password", PASSWORD_SETTING_ID, Setting.SettingType.Account),
        new Setting("Font size", FONT_SETTING_ID, Setting.SettingType.Chat),
        new Setting("Background image", BACKGROUND_SETTING_ID, Setting.SettingType.Chat),
        new Setting("Sound", SOUND_SETTING_ID, Setting.SettingType.Notification),
        new Setting("Vibration", VIBRATION_SETTING_ID, Setting.SettingType.Notification),
        new Setting("LED Color", LED_SETTING_ID, Setting.SettingType.Notification)
    };

    public static Setting[] getSettingsArray(Setting.SettingType settingType){
        List<Setting> filteredSettings = new ArrayList<>();
        for(Setting setting : SETTINGS){
            if(setting.getSettingType() == settingType){
                filteredSettings.add(setting);
            }
        }
        return filteredSettings.toArray(new Setting[filteredSettings.size()]);
    }
}
