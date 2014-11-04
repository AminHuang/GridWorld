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
import java.util.LinkedList;
import java.util.List;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

 /**
  * extends AbstractGrid<E>
  * LinkedList<OccupantInCol> version
  * Each linked list entry holds both a grid occupant and a column index 
  * Each entry in the array list is a linked list or is null if that row is empty
  * The implementation of this class is testable on the AP CS A and AB exams.
  */
public class SparseBoundedGrid<E> extends AbstractGrid<E> {
    private List<LinkedList<OccupantInCol>> occupantArray;
    private int numRows;
    private int numCols;
    
    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: rows > 0 and cols > 0.)
     * @param rows number of rows in SparseBoundedGrid
     * @param cols number of columns in SparseBoundedGrid
     */
    public SparseBoundedGrid(int rows, int cols) {
        if (rows <= 0) {
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0) {
            throw new IllegalArgumentException("cols <= 0");
        }
        
        numRows = rows;
        numCols = cols;
        
        occupantArray = new ArrayList<LinkedList<OccupantInCol>> ();
        for (int i = 0; i < rows; i++) {
            occupantArray.add(new LinkedList<OccupantInCol> ());
        }
    }
    
    /**
     * get the number of rows in this SparseBoundedGrid
     * @return the value of numRows 
     */
    @Override
    public int getNumRows() {
        return numRows;
    }
    
    /**
     * get the number of columns in this SparseBoundedGrid
     * @return the value of numCols 
     */
    @Override
    public int getNumCols() {
        return numCols;
    }
    
    /**
     * Checks whether a location is valid in this SparseBoundedGrid.
     */
    @Override
    public boolean isValid(Location loc) {
        int row = loc.getRow();
        int col = loc.getCol();
        return 0 <= row && row < numRows
                && 0 <= col && col < numCols;
    }
    
    /**
     * Gets the locations in this grid that contain objects.
     * @return an array list of all occupied locations in this SparseBoundedGrid
     */
    @Override
    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> theLocations = new ArrayList<Location>();
        
        int i = 0;
        for (LinkedList<OccupantInCol> node : occupantArray) {
            for(int j = 0; j < node.size(); j++) {
                theLocations.add(new Location(i,node.get(j).getCol()));
            }
            i++;
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
    @SuppressWarnings("unchecked")
    public E get(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        
        int row = loc.getRow();
        int col = loc.getCol();
        LinkedList<OccupantInCol> node = occupantArray.get(row);
        
        if (node.isEmpty()) {
            return null;
        } else {
            for (OccupantInCol c : node) {
                if (c.getCol() == col) {
                    return  (E) c.getOccupant();
                }
            }
        }
        return null;
        
    }

    /**
     * Puts an object at a given location in this SparseBoundedGrid
     * Precondition: (1) loc< is valid in this SparseBoundedGrid 
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
        
        int row = loc.getRow();
        int col = loc.getCol();
        LinkedList<OccupantInCol> node = occupantArray.get(row);
        E oldOccupant = null;
        
        node.add(new OccupantInCol(obj,col));
        
        return oldOccupant;
    }

    /**
     * Removes the object at a given location from this SparseBoundedGrid
     * Precondition: loc is valid in this SparseBoundedGrid
     * @param loc the location of the object that is to be removed
     * @return the object that was removed (or <null if the location is unoccupied)
     */
    @Override
    public E remove(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        
        E r = get(loc);
        int row = loc.getRow();
        int col = loc.getCol();
        LinkedList<OccupantInCol> node = occupantArray.get(row);
        
        for (OccupantInCol c : node) {
            if (c.getCol() == col) {
                node.remove(c);
                break;
            }
        }
        return r;
    }
}
