package com.perennialsys.bookshop.util;

import java.util.HashMap;
import java.util.Map;
//TODO: create an interface IdGenerator and make this class implement that interface
public class AutoIdGenerator{
    private static Map<String,Long> generatedIds = new HashMap<>();
    public static synchronized long next(String className){
        long nextId = 1;
        if(generatedIds.containsKey(className)){
            nextId=generatedIds.get(className)+1;
        }
        generatedIds.put(className,nextId);
        return nextId;
    }
}
