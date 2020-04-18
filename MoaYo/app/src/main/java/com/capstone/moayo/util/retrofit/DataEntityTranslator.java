package com.capstone.moayo.util.retrofit;

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

    public CategoryNode cursorToNode(Cursor c) {
        CategoryNode rootNode = null;
        List<Pair<Integer, CategoryNode>> second_layer = new ArrayList<>();
        List<Pair<Integer, CategoryNode>> third_layer = new ArrayList<>();
        while(c.moveToNext()) {
            CategoryNode node = new CategoryNode();
            node.setId(c.getInt(0));
            node.setTitle(c.getString(1));
            node.setLevel(c.getInt(3));
            Pair<Integer, CategoryNode> pair = new Pair<>(c.getInt(2), node);
            switch (node.getLevel()) {
                case 1:
                    rootNode = node;
                    break;
                case 2:
                    second_layer.add(pair);
                    break;
                case 3:
                    third_layer.add(pair);
                    break;
                default:
                    break;
            }
        }
        c.close();
        for(Pair<Integer, CategoryNode> second_pair : second_layer) {
            CategoryNode secondNode = second_pair.second;
            if(second_pair.first.equals(rootNode.getId())) {
                rootNode.getLowLayer().add(secondNode);
                secondNode.setParent(rootNode);
                second_layer.remove(second_pair);
            }
            for(Pair<Integer, CategoryNode> third_pair : third_layer) {
                CategoryNode thirdNode = third_pair.second;
                if(third_pair.first.equals(secondNode.getId())) {
                    secondNode.getLowLayer().add(thirdNode);
                    thirdNode.setParent(secondNode);
                    third_layer.remove(third_pair);
                }
            }
        }
        return rootNode;
    }
}
