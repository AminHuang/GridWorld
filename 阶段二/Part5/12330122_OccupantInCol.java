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


/**
 * is an array list of linked lists
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class OccupantInCol {
    private Object occupant;
    private int col;
    
    /**
     * Constructor of class OccupantInCol
     */
    public OccupantInCol(Object e, int c) {
        occupant = e;
        col = c;
    }
    
    /**
     * get the value of col
     */
    public int getCol() {
        return col;
    }
    
    /**
     * get the value of occupant
     */
    public Object getOccupant() {
        return occupant;
    }
    
    /**
     * reset the value of col
     */
    public void setCol(int c) {
        col = c;
    }
    
    /**
     * reset the value of occupant
     */
    public void setObject(Object e) {
        occupant = e;
    }

}
