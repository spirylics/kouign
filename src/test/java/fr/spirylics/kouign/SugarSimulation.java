package fr.spirylics.kouign;

import static com.intuit.karate.gatling.javaapi.KarateDsl.karateFeature;
import static com.intuit.karate.gatling.javaapi.KarateDsl.karateProtocol;
import static io.gatling.javaapi.core.CoreDsl.*;

import com.intuit.karate.gatling.javaapi.KarateProtocolBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import java.time.Duration;

public class SugarSimulation extends Simulation {

    public SugarSimulation()
    {
        final var users = Integer.parseInt(System.getProperty("kouign.users", "1"));
        final var during = Duration.parse(System.getProperty("kouign.during", "PT1M"));
        KarateProtocolBuilder protocol = karateProtocol();

        ScenarioBuilder randomScenario = scenario("Test random endpoint")
                .during(during).on(exec(karateFeature("classpath:fr/spirylics/kouign/sugar.feature")));

        setUp(randomScenario.injectOpen(constantUsersPerSec(users).during(Duration.ofSeconds(1)))).protocols(protocol)
                .maxDuration(during.plusSeconds(20));
    }
}
