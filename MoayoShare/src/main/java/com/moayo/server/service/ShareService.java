package com.moayo.server.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

@Service
public class ShareService {

    public JSONObject jsonParser(String body){
        try{
            JSONParser jsonparser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonparser.parse(body);

            return jsonObject;
        }catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
