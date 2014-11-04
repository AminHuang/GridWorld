import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;

public class JumperTest extends TestCase
{
    public JumperTest()
    {
    }

    public static void main(String args[])
    {
        junit.textui.TestRunner.run(JumperTest.class);
    }

    /**
      * The destination is valid (null or Flower there) or not.
      */
    public void testAct1()
    {
        ActorWorld world = new ActorWorld();
        Jumper a = new Jumper(Color.CYAN);
        Jumper b = new Jumper();
        Jumper c = new Jumper(Color.GRAY);
        Rock r1 = new Rock();
        Rock r2 = new Rock();
        Flower f = new Flower();
        a.setDirection(Location.NORTHWEST);
        b.setDirection(Location.NORTH);
        c.setDirection(Location.NORTH);
        world.add(new Location(2,0), a);
        world.add(new Location(2,1), b);
        world.add(new Location(2,2), c);
        world.add(new Location(0,2), r1);
        world.add(new Location(1,3), r2);
        world.add(new Location(0,4), f);
        world.show();
        a.act();
        b.act();
        c.act();
        Grid gra = a.getGrid();
        Grid grb = b.getGrid();
        Grid grc = c.getGrid();
        assert (gra != null);
        assertEquals(2, a.getLocation().getRow());
        assertEquals(0, a.getLocation().getCol());
        assertEquals(Location.NORTH, a.getDirection());
        assert (grb != null);
        assertEquals(0, b.getLocation().getRow());
        assertEquals(1, b.getLocation().getCol());
        assertEquals(0, b.getDirection());
        assert (grc != null);
        assertEquals(2, c.getLocation().getRow());
        assertEquals(2, c.getLocation().getCol());
        assertEquals(45, c.getDirection());
        int i = 0;
        for(; i < 3; i++)
        {
            c.act();
        }
        Grid<Actor> gr = world.getGrid();
        Actor whatAFuck = gr.get(new Location(0,4));
        assertEquals(true, whatAFuck == null);
    }

    /**
      * When two Jumpers or a Jumper and a Bug seize  a position, whether they will eat each other.
      */
    public void testAct2()
    {
        ActorWorld world = new ActorWorld();
        Jumper a = new Jumper(Color.CYAN);
        Jumper b = new Jumper();
        Jumper c = new Jumper(Color.GRAY);
        Bug d = new Bug();
        Jumper e = new Jumper();
        Bug f = new Bug();
        a.setDirection(Location.EAST);
        b.setDirection(Location.WEST);
        c.setDirection(Location.EAST);
        d.setDirection(Location.WEST);
        e.setDirection(Location.EAST);
        f.setDirection(Location.WEST);
        world.add(new Location(0,0), a);
        world.add(new Location(0,4), b);
        world.add(new Location(1,0), c);
        world.add(new Location(1,3), d);
        world.add(new Location(2,0), e);
        world.add(new Location(2,3), f);
        world.show();
        a.jump();
        b.jump();
        c.jump();
        d.move();
        f.move();
        e.jump();
        Grid<Actor> gr = world.getGrid();
        Actor second = gr.get(new Location(1,2));
        Actor third = gr.get(new Location(2,2));
        assertEquals(270, b.getDirection());
        assertEquals(true, (second instanceof Bug));
        assertEquals(true, (third instanceof Jumper) );
    }
}