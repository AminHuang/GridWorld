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
package dancing;

import info.gridworld.actor.Bug;

/**
 * A CircleBug that "dances" by making different turns before each move
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class DancingBug extends Bug {
    private int steps;
    private int turnTimes[];
    
    /**
     * Constructs a dancing bug 
     * that "dances" by making different turns before each move
     * @param steps the current step
     * @param []times represent the time the bug turns before it moves
     */
    public DancingBug(int []times) {
        steps = 0;
        turnTimes = new int[times.length];
        System.arraycopy(times, 0, turnTimes, 0, times.length);
    }

    
    public void act() {
        if(steps == turnTimes.length) {
            steps = 0;
        }

        //find the current direction 
        for(int i = 0; i < turnTimes[steps]; i++){
            turn();
        }
        
        if(canMove()) {
            move();
            steps++;
        } else {
            turn();
        }
    }

}
