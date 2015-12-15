package com.zwli.research.ref;

import java.util.HashMap;
import java.util.Map;

public class CacheTest {

    private Map<String, Object> myAwesomeCache = new HashMap<String, Object>(100);

    public Object getData(String id) {
        Object objToReturn = null;
        if (myAwesomeCache.containsKey(id)) {
            objToReturn = myAwesomeCache.get(id);
        } else {
            // retrieve from the database and populate the in memory cache map
        }
        return objToReturn;
    }
}
