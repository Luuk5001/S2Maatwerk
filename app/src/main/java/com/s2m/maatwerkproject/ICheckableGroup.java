package com.s2m.maatwerkproject;

import com.s2m.maatwerkproject.data.models.Group;

public interface ICheckableGroup {
    void onCheckPickGroupItem(Group group);
    void onClickImageViewShowGroupInfo(Group group);
}
