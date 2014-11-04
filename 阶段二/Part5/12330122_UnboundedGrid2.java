import java.util.ArrayList;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

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
  * extends AbstractGrid<E>
  * array version
  * When a call is made to the put method with a row or column index 
  * that is outside the current array bounds, 
  * double both array bounds until they are large enough, 
  * construct a new square array with those bounds, 
  * and place the existing occupants into the new array.
  * all valid locations have non-negative row and column values
  * The implementation of this class is testable on the AP CS A and AB exams.
 * @param <E>
  */
public class UnboundedGrid2<E> extends AbstractGrid<E> {
    public static final int INIT = 16;
    private Object[][] occupantArray;
    private int dim;
    
    /**
     * Constructs an empty unbounded grid.
     */
    public UnboundedGrid2() {
        occupantArray = new Object[INIT][INIT];
        dim = INIT;
        
    }

    /**
     * the number of rows in this UnboundedGrid can be calculate
     * @return -1 
     */
    @Override
    public int getNumCols() {
        return -1;
    }

    /**
     * the number of columns in this UnboundedGrid can be calculate
     * @return -1 
     */
    @Override
    public int getNumRows() {
        return -1;
    }

    /**
     * Gets the locations in this grid that contain objects.
     * @return an array list of all occupied locations in this UnboundedGrid
     */
    @Override
    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> a = new ArrayList<Location>();
        
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                Location loc = new Location(i, j);
                if (get(loc) != null) {
                    a.add(loc);
                }
            }
        }
        
        return a;
    }

    /**
     * Checks whether a location is valid in this UnboundedGrid.
     */
    @Override
    public boolean isValid(Location loc) {
        return loc.getRow() >= 0 && loc.getCol() >= 0;
    }
    
    /**
     * Returns the object at a given location in this UnboundedGrid
     * Precondition: loc is valid in this UnboundedGrid
     * @param loc a location in this UnboundedGrid
     * @return the object at location loc (or null if the location is unoccupied)
     */
    @Override
    @SuppressWarnings({ "unchecked"})
    public E get(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        return (E) occupantArray[loc.getRow()][loc.getCol()]; 
    }

    /**
     * Puts an object at a given location in this UnboundedGrid
     * Precondition: (1) loc< is valid in this UnboundedGrid 
     * (2) obj is not null
     * @param loc the location at which to put the object
     * @param obj the new object to be added
     * @return the object at loc
     */
    @Override
    public E put(Location loc, E obj) {
        if (!isValid(loc)) {
            throw new NullPointerException("loc == null");
        }
        if (obj == null) {
            throw new NullPointerException("obj == null");
        }
        
        int row = loc.getRow();
        int col = loc.getCol();
        int newDim = dim;
        
        while (row > dim || col > dim){
            newDim *= 2;
        }

        if (newDim != dim) {
            resize(newDim);
        }
        
        // Add the object to the grid.
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    /**
     * Removes the object at a given location from this UnboundedGrid
     * Precondition: loc is valid in this UnboundedGrid
     * @param loc the location of the object that is to be removed
     * @return the object that was removed
     */
    @Override
    public E remove(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }
    
    /**
     * double both array bounds until they are large enough
     * @param newDim the new dimension of the UnboundedGrid
     */
    public void resize(int newDim) {
        Object[][] temp = new Object[newDim][newDim];
        
        for (int i = 0; i < dim; i++) {
            System.arraycopy(occupantArray[i], 0, temp[i], 0, dim);
        }
        
        dim = newDim;
    }

}
