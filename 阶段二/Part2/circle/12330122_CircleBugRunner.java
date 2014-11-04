/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Amin
 */
package circle;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains circle bugs.
 * This class is not tested on the AP CS A and AB exams.
 */
final class CircleBugRunner {
	private CircleBugRunner() {}
	private final static int SIX = 6;

    /**
     * @param args
     */
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        CircleBug first = new CircleBug(2);
        first.setColor(Color.ORANGE);
        world.add(new Location(SIX, 2), first);
        world.show();

    }

}
