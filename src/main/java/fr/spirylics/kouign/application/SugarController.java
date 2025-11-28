/*
 * Copyright (C) Deveryware S.A.S. All Rights Reserved.
 */
package fr.spirylics.kouign.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/{version}/sugar")
public class SugarController {
    @GetMapping(path = "gatherer", version = "1.0")
    public String gatherer() {
        return "gatherer";
    }
}
