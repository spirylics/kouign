/*
 * Copyright (C) Deveryware S.A.S. All Rights Reserved.
 */
package fr.spirylics.kouign.infrastructure.repository;

import fr.spirylics.kouign.domain.itinerary.Point;
import java.util.SequencedSet;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("")
public interface ItineraryRepository {
    SequencedSet<Point> get(String startAddress, String endAddress);
}
