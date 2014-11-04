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
package dancing;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;


import java.awt.Color;

/**
 * This class runs a world that contains dancing bugs.
 * This class is not tested on the AP CS A and AB exams.
 */
final class DancingBugRunner {
	private DancingBugRunner() {}

    /**
     * @param args
     */
    public static void main(String[] args) {
        int times[] = {2,0,0,3,0,0,2,1,0,1,2,0,1,0,2};
        ActorWorld world = new ActorWorld();
        DancingBug first = new DancingBug(times);
        first.setColor(Color.ORANGE);
        world.add(new Location(0, 0), first);
        world.show();

    }

}
