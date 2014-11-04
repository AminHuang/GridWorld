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

package kingCrab;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.List;

import crabCritter.CrabCritter;

/**
 * extends CrabCritter
 * gets the actors to be processed in the same way a CrabCritter does
 * A KingCrab causes each actor processed to move one location further 
 * If the actor cannot move away, the KingCrab removes it from the grid
 * When the KingCrab has completed processing the actors, 
 * it moves like a CrabCritter.
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class KingCrab extends CrabCritter {
	
	/**
     * Processes the elements of actors. 
     * A KingCrab causes each actor processed to move one location further 
     * If the actor cannot move away, the KingCrab removes it from the grid
     * Postcondition: (1) The state of all actors in the grid other than this
     * critter and the elements of <code>actors</code> is unchanged. (2) The
     * location of this critter is unchanged.
     * @param actors the actors to be processed
     */
    public void processActors(ArrayList<Actor> actors) {
    	Grid gr = getGrid();
        Location loc = getLocation();
        
        for (Actor a : actors) {
        	int toward = loc.getDirectionToward(a.getLocation());
        	Location neighborLoc = a.getLocation().getAdjacentLocation(toward);
        	if(gr.isValid(neighborLoc)) {
        		a.moveTo(neighborLoc);
        	} else {
                a.removeSelfFromGrid();
        	}
        }
    }

}
