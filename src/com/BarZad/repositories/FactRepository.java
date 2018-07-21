package com.BarZad.repositories;

import com.BarZad.facts.Fact;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FactRepository {
    private Map<String, Fact> factRepo;
    private Iterator<Fact> factIterator;

    public FactRepository(){
        factRepo = new HashMap<>();
    }

    public void addFact(Fact fact){
        factRepo.put(fact.getId(), fact);
        factIterator = new FactIterator(factRepo);
    }

    public Iterator<Fact> getIterator(){
        return factIterator;
    }
}
