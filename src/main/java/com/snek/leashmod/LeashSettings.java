package me.snek.leashmod;

public interface LeashSettings {
    boolean isEnabled();
    double getDistanceMin();
    double getDistanceMax();
    boolean allowLeashedRemoveFenceKnot();
}
