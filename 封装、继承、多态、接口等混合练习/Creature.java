package com.company;

import java.util.ArrayList;

public abstract class   Creature implements concept, GodBehavior{
    private boolean soul = true;

    public abstract void die();
    public abstract ArrayList<Creature> newBirth();
    public abstract Creature birth();

    public final void setSoul(boolean soul) {
        this.soul = soul;
    }

}
