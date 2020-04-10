package designpatterns.decorator;

public class ManDecoratorA extends Decorator {
    @Override
    public void eat() {
        super.eat();
        reEat();
        System.out.println("ManDecoratorA类");
    }

    public void reEat() {
        char a = '国';
        System.out.println("再吃一顿饭");
    }
}
