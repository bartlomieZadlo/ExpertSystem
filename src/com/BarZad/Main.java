package com.BarZad;

import com.BarZad.parsers.FactParser;
import com.BarZad.parsers.RulesParser;

public class Main {

    public static void main(String[] args) {
        FactParser factParser = new FactParser("Resources/Facts.xml");
        RulesParser rulesParser = new RulesParser("Resources/Rules.xml");
        ESProvider esProvider = new ESProvider(factParser, rulesParser);
        esProvider.collectAnswers();
        System.out.print(esProvider.evaluate());
    }
}
