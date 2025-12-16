package fr.spirylics.kouign.domain.sugar;

import fr.spirylics.kouign.domain.sugar.in.SugarService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Gatherers;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import lombok.Lombok;
import org.apache.commons.lang3.RandomStringUtils;

import static java.lang.Thread.sleep;

public class SugarServiceImpl implements SugarService {
    private final RandomStringUtils randomStringUtils = RandomStringUtils.insecure();

    @Override
    public Stream<String> randomStrings(final int size)
    {
        return IntStream.range(0, size).mapToObj(i -> {
            try {
                sleep(100);
                return randomStringUtils.nextAlphabetic(5);
            } catch (final InterruptedException e) {
                Thread.currentThread().interrupt();
                throw Lombok.sneakyThrow(e);
            }
        });
    }

    @Override
    public Stream<List<String>> randomStrings(final int size, final int windowSize)
    {
        return randomStrings(size).gather(Gatherers.windowFixed(windowSize));
    }

    public Stream<List<String>> legacyRandomStrings(final int size, final int windowSize)
    {
        final List<String> randomStrings = randomStrings(size).toList();
        final Collection<List<String>> lots = new ArrayList<>();

        for (int i = 0; i < randomStrings.size(); i += windowSize) {
            final var end = Math.min(randomStrings.size(), i + windowSize);
            lots.add(randomStrings.subList(i, end));
        }
        return lots.stream();
    }
}
