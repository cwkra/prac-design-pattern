package composite;

import adapters.Quackable;

import java.util.ArrayList;
import java.util.Iterator;

public class Flock implements Quackable {
    ArrayList<Quackable> quackers = new ArrayList<>();

    public void add(Quackable quacker){
        quackers.add(quacker);
    }
    @Override
    public void quack() {
        for (Quackable quacker : quackers) {
            quacker.quack();
        }
    }
}
