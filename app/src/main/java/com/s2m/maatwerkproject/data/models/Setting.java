package com.s2m.maatwerkproject.data.models;

import com.s2m.maatwerkproject.utils.VariableAndroidListItemAdapter;

public class Setting implements VariableAndroidListItemAdapter.VariableListItem{

    public enum SettingType{
        Account,
        Chat,
        Notification
    }

    private String name;
    private String currentSetting;
    private Class action;
    private int settingId;
    private SettingType settingType;

    public Setting(String name, int settingId, SettingType settingType) {
        this.name = name;
        this.action = action;
        this.settingType = settingType;
        this.settingId = settingId;
    }

    public Setting(String name, Class action, int settingId, SettingType settingType) {
        this.name = name;
        this.action = action;
        this.settingId=settingId;
        this.settingType = settingType;
    }

    public void setCurrentSetting(String currentSetting) {
        this.currentSetting = currentSetting;
    }

    public String getCurrentSetting() {
        return currentSetting;
    }

    public SettingType getSettingType() {
        return settingType;
    }

    @Override
    public String getTextView1Text() {
        return name;
    }

    @Override
    public String getTextView2Text() {
        return currentSetting;
    }

    @Override
    public int getItemId() {
        return settingId;
    }
}
