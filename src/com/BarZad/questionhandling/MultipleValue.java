package com.BarZad.questionhandling;

import java.util.ArrayList;
import java.util.List;

public class MultipleValue extends Value {
    public MultipleValue(String params, boolean selectionType){
        setInputPattern(new ArrayList<>());
        setSelectionType(selectionType);
        String[] splittedParams = params.split(",");
        for (String param : splittedParams){
            getInputPattern().add(param);
        }
    }
}
