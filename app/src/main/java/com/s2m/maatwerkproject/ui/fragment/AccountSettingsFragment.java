package com.s2m.maatwerkproject.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.s2m.maatwerkproject.data.AppSettings;
import com.s2m.maatwerkproject.ui.activity.ChangeEmailActivity;
import com.s2m.maatwerkproject.ui.activity.ChangePasswordActivity;
import com.s2m.maatwerkproject.ui.activity.ChangePhoneNumberActivity;
import com.s2m.maatwerkproject.utils.VariableAndroidListItemAdapter;
import com.s2m.maatwerkproject.R;
import com.s2m.maatwerkproject.models.Setting;
import butterknife.ButterKnife;

public class AccountSettingsFragment extends ListFragment{

    private View containerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_settings, container, false);
        ButterKnife.bind(this, view);
        this.containerView = view;

        Setting[] settings =  AppSettings.getSettingsArray(Setting.SettingType.Account);
        settings[0].setCurrentSetting("Luuk5001@gmail.com");

        setListAdapter(new VariableAndroidListItemAdapter(view.getContext(),settings));

        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent;
        switch ((int)id){
            case AppSettings.PHONE_NUMBER_SETTING_ID:
                intent = new Intent(containerView.getContext(), ChangePhoneNumberActivity.class);
                startActivity(intent);
                break;
            case AppSettings.EMAIL_SETTING_ID:
                intent = new Intent(containerView.getContext(), ChangeEmailActivity.class);
                startActivity(intent);
                break;
            case AppSettings.PASSWORD_SETTING_ID:
                intent = new Intent(containerView.getContext(), ChangePasswordActivity.class);
                startActivity(intent);
                break;
        }
    }
}
