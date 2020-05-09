package com.capstone.moayo.util;

import android.database.Cursor;
import android.util.Pair;

import com.capstone.moayo.entity.Category;
import com.capstone.moayo.entity.CategoryNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataEntityTranslator {

    // use CategoryDao
    public static CategoryNode cursorToCategoryNode(Cursor c){
        // Map<ID,Pair<parent,Object>>
        Map<Integer,Pair<Integer,CategoryNode>> nodeSet = new HashMap<>();
        CategoryNode root = null;
        while(c.moveToNext()){
            CategoryNode cn = new CategoryNode();
            cn.setId(c.getInt(0));
            cn.setDogamId(c.getInt(1));
            cn.setTitle(c.getString(2));
            cn.setParentDogamId(c.getInt(4));
            cn.setLevel(c.getInt(5));

            nodeSet.put(c.getInt(0),Pair.create(c.getInt(3),cn));
        }
        c.close();

        for(Pair<Integer,CategoryNode> p : nodeSet.values()){
            if(p.second.getId() == p.first) {
                root = p.second;
                continue;
            }

            CategoryNode parent = nodeSet.get(p.first).second;
            p.second.setParent(parent);

            if(parent.getId() != p.second.getId())
                parent.getLowLayer().add(p.second);
        }
        return root;
    }
}
