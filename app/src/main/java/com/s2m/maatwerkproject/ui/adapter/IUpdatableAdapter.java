package com.s2m.maatwerkproject.ui.adapter;

import java.util.List;

public interface IUpdatableAdapter<T> {
    void refreshData(List<T> data);
    void addItem(T item);
}
