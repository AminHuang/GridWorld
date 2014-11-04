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
 * @version 1.0
 */
package z;

import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

/**
 * A CircleBug that move in a "Z" pattern
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ZBug extends Bug {
    /**
     * @param steps the number of steps in the current side
     * @param sideLength the maximum size of each segment
     * @param seg the segment of the Z the ZBug is on
     */
    private int steps;
    private int sideLength;
    private int seg;
    
    /**
     * Constructs a circle bug that traces a circle square of a given side length
     * @param length the side length
     */
    public ZBug(int length) {
        steps = 0;
        sideLength = length;
        seg = 0;
        turn();
        turn();
    }
    
    /**
     * the actually act
     */
    public void act() {
        if (seg < 3) {
            if(canMove() && steps < sideLength) {
                move();
                steps++;
            } else {
                if(seg == 0){
                    setDirection(Location.SOUTHWEST);
                }
                else if (seg == 1) {
                    setDirection(Location.EAST);
                }
                steps = 0;
                seg++;
                
            }
        }
        
    }

}
