package fr.spirylics.kouign;

import com.intuit.karate.gatling.javaapi.KarateProtocolBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import static io.gatling.javaapi.core.CoreDsl.*;
import static com.intuit.karate.gatling.javaapi.KarateDsl.*;

public class SugarSimulation extends Simulation {

    public SugarSimulation() {
        KarateProtocolBuilder protocol = karateProtocol();

        ScenarioBuilder randomScenario = scenario("Test random endpoint")
            .exec(karateFeature("classpath:fr/spirylics/kouign/sugar.feature@name=random"));

        setUp(
            randomScenario.injectOpen(constantUsersPerSec(10).during(60)).protocols(protocol)
        );
    }
}
