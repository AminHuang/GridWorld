package kingCrab;
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
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author Cay Horstmann
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.Color;

import crabCritter.CrabCritter;

/**
 * This class runs a world that contains rock hound.
 * This class is not tested on the AP CS A and AB exams.
 */
final class KingCrabRunner {
	private KingCrabRunner() {}
	public static final int ZERO = 0;
	public static final int ONE = 1;
	public static final int TWO = 2;
	public static final int THREE = 3;
	public static final int FOUR = 4;
	public static final int FIVE = 5;
	public static final int SIX = 6;
	public static final int SEVEN = 7;
	public static final int EIGHT = 8;
	public static final int NIN = 9;
	
	/**
     * @param args
     */
    public static void main(String[] args) {
    	ActorWorld world = new ActorWorld();
    	world.add(new Location(SEVEN, FIVE), new Rock());
        world.add(new Location(FIVE, FOUR), new Rock());
        world.add(new Location(FIVE, SEVEN), new Rock());
        world.add(new Location(SEVEN, THREE), new Rock());
        world.add(new Location(SEVEN, EIGHT), new Flower());
        world.add(new Location(TWO, TWO), new Flower());
        world.add(new Location(THREE, FIVE), new Flower());
        world.add(new Location(THREE, EIGHT), new Flower());
        world.add(new Location(SIX, FIVE), new Bug());
        world.add(new Location(FIVE, THREE), new Bug());
        world.add(new Location(FOUR, FIVE), new KingCrab());
        world.add(new Location(SIX, ONE), new KingCrab());
        world.add(new Location(SEVEN, FOUR), new KingCrab());
        world.show();
    }
}