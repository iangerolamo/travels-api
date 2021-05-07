package io.github.iangerolamo.travelsapi.factory;

import io.github.iangerolamo.travelsapi.model.Travel;

public interface TravelFactory {

    Travel createTravel (String type);
}
