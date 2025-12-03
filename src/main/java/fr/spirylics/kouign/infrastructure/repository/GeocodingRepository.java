/*
 * Copyright (C) Deveryware S.A.S. All Rights Reserved.
 */
package fr.spirylics.kouign.infrastructure.repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface GeocodingRepository {
    @GetExchange("/search?format=json")
    List<GeocodingResponse> search(@RequestParam("q") String query);

    @JsonIgnoreProperties(ignoreUnknown = true)
    record GeocodingResponse(
        @JsonProperty("lat") String lat,
        @JsonProperty("lon") String lon,
        @JsonProperty("display_name") String displayName
    ) {
        public double latitude() {
            return Double.parseDouble(lat);
        }

        public double longitude() {
            return Double.parseDouble(lon);
        }
    }
}
