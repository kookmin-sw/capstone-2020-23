package com.moayo.server.service;

import com.moayo.server.Entity.Book;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ShareService {

    DocumentBuilderFactory docFactory;
    DocumentBuilder docBuilder;

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

    public Document xmlParser(InputStream body){
        if(docBuilder == null){
            if(docFactory == null){
                docFactory = DocumentBuilderFactory.newInstance();
            }
            try {
                docBuilder = docFactory.newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
        }

        try {
            Document doc = docBuilder.parse(body);
            return doc;
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Exception log add.
        return null;
    }

    public void uploadBook(Book book){

    }
}
