package com.BarZad.repositories;

import com.BarZad.questionhandling.Question;

import java.util.*;

public class QuestionIterator implements Iterator {
    private int index;
    private Map<String, Question> questList;
    private ArrayList<String> keyList;

    public QuestionIterator(Map<String, Question> questList) {
        this.questList = questList;
        keyList = new ArrayList<>();
        for (String key : questList.keySet()){
            keyList.add(key);
        }
    }

    @Override
    public boolean hasNext() {

        if (index < questList.size()) {
            return true;
        }
        index = 0;
        return false;
    }

    @Override
    public Object next() {

        if (this.hasNext()) {
            return questList.get(keyList.get(index++));
        }
        return null;
    }
}
