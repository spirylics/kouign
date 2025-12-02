/*
 * Copyright (C) Deveryware S.A.S. All Rights Reserved.
 */
package fr.spirylics.kouign.domain.itinerary;

import fr.spirylics.kouign.domain.chat.in.ItineraryService;
import fr.spirylics.kouign.infrastructure.repository.ItineraryRepository;
import java.util.SequencedSet;

public record ItineraryServiceImpl(ItineraryRepository repository) implements ItineraryService {
    @Override
    public SequencedSet<Point> get(final String startAddress, final String endAddress)
    {
        return repository().get(startAddress, endAddress);
    }
}
