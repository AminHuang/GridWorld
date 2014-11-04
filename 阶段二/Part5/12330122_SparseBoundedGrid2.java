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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

 /**
  * extends AbstractGrid<E>
  * HashMap version
  * Each linked list entry holds both a grid occupant and a column index 
  * Each entry in the array list is a linked list or is null if that row is empty
  * The implementation of this class is testable on the AP CS A and AB exams.
  */
public class SparseBoundedGrid2<E> extends AbstractGrid<E> {
    private Map<Location, E> occupantMap;
    private int rowsNum;
    private int colsNum;
    
    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: rows > 0 and cols > 0.)
     * @param rows number of rows in SparseBoundedGrid
     * @param cols number of columns in SparseBoundedGrid
     */
    public SparseBoundedGrid2(int rows, int cols) {
        if (rows <= 0) {
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0) {
            throw new IllegalArgumentException("cols <= 0");
        }
        
        rowsNum = rows;
        colsNum = cols;
        
        occupantMap = new HashMap<Location, E>();
    }
    
    /**
     * get the number of rows in this SparseBoundedGrid
     * @return the value of numRows 
     */
    @Override
    public int getNumRows() {
        return rowsNum;
    }
    
    /**
     * get the number of columns in this SparseBoundedGrid
     * @return the value of numCols 
     */
    @Override
    public int getNumCols() {
        return colsNum;
    }
    
    /**
     * Checks whether a location is valid in this SparseBoundedGrid.
     */
    @Override
    public boolean isValid(Location loc) {
        int row = loc.getRow();
        int col = loc.getCol();
        return 0 <= row && row < rowsNum
                && 0 <= col && col < colsNum;
    }
    
    /**
     * Gets the locations in this grid that contain objects.
     * @return an array list of all occupied locations in this SparseBoundedGrid
     */
    @Override
    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> theLocations = new ArrayList<Location>();
        
        for (Location loc : occupantMap.keySet()) {
            theLocations.add(loc);
        }
        
        return theLocations;
    }
    
    /**
     * Returns the object at a given location in this SparseBoundedGrid
     * Precondition: loc is valid in this SparseBoundedGrid
     * @param loc a location in this SparseBoundedGrid
     * @return the object at location loc (or null if the location is unoccupied)
     */
    @Override
    public E get(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }       
        
        return occupantMap.get(loc);
        
    }

    /**
     * Puts an object at a given location in this SparseBoundedGrid
     * Precondition: (1) loc is valid in this SparseBoundedGrid 
     * (2) obj is not null
     * @param loc the location at which to put the object
     * @param obj the new object to be added
     * @return the object at loc(or null if the location was unoccupied)
     */
    @Override
    public E put(Location loc, E obj) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        if (obj == null) {
            throw new NullPointerException("obj == null");
        }
        
        return occupantMap.put(loc, obj);
    }

    /**
     * Removes the object at a given location from this SparseBoundedGrid
     * Precondition: loc is valid in this SparseBoundedGrid
     * @param loc the location of the object that is to be removed
     * @return the object that was removed (or null if the location is unoccupied)
     */
    @Override
    public E remove(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        
        return occupantMap.remove(loc);
    }
}
