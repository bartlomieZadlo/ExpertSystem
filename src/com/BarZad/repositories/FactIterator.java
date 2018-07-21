package com.BarZad.repositories;

import com.BarZad.facts.Fact;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FactIterator implements Iterator {
    private int index;
    private Map<String, Fact> factList;
    private List<String> keyList;

    public FactIterator(Map<String, Fact> questList) {
        this.factList = questList;
        keyList = new ArrayList<>();
        for (String key : factList.keySet()){
            keyList.add(key);
        }
    }

    @Override
    public boolean hasNext() {

        if (index < factList.size()) {
            return true;
        }
        index = 0;
        return false;
    }

    @Override
    public Object next() {

        if (this.hasNext()) {
            return factList.get(keyList.get(index++));
        }
        return null;
    }
}
