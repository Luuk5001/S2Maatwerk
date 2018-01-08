package com.s2m.maatwerkproject.utils;

import java.util.ArrayList;


public class NonDuplicateList<E> extends ArrayList<E> {

    @Override
    public boolean add(E o) {
        if(this.contains(o)){
            this.set(this.indexOf(o), o);
            return true;
        }
        else{
            return super.add(o);
        }
    }
}
