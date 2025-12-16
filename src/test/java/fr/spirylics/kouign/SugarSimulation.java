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
            .exec(karateFeature("classpath:fr/spirylics/kouign/performance/sugar.feature@name=Test random endpoint"));

        ScenarioBuilder randomWindowScenario = scenario("Test randomWindow endpoint")
            .exec(karateFeature("classpath:fr/spirylics/kouign/performance/sugar.feature@name=Test randomWindow endpoint"));

        ScenarioBuilder templateScenario = scenario("Test template endpoint")
            .exec(karateFeature("classpath:fr/spirylics/kouign/performance/sugar.feature@name=Test template endpoint"));

        setUp(
            randomScenario.injectOpen(constantUsersPerSec(10).during(60)).protocols(protocol),
            randomWindowScenario.injectOpen(constantUsersPerSec(10).during(60)).protocols(protocol),
            templateScenario.injectOpen(constantUsersPerSec(10).during(60)).protocols(protocol)
        );
    }
}
