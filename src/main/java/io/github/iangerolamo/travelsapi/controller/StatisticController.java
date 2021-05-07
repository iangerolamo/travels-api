package io.github.iangerolamo.travelsapi.controller;

import io.github.iangerolamo.travelsapi.model.Statistic;
import io.github.iangerolamo.travelsapi.model.Travel;
import io.github.iangerolamo.travelsapi.service.StatisticService;
import io.github.iangerolamo.travelsapi.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.log4j.Logger;

import java.util.List;

@RestController
@RequestMapping("/api-travels/statistics")
public class StatisticController {

    private static final Logger logger = Logger.getLogger(StatisticController.class);

    @Autowired
    private TravelService travelService;

    @Autowired
    private StatisticService statisticsService;

    @GetMapping(produces = { "application/json" })
    public ResponseEntity<Statistic> getStatistics() {

        List<Travel> travels = travelService.find();
        Statistic statistics = statisticsService.create(travels);

        logger.info(statistics);

        return ResponseEntity.ok(statistics);
    }
}
