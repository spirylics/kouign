/*
 * Copyright (C) Deveryware S.A.S. All Rights Reserved.
 */
package fr.spirylics.kouign.domain.sugar.in;

import java.util.List;
import java.util.stream.Stream;

public interface SugarService {
    Stream<String> randomStrings(int size);

    Stream<List<String>> randomStrings(int size, int windowSize);
}
