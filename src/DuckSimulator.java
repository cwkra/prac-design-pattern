import adapters.*;
import composite.Flock;
import composite.LeaderFlock;
import decorator.QuackCounter;
import decorator.QuackEcho;
import factories.AbstractDuckFactory;
import factories.CountingAndEchoDuckFactory;
import factories.CountingDuckFactory;
import factories.DuckFactory;

public class DuckSimulator {
    public static void main(String[] args) {
        DuckSimulator simulator = new DuckSimulator();
//        DuckFactory duckFactory = new DuckFactory();
        AbstractDuckFactory duckFactory = new CountingDuckFactory();
//        AbstractDuckFactory duckFactory = new CountingAndEchoDuckFactory();

        simulator.simulate(duckFactory);
    }

//    void simulate() {

//---------------------------------------
//        Adapter pattern
// ---------------------------------------

//        Quackable mallardDuck = new MallardDuck();
//        Quackable redheadDuck = new RedheadDuck();
//        Quackable duckCall = new DuckCall();
//        Quackable rubberDuck = new RubberDuck();
//        Quackable gooseDuck = new GooseAdapter(new Goose());
//        Quackable pigeonDuck = new PigeonAdapter(new Pigeon());
//        System.out.println("\nDuck Simulator: With Goose Adapter and Pigeon Adapter");

//---------------------------------------
//        Decorator pattern
//---------------------------------------

//    แบบที่ 1 พิมพ์ new QuackEcho(new QuackCounter(new RubberDuck())); จะนับจำนวนการ quack ได้ 5 ครั้ง
//    แบบที่ 2 พิมพ์ new QuackCounter(new QuackEcho(new RubberDuck())); จะนับจำนวนการ quack ได้ 4 ครั้ง แต่มีการพิมพ์ echo มาด้วย

//        Quackable mallardDuck = new QuackCounter(new MallardDuck());
//        Quackable redheadDuck = new QuackCounter(new RedheadDuck());
//        Quackable duckCall = new QuackCounter(new DuckCall());
//        Quackable rubberDuck = new QuackEcho(new QuackCounter(new RubberDuck()));
//        Quackable rubberDuck1 = new QuackCounter(new QuackEcho(new RubberDuck()));
//        System.out.println("\nDuck Simulator: With Decorator");

//        simulate(mallardDuck);
//        simulate(redheadDuck);
//        simulate(duckCall);
//        simulate(rubberDuck);
//        simulate(rubberDuck1);
//        simulate(gooseDuck);
//        simulate(pigeonDuck);
//        System.out.println("The ducks quacked "+QuackCounter.getQuacks()+" times.");
//    }

//    void simulate(Quackable duck) {
//        duck.quack();
//    }

//---------------------------------------
//    Abstract pattern
//---------------------------------------

//    แบบที่ 1 ไม่มี decorator ใดเลย: จะไม่มีการนับจำนวนการ quack
//    แบบที่ 2 มี counter decorator: จะมีการนับจำนวนการ quack (4 ครั้ง)
//    แบบที่ 3 มีทั้ง counter decorator และ echo decorator: จะมีการนับจำนวนการ quack และ มีการ echo (นับได้ 8 ครั้ง)

//    void simulate(AbstractDuckFactory duckFactory) {
//        Quackable mallardDuck = duckFactory.createMallardDuck();
//        Quackable redheadDuck = duckFactory.createRedheadDuck();
//        Quackable duckCall = duckFactory.createDuckCall();
//        Quackable rubberDuck = duckFactory.createRubberDuck();
//        Quackable gooseDuck = new GooseAdapter(new Goose());
//        System.out.println("\nDuck Simulator: With Abstract Factory");
//
//        simulate(mallardDuck);
//        simulate(redheadDuck);
//        simulate(duckCall);
//        simulate(rubberDuck);
//        simulate(gooseDuck);
//
//        System.out.println("The ducks quacked " + QuackCounter.getQuacks() +" times.");
//        }

//---------------------------------------
//    Composite pattern
//---------------------------------------
    void simulate(AbstractDuckFactory duckFactory) {
        Quackable redheadDuck = duckFactory.createRedheadDuck();
        Quackable duckCall = duckFactory.createDuckCall();
        Quackable rubberDuck = duckFactory.createRubberDuck();
        Quackable gooseDuck = new GooseAdapter(new Goose());
        System.out.println("\nDuck Simulator: With Composite - Flocks");

        Flock flockOfDucks = new Flock();

        flockOfDucks.add(redheadDuck);
        flockOfDucks.add(duckCall);
        flockOfDucks.add(rubberDuck);
        flockOfDucks.add(gooseDuck);

        Flock flockOfMallards = new Flock();
        Quackable mallardOne = duckFactory.createMallardDuck();
        Quackable mallardTwo = duckFactory.createMallardDuck();
        Quackable mallardThree = duckFactory.createMallardDuck();
        Quackable mallardFour = duckFactory.createMallardDuck();

        LeaderFlock leaderFlock = new LeaderFlock();
        leaderFlock.add(mallardOne);
        flockOfMallards.add(leaderFlock);
        flockOfMallards.add(mallardTwo);
        flockOfMallards.add(mallardThree);
        flockOfMallards.add(mallardFour);

        flockOfDucks.add(flockOfMallards);

        System.out.println("\nDuck Simulator: Whole Flock Simulator");
        simulate(flockOfDucks);

        System.out.println("\nDuck Simulator: Mallard Flock Simulator");
        simulate(flockOfMallards);

        System.out.println("The ducks quacked " + QuackCounter.getQuacks() +" times.");
    }

    void simulate(Quackable duck){
        duck.quack();
    }
}
