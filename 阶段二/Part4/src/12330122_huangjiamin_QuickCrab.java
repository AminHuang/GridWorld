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
package quickCrab;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

import crabCritter.CrabCritter;


/**
 * extends CrabCritter
 * moves to one of the two locations, 
 * randomly selected, 
 * that are two spaces to its right or left, 
 * if that location and the intervening location are both empty. 
 * Otherwise, a QuickCrab moves like a CrabCritter.
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class QuickCrab extends CrabCritter {
	/**
     * @return list of empty locations immediately 
     * that location and the intervening location are both empty
     */
	public ArrayList<Location> getQuickMoveLocations() {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        Location loc = getLocation();
        Location left = loc.getAdjacentLocation((getDirection() + Location.LEFT));
        Location right = loc.getAdjacentLocation((getDirection() + Location.RIGHT));
        
        if (gr.isValid(left)) {
        	if (gr.get(left) == null) {
        		Location neighborLoc = left.getAdjacentLocation(getDirection() + Location.LEFT);
        		if (gr.isValid(neighborLoc) && gr.get(neighborLoc) == null) {
        				locs.add(neighborLoc);
        		}
        	}
        }
        
        if (gr.isValid(right)) {
        	if (gr.get(right) == null) {
        		Location neighborLoc = right.getAdjacentLocation(getDirection() + Location.RIGHT);
        		if (gr.isValid(neighborLoc) && gr.get(neighborLoc) == null) {
        				locs.add(neighborLoc);        			
        		}
        	}
        }

        return locs;
    }
    
	
	/**
     * @return list of empty locations immediately to the right and to the left
     */
    public ArrayList<Location> getMoveLocations() {
    	ArrayList<Location> quick = getQuickMoveLocations();
    	if (quick.size() != 0) {
    		return quick;
    	}
        ArrayList<Location> locs = new ArrayList<Location>();
        int[] dirs =
            { Location.LEFT, Location.RIGHT };
        for (Location loc : getLocationsInDirections(dirs)) {
            if (getGrid().get(loc) == null) {
                locs.add(loc);
            }
        }

        return locs;
    }


    

}
