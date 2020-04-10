package designpatterns.decorator;

import org.junit.jupiter.api.Test;


class ManTest {

    @Test
    void eat() {
        Decorator decorator = new Decorator();
        Man man = new Man();
        decorator.setPerson(man);
        decorator.eat();
    }
}