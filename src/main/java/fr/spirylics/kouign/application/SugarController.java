/*
 * Copyright (C) Deveryware S.A.S. All Rights Reserved.
 */
package fr.spirylics.kouign.application;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Gatherers;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/{version}/sugar")
public class SugarController {
    @GetMapping(path = "fold", version = "1.0")
    public Map<String, ?> fold(@RequestParam final Collection<Integer> numbers)
    {
        final var fold = numbers.stream().gather(Gatherers.fold(() -> 0, Integer::sum));
        return Map.of("list", fold.toList());
    }

    @GetMapping(path = "template", version = "1.0")
    public String template(@RequestParam final String name)
    {
        return StringSubstitutor.replace("Hello ${name}", Map.of("name", name));
    }
}
