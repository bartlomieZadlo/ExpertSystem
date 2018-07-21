package com.BarZad;

import com.BarZad.facts.Fact;
import com.BarZad.parsers.FactParser;
import com.BarZad.parsers.RulesParser;
import com.BarZad.questionhandling.Question;

import java.util.*;

public class ESProvider {
    private FactParser factParser;
    private RulesParser rulesParser;
    private Map<String, Boolean> answers;
    private Scanner keyboard;

    public ESProvider(FactParser factParser, RulesParser rulesParser) {
        this.factParser = factParser;
        this.rulesParser = rulesParser;
        keyboard = new Scanner(System.in);
    }

    public void collectAnswers() {
        answers = new HashMap<>();
        Iterator<Question> qIter = rulesParser.getRuleRepository().getIterator();
        while (qIter.hasNext()) {
            Question question = qIter.next();
            addAnswer(question);
        }
    }

    private void addAnswer(Question question) {
        boolean inputRight;
        do {
            try {
                System.out.println(question.getQuestion());
                System.out.println(question.getAnswer().getValues().get(0).getInputPattern() + "or" + question.getAnswer().getValues().get(1).getInputPattern());
                String input = keyboard.nextLine();
                answers.put(question.getId(), question.getEvaluatedAnswer(input));
                inputRight = true;
            } catch (InputMismatchException e) {
                System.out.print(e);
                inputRight = false;
            }
        }while (!inputRight);
    }

    public boolean getAnswerByQuestionID(String questionId){
        return answers.get(questionId);
    }

    public String evaluate(){
    String evaluation = null;
        Iterator<Fact> fIter = factParser.getFactRepository().getIterator();
        while (fIter.hasNext()){
            Fact fact = fIter.next();
            if (compareAnswersToFact(fact)) evaluation = fact.getDescription();
        }
    return evaluation;
    }

    private boolean compareAnswersToFact(Fact fact){
        boolean compare = true;
        for (String answer : answers.keySet()){
            if(!(fact.getValueById(answer) == answers.get(answer))) compare = false;
        }
        return compare;
    }
}