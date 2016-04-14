package com.getbase.smokers;

import static com.getbase.smokers.Ingredient.*;

public class CigaretteSmokers {

    public static void main(String... args) {
        Agent agent = new Agent();
        new Thread(agent).start();

        MatchesPusher matchesPusher = new MatchesPusher(agent.getMatchesLock(), agent.getMatchesAvail());
        new Thread(matchesPusher).start();

        PaperPusher paperPusher = new PaperPusher(agent.getPaperLock(), agent.getPaperAvail());
        new Thread(paperPusher).start();

        TobaccoPusher tobaccoPusher = new TobaccoPusher(agent.getTobaccoLock(), agent.getTobaccoAvail());
        new Thread(tobaccoPusher).start();

        Smoker smokerWithPaper = new Smoker(PAPER, agent.getAgentLock(), agent.getAgentCond(), Pusher.paperSmokerLock, Pusher.paperSmokerAvail);
        new Thread(smokerWithPaper).start();

        Smoker smokerWithMatches = new Smoker(MATCHES, agent.getAgentLock(), agent.getAgentCond(), Pusher.matchesSmokerLock, Pusher.matchesSmokerAvail);
        new Thread(smokerWithMatches).start();

        Smoker smokerWithTobacco = new Smoker(TOBACCO, agent.getAgentLock(), agent.getAgentCond(), Pusher.tobaccoSmokerLock, Pusher.tobaccoSmokerAvail);
        new Thread(smokerWithTobacco).start();
    }
}
