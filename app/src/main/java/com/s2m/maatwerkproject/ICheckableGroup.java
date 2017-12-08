package com.s2m.maatwerkproject;

import android.widget.CheckBox;

import com.s2m.maatwerkproject.models.Group;

public interface ICheckableGroup {
    void onCheckPickGroupItem(Group group);
    void onClickImageViewShowGroupInfo(Group group);
}
