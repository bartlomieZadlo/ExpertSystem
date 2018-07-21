package com.BarZad.questionhandling;

import java.util.ArrayList;

public class SingleValue extends Value {

    public SingleValue(String param, boolean selectionType){
        setInputPattern(new ArrayList<>());
        setSelectionType(selectionType);
        getInputPattern().add(param);
    }
}
