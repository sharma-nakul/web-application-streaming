package edu.sjsu.controller;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.googlecode.charts4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Collections;

import static com.googlecode.charts4j.Color.*;

@Configuration
@Controller
public class WebController {

    @Autowired
    Session session;

    @RequestMapping(value = "/results", method = RequestMethod.GET)
    public String welcome(Model model) {
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


        Plot plot1 = Plots.newBarChartPlot(Data.newData(results.get(0).getCount()),BLUEVIOLET,results.get(0).getWord());
        Plot plot2 = Plots.newBarChartPlot(Data.newData(results.get(1).getCount()),ORANGERED,results.get(1).getWord());
        Plot plot3 = Plots.newBarChartPlot(Data.newData(results.get(2).getCount()),LIMEGREEN,results.get(2).getWord());
        Plot plot4 = Plots.newBarChartPlot(Data.newData(results.get(3).getCount()),ROYALBLUE,results.get(3).getWord());
        Plot plot5 = Plots.newBarChartPlot(Data.newData(results.get(4).getCount()),ROSYBROWN,results.get(4).getWord());

        BarChart chart = GCharts.newBarChart(plot1,plot2,plot3,plot4,plot5);
        chart.setTitle("Count Vs Words", BLACK, 16);
        chart.setSize(400, 200);
        chart.setBackgroundFill(Fills.newSolidFill(ALICEBLUE));
            chart.addYAxisLabels(AxisLabelsFactory.newNumericRangeAxisLabels(0, 15));
        String barChartUrl = chart.toURLString();
        model.addAttribute("barUrl",barChartUrl);
        return "chart";
    }

}