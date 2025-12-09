/*
 * Copyright (C) Deveryware S.A.S. All Rights Reserved.
 */
package fr.spirylics.kouign.domain.itinerary;

import fr.spirylics.kouign.domain.chat.in.ItineraryService;
import fr.spirylics.kouign.infrastructure.repository.GeocodingRepository;
import fr.spirylics.kouign.infrastructure.repository.ItineraryRepository;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.SequencedSet;

public record ItineraryServiceImpl(ItineraryRepository itineraryRepository, GeocodingRepository geocodingRepository)
        implements ItineraryService {
    @Override
    public SequencedSet<Point> get(final String startAddress, final String endAddress)
    {
        List<GeocodingRepository.GeocodingResponse> startResults = geocodingRepository.search(startAddress);
        if (startResults == null || startResults.isEmpty()) {
            return new LinkedHashSet<>();
        }

        List<GeocodingRepository.GeocodingResponse> endResults = geocodingRepository.search(endAddress);
        if (endResults == null || endResults.isEmpty()) {
            return new LinkedHashSet<>();
        }

        GeocodingRepository.GeocodingResponse startCoord = startResults.getFirst();
        GeocodingRepository.GeocodingResponse endCoord = endResults.getFirst();

        String coordinates = String.format(Locale.US, "%f,%f;%f,%f", startCoord.longitude(), startCoord.latitude(),
                endCoord.longitude(), endCoord.latitude());

        return itineraryRepository.get(coordinates);
    }
}
