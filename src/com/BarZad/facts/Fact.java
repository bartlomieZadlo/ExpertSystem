package com.BarZad.facts;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Fact {
    private String id;
    private String description;
    private Set<String> idSet;
    private Map<String, Boolean> idValueList;

    public Fact(String id, String description){
    this.id = id;
    this.description = description;
    idValueList = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Set<String> getIdSet() {
        return idSet;
    }

    public void setIdSet(Set<String> idSet) {
        this.idSet = idSet;
    }

    public void setFactValueById(String id, boolean value){
        idValueList.put(id, value);
    }

    public boolean getValueById(String id){
        return idValueList.get(id);
    }
}
