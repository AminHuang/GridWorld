import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
    private static final int NINTY = 90;
    private static final int CIRCLE = 360;
    private static final int FOUR = 4;
    
    private Location next;
    private Location last;
    private boolean isEnd = false;
    //private Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
    private Stack<Location> crossLocation = new Stack<Location>();
    private Integer stepCount = 0;
    //final message has been shown
    private boolean hasShown = false;
    // stright is 0, left is 1, right is 3
    private int dir[] = new int[FOUR];
    private int curdir;

    /**
     * Constructs a box bug that traces a square of a given side length
     * 
     * @param length
     *            the side length
     */
    public MazeBug() {
        setColor(Color.GREEN);
        last = new Location(0, 0);
        // the list is null
        //ArrayList<Location> valid = new ArrayList<Location>();
        //valid.add(getLocation());
        crossLocation.push(getLocation());
        for(int i = 0; i < FOUR; i++) {
            dir[i] = 1;
        }
        dir[2] = 0;
        curdir = 0;
        
    }

    /**
     * Moves to the next location of the square.
     */
    public void act() {
        
        boolean willMove = canMove();
        if (isEnd == true && hasShown == false) {
        //to show step count when reach the goal        

                String msg = stepCount.toString() + " steps";
                JOptionPane.showMessageDialog(null, msg);
                hasShown = true;
            
        } else if (willMove) {
            // if can move put the current location to the crossLocation
            //ArrayList<Location> temp = new ArrayList<Location> ();
            //temp.add(getLocation());
            crossLocation.push(getLocation());
            
            last = getLocation();
            
            if (getValid(getLocation()).size() > 1) {
                // 两个位置的相对方向 
                int d = getLocation().getDirectionToward(next);
                // 前进方向
                int c = this.getDirection();
                // 实际转向角度
                int angle = c - d;
                angle = angle >= 0 ? angle : angle + CIRCLE;
            
                int toward = angle / NINTY;
                dir[toward]++;
                curdir = toward;
                
            }
            
            move();
            //increase step count when move 
            stepCount++;
            
            
            
        } else {
            next = last;
            
            if (getValid(getLocation()).size() > 1) {
                dir[curdir]--;
            }
            
            move();
            stepCount++;
            crossLocation.pop();
            //last = crossLocation.peek().get(0);
            last = crossLocation.peek();
            
        }
    }

    /**
     * Find all positions that can be move to.
     * 
     * @param loc
     *            the location to detect.
     * @return List of positions.
     */
    public ArrayList<Location> getValid(Location loc) {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return null;
        }
        ArrayList<Location> valid = new ArrayList<Location>();
        
        for (int i = 0; i < FOUR; i++) {
            Location nloc = loc.getAdjacentLocation(getDirection() + i * Location.RIGHT);
            if (gr.isValid(nloc)) {
                Actor a = gr.get(nloc);
                if (a instanceof Rock && a.getColor().getRed() == Color.RED.getRed()) {
                    isEnd = true;
                    valid.add(nloc);
                    break;
                } else if (a == null){
                    valid.add(nloc);
                }
            }
        }
        
        
        return valid;
    }

    /**
     * Tests whether this bug can move forward into a location that is empty or
     * contains a flower.
     * 
     * @return true if this bug can move.
     */
    public boolean canMove() {
        ArrayList<Location> nextArray = getValid(getLocation());
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return false;
        } else if (nextArray.size() == 0) {
            return false;
        } else {
            int i = 0;
            int max = 0;
            for (Location loc : nextArray) {
                Actor a = gr.get(loc);
                
                if (a instanceof Rock) {
                    isEnd = true;
                    next = getLocation();
                    return true;
                }
                
                int d = getLocation().getDirectionToward(loc);
                int c = this.getDirection();
                int angle = c - d;
                angle = angle < 0 ? angle + CIRCLE : angle;
                
                int toward = angle / NINTY;
                
                if (dir[toward] > dir[max]) {
                    max = i;
                }
                
                i++;
            
            }
            /*
            if (max < 2) {
                Random random = new Random();
            max = random.nextInt(nextArray.size());
            }
            */
            
            
            next = nextArray.get(max);
            return true;
        }
    }
    /**
     * Moves the bug forward, putting a flower into the location it previously
     * occupied.
     */
    public void move() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        Location loc = getLocation();
        if (gr.isValid(next)) {
            setDirection(getLocation().getDirectionToward(next));
            last = getLocation();
            moveTo(next);
        } else {
            removeSelfFromGrid();
        }
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
    }
    
    
}
