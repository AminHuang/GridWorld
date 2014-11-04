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
package spiral;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;

import java.awt.Color;

/**
 * This class runs a world that contains spiral bugs.
 * This class is not tested on the AP CS A and AB exams.
 */
final class SpiralBugRunner {
    private SpiralBugRunner() {}
    private final static int FIVE = 5;
    private final static int THREE = 3;

    /**
     * change the world to an UnboundedGrid 
     * to see the spiral pattern more clearly
     * @param args
     */
    public static void main(String[] args) {
        UnboundedGrid<Actor> grid = new UnboundedGrid<Actor> ();
        ActorWorld world = new ActorWorld(grid);
        SpiralBug first = new SpiralBug(THREE);
        first.setColor(Color.ORANGE);
        world.add(new Location(FIVE, FIVE), first);
        world.show();

    }

}
