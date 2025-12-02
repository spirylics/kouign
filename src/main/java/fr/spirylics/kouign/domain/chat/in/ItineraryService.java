/*
 * Copyright (C) Deveryware S.A.S. All Rights Reserved.
 */
package fr.spirylics.kouign.domain.chat.in;

import fr.spirylics.kouign.domain.itinerary.Point;
import java.util.SequencedSet;

public interface ItineraryService {
    SequencedSet<Point> get(String startAddress, String endAddress);
}
