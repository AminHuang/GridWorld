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
package blusterCritter;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

/**
 * extends Critter
 * A BlusterCritter looks at all of the neighbors within two steps of its current location. 
 * If there are fewer than c critters,
 * the BlusterCritter's color gets brighter (color values increase).
 * If there are c or more critters
 * the BlusterCritter's color darkens (color values decrease)
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class BlusterCritter extends Critter{
	private static final double DARKENING_FACTOR = 0.05;
	private int courage;
	
	/**
     * Constructs a BlusterCritter of a given value.
     * @param  indicates the courage of the critter
     */
    public BlusterCritter(int c) {
    	super();
    	courage = c;
    }
	
	/**
	 * Darken BlusterCritter's color by DARKENING_FACTOR
	 */
	public void darken() {
		Color c = getColor();
        int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
        red = red > 0 ? red : 0;
        int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
        green = green > 0 ? green : 0;
        int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
        blue = blue > 0 ? blue : 0;
        setColor(new Color(red, green, blue));
	}
	
	/**
	 * brighter BlusterCritter's color by DARKENING_FACTOR
	 */
	public void brighter() {
		Color c = getColor();
        int red = (int) (c.getRed() * (1 + DARKENING_FACTOR));
        red = red > 255 ? 255 : red; 
        red = red > 0 ? red : 10;
        int green = (int) (c.getGreen() * (1 + DARKENING_FACTOR));
        green = green > 255 ? 255 : green;
        green = green > 0 ? green : 10;
        int blue = (int) (c.getBlue() * (1 + DARKENING_FACTOR));
        blue = blue > 255 ? 255 : blue;
        blue = blue > 0 ? blue : 10;
        setColor(new Color(red, green, blue));
        return;
	}
	
	/**
     * looks at all of the neighbors within two steps of its current location
     * @return a list of actors occupying these locations
     */
    public ArrayList<Actor> getActors() {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        Grid<Actor> gr = getGrid();
        Location loc = getLocation();
        int leftRow, leftCol, rightRow, rightCol;
        
        leftRow = loc.getRow() - 2;
        leftCol = loc.getCol() - 2;
        rightRow = loc.getRow() + 2;
        rightCol = loc.getCol() + 2;
        
        if(leftRow < 0) {
        	leftRow = 0;
        }
        if(leftCol < 0) {
        	leftCol = 0;
        }
        if(rightRow >= gr.getNumRows()) {
        	rightRow = gr.getNumRows() - 1;
        }
        if(rightCol >= gr.getNumCols()){
        	rightCol = gr.getNumCols() - 1;
        }
        
        for(int i = leftRow; i <= rightRow; i++) {
        	for(int j = leftCol; j < rightCol; j++) {
        		Location l = new Location(i,j);
        		Actor a = gr.get(l);
        		if(a != null) {
        			actors.add(a);
        		}
        	}
        }
        
        return actors;
    }
    
    /**
     * Processes the elements of <code>actors</code>.
     * The BlusterCritter's color get darken or brighter.
     * Postcondition: (1) The state of all actors in the grid other than this
     * critter and the elements of <code>actors</code> is unchanged. (2) The
     * location of this critter is unchanged.
     * @param actors the actors to be processed
     */
    
    public void processActors(ArrayList<Actor> actors) {
    	int count = 0;
    	for(Actor a: actors) {
    		if (a  instanceof Critter) {
    			count++;
    		}
    	}
        if (count >= courage) {
        	darken();
        } else {
        	brighter();
        }
    }


}
