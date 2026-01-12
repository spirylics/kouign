package fr.spirylics.kouign.application;

import fr.spirylics.kouign.domain.sugar.in.SugarService;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/{version}/sugar")
@RequiredArgsConstructor
@Accessors(fluent = true, chain = true)
@Getter(AccessLevel.PRIVATE)
public class SugarController {
    private final SugarService sugarService;

    @GetMapping(path = "random", version = "1.0")
    public Collection<String> random(@RequestParam final int size)
    {
        return sugarService().randomStrings(size).toList();
    }

    @GetMapping(path = "randomWindow", version = "1.0")
    public Collection<List<String>> random(@RequestParam final int size, final int windowSize)
    {
        return sugarService().randomStrings(size, windowSize).toList();
    }

    @GetMapping(path = "template", version = "1.0")
    public String template(@RequestParam final String name)
    {
        return StringSubstitutor.replace("Hello ${name}", Map.of("name", name));
    }
}
