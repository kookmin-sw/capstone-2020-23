package com.capstone.moayo.util.retrofit;

import android.database.Cursor;
import android.util.Pair;

import com.capstone.moayo.entity.CategoryNode;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataEntityTranslator {

    // use CategoryDao
    public CategoryNode cursorToCategoryNode(Cursor c){
        // Map<ID,Pair<parent,Object>>
        Map<Integer,Pair<Integer,CategoryNode>> nodeSet = new HashMap<>();
        CategoryNode root = null;
        while(c.moveToNext()){
            CategoryNode cn = new CategoryNode();
            cn.setId(c.getInt(0));
            cn.setTitle(c.getString(1));
            cn.setLevel(c.getInt(3));

            nodeSet.put(c.getInt(0),Pair.create(c.getInt(2),cn));
        }
        c.close();

        for(Pair<Integer,CategoryNode> p : nodeSet.values()){
            CategoryNode parent = nodeSet.get(p.first).second;
            p.second.setParent(parent);

            if(parent.getId() != p.second.getId())
                parent.getLowLayer().add(p.second);
            if(p.second.getId() == p.first)
                root = p.second;
        }
        return root;
    }
}
