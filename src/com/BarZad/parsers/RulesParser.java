package com.BarZad.parsers;

import com.BarZad.questionhandling.Answer;
import com.BarZad.questionhandling.Question;
import com.BarZad.repositories.RuleRepository;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

public class RulesParser extends XMLParser {
    private RuleRepository ruleRepo;

    public RulesParser(String xmlPath){
        loadXMLDocument(xmlPath);
        ruleRepo = new RuleRepository();
        addQuestions();
    }

    public RuleRepository getRuleRepository(){
        return ruleRepo;
    }

    private Question makeQuestion(Node questionData){
            Element eElement = (Element) questionData;
            String id = eElement.getAttribute("id");
            String question = eElement.getElementsByTagName("Question").item(0).getTextContent();
            NodeList answerData = eElement.getElementsByTagName("Selection");
            Answer answer = makeAnswer(answerData);
        return new Question(id, question, answer);
    }

    private Answer makeAnswer(NodeList answerData){
        List<String> paramList = new ArrayList<>();
        List<Boolean> selectionTypeList = new ArrayList<>();
        String valueType = null;
        for (int temp = 0; temp <answerData.getLength(); temp++){
            Element element = (Element) answerData.item(temp);
            selectionTypeList.add(Boolean.parseBoolean(element.getAttribute("value")));
            Element selection = ((Element) element.getChildNodes().item(1));
            paramList.add(selection.getAttribute("value"));
            valueType = selection.getTagName();
        }
        return new Answer(paramList, selectionTypeList, valueType);
    }

    private void addQuestions(){
        NodeList nList = getParsedXML().getElementsByTagName("Rule");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node questionData = nList.item(temp);
            if (questionData.getNodeType() == Node.ELEMENT_NODE) {
                ruleRepo.addQuestion(makeQuestion(questionData));
            }
        }
    }
}
