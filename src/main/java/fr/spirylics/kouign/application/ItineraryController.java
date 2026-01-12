package fr.spirylics.kouign.application;

import fr.spirylics.kouign.domain.chat.in.ItineraryService;
import fr.spirylics.kouign.domain.itinerary.Point;
import java.util.SequencedSet;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/{version}/itinerary")
@RequiredArgsConstructor
public class ItineraryController {
    private final ItineraryService itineraryService;

    @GetMapping(version = "1.0")
    SequencedSet<Point> get(final String startAddress, final String endAddress)
    {
        return itineraryService.get(startAddress, endAddress);
    }
}
