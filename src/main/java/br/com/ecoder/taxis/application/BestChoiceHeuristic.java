package br.com.ecoder.taxis.application;

import br.com.ecoder.taxis.model.Driver;

class BestChoiceHeuristic implements Comparable<BestChoiceHeuristic> {

    Driver driver;
    Long heuristic;

    BestChoiceHeuristic(Driver driver, Long heuristic) {
        this.driver = driver;
        this.heuristic = heuristic;
    }

    @Override
    public int compareTo(BestChoiceHeuristic other) {
        // ordem decrescente
        return this.heuristic.compareTo(other.heuristic);
    }
}
