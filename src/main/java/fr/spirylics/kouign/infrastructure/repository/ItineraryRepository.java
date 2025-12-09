/*
 * Copyright (C) Deveryware S.A.S. All Rights Reserved.
 */
package fr.spirylics.kouign.infrastructure.repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.spirylics.kouign.domain.itinerary.Point;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.SequencedSet;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface ItineraryRepository {
    @GetExchange("/route/v1/driving/{coordinates}?geometries=geojson&overview=full")
    OsrmResponse route(@PathVariable String coordinates);

    default SequencedSet<Point> get(String coordinates)
    {
        OsrmResponse response = route(coordinates);

        if (response.routes() == null || response.routes().isEmpty()) {
            return new LinkedHashSet<>();
        }

        Route firstRoute = response.routes().getFirst();
        if (firstRoute.geometry() == null || firstRoute.geometry().coordinates() == null) {
            return new LinkedHashSet<>();
        }

        return firstRoute.geometry().coordinates().stream().map(coord -> new Point(coord.get(1), coord.get(0)))
                .collect(LinkedHashSet::new, LinkedHashSet::add, LinkedHashSet::addAll);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    record OsrmResponse(String code, List<Route> routes) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    record Route(double distance, double duration, Geometry geometry) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    record Geometry(String type, List<List<Double>> coordinates) {
    }
}
