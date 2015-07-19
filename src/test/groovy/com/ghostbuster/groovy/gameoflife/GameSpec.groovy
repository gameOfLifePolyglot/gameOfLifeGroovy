package com.ghostbuster.groovy.gameoflife

import spock.lang.Specification

class GameSpec extends Specification {

    private Game game

    void setup() {
        game = new Game()
    }

    def "Should game not be empty after adding life"() {
        when:
        game.add(new Life(0, 0))

        then:
        game.isAlive(new Life(0, 0))
    }

    def "Should alone life die after one tick"() {
        given:
        game.add(new Life(0, 0))

        when:
        game.tick()

        then:
        !game.isAlive(new Life(0, 0))
    }

    def "Should life not die after one tick when has two neighbours"() {
        given:
        game.add(new Life(0, 0))
        addTwoFriends()

        when:
        game.tick()

        then:
        game.isAlive(new Life(0, 0))
    }

    def "Should life not die after one tick when has three neighbours"() {
        given:
        Life life = new Life(0, 0)
        game.add(life)
        addThreeFriends()

        when:
        game.tick()

        then:
        game.isAlive(life)
    }

    def "Should life die after one tick when has more then three neighbours"() {
        given:
        Life life = new Life(0, 0)
        game.add(life)
        addFourFriends()

        when:
        game.tick()

        then:
        !game.isAlive(life)
    }

    def "Should dead life emerge after one tick when has exactly three neighbours"() {
        given:
        Life zombie = new Life(0, 0)
        Life firstFriend = new Life(0, 1)
        Life secondFriend = new Life(1, 1)
        Life thirdFriend = new Life(1, 0)
        game.add( firstFriend, secondFriend, thirdFriend)

        when:
        game.tick()

        then:
        game.isAlive(zombie)
    }

    private addTwoFriends() {
        game.add(new Life(0, 1), new Life(1, 1))
    }

    private addThreeFriends() {
        addTwoFriends()
        game.add(new Life(1, 0))
    }

    private addFourFriends() {
        addThreeFriends()
        game.add( new Life(-1, 0))
    }
}
