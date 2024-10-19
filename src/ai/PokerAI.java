
/*
 * +----------------------------------------------------------------------------------------------------+
 * |                                      PokerAI                                                      |
 * +----------------------------------------------------------------------------------------------------+
 * | - aggression: double                                                                              |
 * | - tightness: double                                                                               |
 * | - bluffFrequency: double                                                                          |
 * | - riskTolerance: double                                                                           |
 * +----------------------------------------------------------------------------------------------------+
 * | + PokerAI(aggression: double, tightness: double, bluffFrequency: double, riskTolerance: double)   |
 * | + adjustStrategy(wonLastHand: boolean): void                                                      |
 * | + makeDecision(handStrength: double, potSize: double, position: String): String                   |
 * | + getAggression(): double                                                                         |
 * | + setAggression(aggression: double): void                                                         |
 * | + getTightness(): double                                                                          |
 * | + setTightness(tightness: double): void                                                           |
 * | + getBluffFrequency(): double                                                                     |
 * | + setBluffFrequency(bluffFrequency: double): void                                                 |
 * | + getRiskTolerance(): double                                                                      |
 * | + setRiskTolerance(riskTolerance: double): void                                                   |
 * | + toString(): String                                                                              |
 * | + equals(obj: Object): boolean                                                                    |
 * +----------------------------------------------------------------------------------------------------+
 * */
public class PokerAI {

    private double aggression;      // raise/bet frequency
    private double tightness;       // control of the range of hands played
    private double bluffFrequency;  // how often AI bluffs
    private double riskTolerance;   // risk taking

    public PokerAI(double aggression, double tightness, double bluffFrequency, double riskTolerance) {
        this.aggression = aggression;
        this.tightness = tightness;
        this.bluffFrequency = bluffFrequency;
        this.riskTolerance = riskTolerance;
    }

    // Incremental tendecies adjustments
    public void adjustStrategy(boolean wonLastHand) {
        if (wonLastHand) {
            aggression = Math.min(1.0, aggression + 0.05);  // ++aggression after a win
            bluffFrequency = Math.min(1.0, bluffFrequency + 0.05);  // ++bluffs if bluffing worked
        } else {
            aggression = Math.max(0.1, aggression - 0.05);  // --aggression after a loss
            bluffFrequency = Math.max(0.1, bluffFrequency - 0.05);  // --bluffing if it fails
        }
    }

    // AI decision-making process based on hand strength and game situation
    public String makeDecision(double handStrength, double potSize, String position) {
        // logic
        if (handStrength > tightness) {
            if (aggression > 0.7) {
                return "raise";  // Aggressive play
            } else {
                return "call";   // Passive play
            }
        } else if (Math.random() < bluffFrequency) {
            return "bluff";      // AI attempts a bluff
        } else {
            if (riskTolerance > 0.7) {
                return "call";   // High-risk tolerance leads to calling
            } else {
                return "fold";   // Fold if the risk is too high
            }
        }
    }

    //Setters and getters
    public double getAggression() {
        return aggression;
    }

    public void setAggression(double aggression) {
        this.aggression = aggression;
    }

    public double getTightness() {
        return tightness;
    }

    public void setTightness(double tightness) {
        this.tightness = tightness;
    }

    public double getBluffFrequency() {
        return bluffFrequency;
    }

    public void setBluffFrequency(double bluffFrequency) {
        this.bluffFrequency = bluffFrequency;
    }

    public double getRiskTolerance() {
        return riskTolerance;
    }

    public void setRiskTolerance(double riskTolerance) {
        this.riskTolerance = riskTolerance;
    }

    @Override
    public String toString() {
        return "PokerAI {"
                + "aggression=" + aggression
                + ", tightness=" + tightness
                + ", bluffFrequency=" + bluffFrequency
                + ", riskTolerance=" + riskTolerance
                + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        PokerAI pokerAI = (PokerAI) obj;
        return Double.compare(pokerAI.aggression, aggression) == 0
                && Double.compare(pokerAI.tightness, tightness) == 0
                && Double.compare(pokerAI.bluffFrequency, bluffFrequency) == 0
                && Double.compare(pokerAI.riskTolerance, riskTolerance) == 0;
    }

}
