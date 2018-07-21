package com.BarZad.questionhandling;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class Answer {
    private List<Value> values;
    private boolean evaluation;
    private boolean checkSuccesful;

    public Answer(List<String> params, List<Boolean> selectionTypes, String valueType) {
        values = new ArrayList<>();
        for (int i = 0; i<params.size(); i++){
            addValue(params.get(i), selectionTypes.get(i), valueType);
        }
    }

    public boolean evaluateAnswerByInput(String input){
        for (Value value : values){
            compareInput(value, input);
        }
        if (!checkSuccesful){
            throw new InputMismatchException("No such value");
        }
        return evaluation;
    }

    private void compareInput(Value value, String input){
        for (String answer : value.getInputPattern()) {
            if (answer.equals(input)){
                evaluation = value.getSelectionType();
                checkSuccesful = true;
            }
        }
    }

    private void addValue(String param, Boolean selectionType, String valueType){
        if (valueType.equals("SingleValue")){
            values.add(new SingleValue(param, selectionType));
        }
        else {
            values.add(new MultipleValue(param, selectionType));
        }
    }

    public List<Value> getValues() {
        return values;
    }
}
