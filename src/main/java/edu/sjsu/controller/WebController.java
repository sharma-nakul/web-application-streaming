package edu.sjsu.controller;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Collections;

@Configuration
@Controller
public class WebController {

    @Autowired
    Session session;

    @RequestMapping(value = "/results", method = RequestMethod.GET)
    public ResponseEntity welcome() {
        final ResultSet rows = session.execute("SELECT * FROM word_count");

        ArrayList<WordCount> results = new ArrayList<>();

        for (Row row : rows.all()) {
            results.add(new WordCount(
                    row.getString("word"),
                    row.getInt("count")
            ));
        }

        Collections.sort(results, (a, b) ->
                b.getWord().compareTo(a.getWord()));

       return new ResponseEntity<ArrayList<WordCount>>(results, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

}