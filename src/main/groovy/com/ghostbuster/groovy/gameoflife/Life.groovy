package com.ghostbuster.groovy.gameoflife

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.Sortable
import groovy.transform.TupleConstructor

@TupleConstructor(includeFields = true)
@EqualsAndHashCode(includeFields = true)
@Sortable
@CompileStatic
class Life {

    private final int x
    private final int y

    Set<Life> getPossibleNeighbours() {
        return [(x - 1..x + 1), (y - 1..y + 1)]
                .combinations()
                .collect(this.&aLife)
                .findAll { it != this }
                .toSet()
    }

    private static Life aLife(List<Integer> coords) {
        return new Life(coords.get(0), coords.get(1))
    }
}
