package io.github.iangerolamo.travelsapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.iangerolamo.travelsapi.enumeration.TravelTypeEnum;
import io.github.iangerolamo.travelsapi.factory.TravelFactory;
import io.github.iangerolamo.travelsapi.factory.impl.TravelFactoryImpl;
import io.github.iangerolamo.travelsapi.model.Travel;


import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TravelService {

    // Parsear: extrair conteúdos de um conjunto de caracteres de forma limpa

    private TravelFactory factory;
    private List<Travel> travels;

    public void createFactory() {
        if(factory == null) {
            factory = new TravelFactoryImpl();
        }
    }

    public void createTravelList() {
        if(travels == null) {
            travels = new ArrayList<>();
        }
    }

    // para verificar se o JSON é válido

    public boolean isJSONValid(String jsonInString) {
        try {
            return new ObjectMapper().readTree(jsonInString) != null;
        } catch (IOException e) {
            return false;
        }
    }

    // para parsear o campo id do JSON

    private long parseId(JSONObject travel) {
        return Long.valueOf((int) travel.get("id"));
    }

    // para parsear o campo amount do JSON

    private BigDecimal parseAmount(JSONObject travel) {
        return new BigDecimal((String) travel.get("amount"));
    }

    // para parsear o campo startDate do JSON

    private LocalDateTime parseStartDate(JSONObject travel) {
        var startDate = (String) travel.get("startDate");
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
        return ZonedDateTime.parse(startDate, formatter.withZone(ZoneId.of("UTC"))).toLocalDateTime();
    }

    // para parsear o campo endDate do JSON

    private LocalDateTime parseEndDate(JSONObject travel) {
        var endDate = (String) travel.get("endDate");
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
        return ZonedDateTime.parse(endDate, formatter.withZone(ZoneId.of("UTC"))).toLocalDateTime();
    }

    // para verificar se a data de início da viagem é anterior a data de fim da viagem

    public boolean isStartDateGreaterThanEndDate(Travel travel) {
        if (travel.getEndDate() == null) return false;
        return travel.getStartDate().isAfter(travel.getEndDate());
    }

    // definindo os valores

    private void setTravelValues(JSONObject jsonTravel, Travel travel) {

        String orderNumber = (String) jsonTravel.get("orderNumber");
        String type = (String) jsonTravel.get("type");

        travel.setOrderNumber(orderNumber != null ? orderNumber : travel.getOrderNumber());
        travel.setAmount(jsonTravel.get("amount") != null ? parseAmount(jsonTravel) : travel.getAmount());
        travel.setStartDate(jsonTravel.get("startDate") != null ? parseStartDate(jsonTravel) : travel.getStartDate());
        travel.setEndDate(jsonTravel.get("endDate") != null ? parseEndDate(jsonTravel) : travel.getEndDate());
        travel.setType(type != null ? TravelTypeEnum.getEnum(type) : travel.getType());
    }

    // para criar uma viagem

    public Travel create(JSONObject jsonTravel) {

        createFactory();

        Travel travel = factory.createTravel((String) jsonTravel.get("type"));
        travel.setId(parseId(jsonTravel));
        setTravelValues(jsonTravel, travel);

        return travel;
    }


    // atualizar uma viagem

    public Travel update(Travel travel, JSONObject jsonTravel) {

        setTravelValues(jsonTravel, travel);
        return travel;
    }

    // para adicionar uma viagem na lista

    public void add(Travel travel) {
        createTravelList();
        travels.add(travel);
    }

    // para recuperar todas as viagens criadas

    public List<Travel> find() {
        createTravelList();
        return travels;
    }

    // para recuperar uma viagem por id

    public Travel findById(long id) {
        return travels.stream().filter(t -> id == t.getId()).collect(Collectors.toList()).get(0);
    }

    // remover viagens

    public void delete() {
        travels.clear();
    }

    public void clearObjects() {
        travels = null;
        factory = null;
    }


}
