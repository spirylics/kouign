package fr.spirylics.kouign;

import static com.intuit.karate.gatling.javaapi.KarateDsl.karateFeature;
import static com.intuit.karate.gatling.javaapi.KarateDsl.karateProtocol;
import static io.gatling.javaapi.core.CoreDsl.constantUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.scenario;

import com.intuit.karate.gatling.javaapi.KarateProtocolBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import java.time.Duration;

public class SugarSimulation extends Simulation {

    public SugarSimulation()
    {
        KarateProtocolBuilder protocol = karateProtocol();

        ScenarioBuilder randomScenario = scenario("Test random endpoint")
                .exec(karateFeature("classpath:fr/spirylics/kouign/sugar.feature"));

        setUp(
                randomScenario.injectOpen(
                        constantUsersPerSec(Integer.parseInt(System.getProperty("kouign.usersPerSec", "1")))
                                .during(Duration.parse(System.getProperty("kouign.during", "PT1M"))))
        ).protocols(protocol);
    }
}
