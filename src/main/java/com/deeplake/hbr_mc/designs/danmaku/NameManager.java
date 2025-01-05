package com.deeplake.hbr_mc.designs.danmaku;

import java.util.HashMap;

public class NameManager {
//    public static HashMap<Integer,String> nameMatch = new HashMap<Integer,String>();
    public static HashMap<String,String> nameMatch = new HashMap<String,String>();

    //if the name has a id, use the name as it first appears
    //if not, add it to the list
    //This is because bilibili will change the name into asterisks along time
//    public static String filterName(int id, String name)
//    {
//        if(nameMatch.containsKey(id))
//        {
//            return nameMatch.get(id);
//        }
//        else
//        {
//            nameMatch.put(id, name);
//            return name;
//        }
//    }

    //by face
    public static String filterName(String id, String name)
    {
//        return name;
        if(nameMatch.containsKey(id))
        {
            return nameMatch.get(id);
        }
        else
        {
            nameMatch.put(id, name);
            return name;
        }
    }
}
