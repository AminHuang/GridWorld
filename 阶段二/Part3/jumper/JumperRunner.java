/**
 * This class runs a world that contains jumper . <br />
 * This class is not tested on the AP CS A and AB exams.
 */
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;

import java.awt.Color;

public class JumperRunner
{
    public static void main(String args[])
    {
        ActorWorld world = new ActorWorld();
        Jumper fucker = new Jumper(Color.MAGENTA);
        world.add(fucker);
        world.add(new Jumper());
        world.add(new Rock());
        world.add(new Bug());
        world.add(new Flower());
        world.show();
    }
}