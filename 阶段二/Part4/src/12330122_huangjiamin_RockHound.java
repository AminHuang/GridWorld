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
package rockHound;

import java.util.ArrayList;
import java.util.List;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;

/**
 * extends Critter
 * gets the actors to be processed in the same way as a Critter. 
 * It removes any rocks in that list from the grid
 * A RockHound moves like a Critter
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class RockHound extends Critter{
	/**
     * Processes the elements of actors. New actors may be added
     * to empty locations. Implemented to "eat" (i.e. remove) 
     * selected actors that are rocks.
     * Postcondition: (1) The state of all actors in the grid other than this
     * critter and the elements of actors is unchanged. 
     * (2) The location of this critter is unchanged.
     * @param actors the actors to be processed
     */
    public void processActors(ArrayList<Actor> actors) {
        for (Actor a : actors) {
            if (a instanceof Rock) {
                a.removeSelfFromGrid();
            }
        }
    }

}
