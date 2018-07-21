package com.BarZad.parsers;

import com.BarZad.facts.Fact;
import com.BarZad.repositories.FactRepository;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.HashSet;
import java.util.Set;

public class FactParser extends XMLParser {
    private FactRepository factRepo;

    public FactParser(String xmlPath){
        loadXMLDocument(xmlPath);
        factRepo = new FactRepository();
        addFacts();
    }

    public FactRepository getFactRepository(){
        return factRepo;
    }

    private void addFacts(){
        NodeList nList = getParsedXML().getElementsByTagName("Fact");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node factData = nList.item(temp);
            if (factData.getNodeType() == Node.ELEMENT_NODE) {
                factRepo.addFact(makeFact(factData));
            }
        }
    }

    private Fact makeFact(Node factData){
        Element eElement = (Element) factData;
        String id = eElement.getAttribute("id");
        Element description = (Element) (eElement.getElementsByTagName("Description").item(0));
        String descriptionValue = description.getAttribute("value");
        Fact fact = new Fact(id, descriptionValue);
        fillFactFields(fact, factData);
        return fact;
    }

    private void fillFactFields(Fact fact, Node factData){
        NodeList nodeList = (((Element) factData).getElementsByTagName("Eval"));
        Set<String> tempSet = new HashSet<>();
        for (int temp = 0; temp <nodeList.getLength(); temp++){
            Element element = (Element) nodeList.item(temp);
            String id = element.getAttribute("id");
            tempSet.add(id);
            boolean value = Boolean.parseBoolean(element.getTextContent());
            fact.setFactValueById(id, value);
        }
        fact.setIdSet(tempSet);
    }

}
