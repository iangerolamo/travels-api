package io.github.iangerolamo.travelsapi.factory.impl;

import io.github.iangerolamo.travelsapi.enumeration.TravelTypeEnum;
import io.github.iangerolamo.travelsapi.factory.TravelFactory;
import io.github.iangerolamo.travelsapi.model.Travel;

public class TravelFactoryImpl implements TravelFactory {

    @Override
    public Travel createTravel (String type) {

        if (TravelTypeEnum.ONE_WAY.getValue().equals(type)) {
            return new Travel(TravelTypeEnum.ONE_WAY);
        } else if (TravelTypeEnum.MULTI_CITY.getValue().equals(type)) {
            return new Travel(TravelTypeEnum.MULTI_CITY);
        }

        return new Travel(TravelTypeEnum.RETURN);
    }
}
