package com.ghostbuster.groovy.gameoflife

import spock.lang.Specification

final class LifeSpec extends Specification {

    def "Should life return it possible neighbours"() {
        given:
        def p = new Life(0, 0).possibleNeighbours

        expect:
        p == [new Life(-1, 1), new Life(0, 1), new Life(1, 1),
              new Life(-1, 0), new Life(1, 0),
              new Life(-1, -1), new Life(0, -1), new Life(1, -1)].toSet()
    }

    def "Should life be recognizable"() {
        given:
        Life first = new Life(0, 0)
        Life second = new Life(1, 1)

        expect:
        first != second

    }

}
