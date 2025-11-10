package fr.spirylics.kouign.domain.model;

import java.time.Instant;

public record Model(
        String id,
        String name,
        Instant created,
        String ownedBy
) {
}
