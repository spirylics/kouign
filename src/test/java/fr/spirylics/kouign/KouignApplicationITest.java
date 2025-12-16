/*
 * Copyright (C) Deveryware S.A.S. All Rights Reserved.
 */
package fr.spirylics.kouign;

import com.intuit.karate.junit5.Karate;

public class KouignApplicationITest {
    @Karate.Test
    Karate sugar() {
        return Karate.run("sugar").relativeTo(getClass());
    }
}
