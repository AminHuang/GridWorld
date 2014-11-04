import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Bug;

import java.awt.Color;

/**
    * A <code>Jumper</code> is an actor that will jump over Rocks and Flowers and turn.
    * I just regard it as a Frog.
    */
    public class Jumper extends Actor
    {
        /**
         *Constructor  for a green forg.
         */
        public Jumper()
        {
            setColor(Color.GREEN);
        }

        /**
         *Constructor for a frog whiose color is given.
         *@param JumperColor the color for this frog
         */
        public Jumper(Color JumperColor)
        {
            setColor(JumperColor);
        }

        public void act()
        {
            if (canJump())
                    jump();
            else
                    turn();
        }

        /**
         * Turns the frog 45 degrees to the right without changing its location.
         *Some guy advised the frog should be turned by 90 degrees, but I cannot image a boxFrog.
         */
         public void turn()
         {
            setDirection(getDirection() + Location.HALF_RIGHT);
         } 

         /**
          *Moves the frog forward two locations each time.
          *The location two locations in front of the frog should be vaild. otherwise it will be dead (removed).
          */
         public void jump()
         {
            Grid<Actor> gr = getGrid();
            if(gr.equals(null))
                 return;
             Location loc = getLocation();
             Location next = loc.getAdjacentLocation(getDirection());
             Location nextnext = next.getAdjacentLocation(getDirection());
             if (gr.isValid(nextnext))
                moveTo(nextnext);
            else
                removeSelfFromGrid();            
         }

         /**
           *A flag to represent if the frog can jump.
           */
         public boolean canJump()
         {
            Grid<Actor> gr = getGrid();
            if(gr.equals(null))
                return false;
            Location loc = getLocation();
            Location next = loc.getAdjacentLocation(getDirection());
            if(!gr.isValid(next))
                return false;
            Location nextnext = next.getAdjacentLocation(getDirection());
            if(!gr.isValid(nextnext))
                return false;
            Actor obstacle = gr.get(nextnext);
            return (obstacle == null)||(obstacle instanceof Flower||(obstacle instanceof Bug)||(obstacle instanceof Jumper));
         }
    }