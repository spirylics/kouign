package fr.spirylics.kouign.domain.sugar;

import static org.apache.commons.lang3.ThreadUtils.sleepQuietly;

import fr.spirylics.kouign.domain.sugar.in.SugarService;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Gatherers;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

@Slf4j
@RequiredArgsConstructor
public class SugarServiceImpl implements SugarService {
    private final RandomStringUtils randomStringUtils = RandomStringUtils.insecure();
    private final Duration pause;

    @Override
    public Stream<String> randomStrings(final int size)
    {
        return IntStream.range(0, size).mapToObj(i -> {
            log.info("--> ramdom");
            sleepQuietly(pause);
            final var result = randomStringUtils.nextAlphabetic(5);
            log.info("<-- ramdom result: {}", result);
            return result;
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
