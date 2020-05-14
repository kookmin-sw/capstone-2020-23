package com.moayo.server.controller;


import com.moayo.server.model.*;
import com.moayo.server.service.concrete.ShareService;
import com.moayo.server.service.XMLParsingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import util.XMLParsing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@RestController
public class MainController {
    @Autowired
    ShareService service;
    @Autowired
    XMLParsingService xmlParsingService;

    @RequestMapping(value = "/xmlParsing",method = RequestMethod.POST)
    public DogamListModel xmlParsing(HttpServletResponse res,HttpServletRequest req,@RequestBody String body) throws IOException, SAXException, ParserConfigurationException {
        Document doc = XMLParsing.XMLParsing(body);
        DogamListModel dogamListModel = xmlParsingService.insertData(doc);
        return dogamListModel;
    }

    @RequestMapping(value = "/getDogam",method = RequestMethod.POST)
    public DogamModel getDogam(HttpServletRequest req,HttpServletResponse res,@RequestParam int dogamId){
        return service.getDogam(dogamId);
    }

//    @RequestMapping(value = "/getDogamList",method = RequestMethod.POST)
//    public DogamListModel[] getDogamList(@RequestParam String hashtag){
//
//    }
}
