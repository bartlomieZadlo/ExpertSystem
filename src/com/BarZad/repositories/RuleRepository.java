package com.BarZad.repositories;

import com.BarZad.questionhandling.Question;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RuleRepository {
    private Map<String, Question> ruleRepo;
    private Iterator<Question> questionIterator;

    public RuleRepository(){
        ruleRepo = new HashMap<>();
    }

    public void addQuestion(Question question){
        ruleRepo.put(question.getId(), question);
        questionIterator= new QuestionIterator(ruleRepo);
    }

    public Iterator<Question> getIterator(){
        return questionIterator;
    }
}
