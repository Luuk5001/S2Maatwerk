package com.s2m.maatwerkproject.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.s2m.maatwerkproject.R;
import com.s2m.maatwerkproject.data.AppSettings;
import com.s2m.maatwerkproject.data.models.Setting;
import com.s2m.maatwerkproject.utils.VariableAndroidListItemAdapter;

import butterknife.ButterKnife;

public class ChatSettingsFragment extends ListFragment {
    private View containerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_settings, container, false);
        ButterKnife.bind(this, view);
        this.containerView = view;

        setListAdapter(new VariableAndroidListItemAdapter(view.getContext(), AppSettings.getSettingsArray(Setting.SettingType.Chat)));

        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent;
        //TODO start dialogs
        switch ((int)id){
            case AppSettings.FONT_SETTING_ID:
                break;
            case AppSettings.BACKGROUND_SETTING_ID:
                break;
        }
    }
}
