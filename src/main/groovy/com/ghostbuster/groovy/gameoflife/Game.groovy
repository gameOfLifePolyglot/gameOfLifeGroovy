package com.ghostbuster.groovy.gameoflife

import groovy.transform.CompileStatic

@CompileStatic
final class Game {
    private Set<Life> lives = new HashSet<Life>()

    void add(Life... lives) {
        this.lives.addAll(lives)
    }

    void tick() {
        def survived = lives.findAll(this.&shouldSurvive)
        def possibleToEmerge = lives.collectMany { it.possibleNeighbours }.toSet()
        def emerged = possibleToEmerge.findAll(this.&shouldEmerge)

        lives = survived + emerged
    }

    boolean shouldSurvive(Life life) {
        return intersectLivesWithPossiblesOf(life).size() in [2, 3]
    }

    boolean shouldEmerge(Life life) {
        return intersectLivesWithPossiblesOf(life).size() == 3
    }

    private Set<Life> intersectLivesWithPossiblesOf(Life life) {
//        why this dont work!?
//        return life.possibleNeighbours.intersect(lives)
        return life.possibleNeighbours.findAll{ lives.contains(it) }.toSet()
    }

    boolean isAlive(Life life) {
        return lives.contains(life)
    }
}
