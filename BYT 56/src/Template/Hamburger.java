package Template;

abstract class HamburgerTemplate {
    abstract void prepareBun();
    abstract void cookPatty();
    abstract void addIngredients();
    void finish() {
        System.out.println("Finished");
    }
    final void makeHamburger() {
        prepareBun();
        cookPatty();
        addIngredients();
        finish();
    }
}

class VeggieBurger extends HamburgerTemplate {
    @Override
    void prepareBun() {
        System.out.println("Preparing bun");
    }
    @Override
    void cookPatty() {
        System.out.println("Cooking veggie patty");
    }
    @Override
    void addIngredients() {
        System.out.println("Adding lettuce, tomato, onions");
    }
}

class Cheeseburger extends HamburgerTemplate {
    @Override
    void prepareBun() {
        System.out.println("Preparing meat bun");
    }
    @Override
    void cookPatty() {
        System.out.println("Cooking meat patty");
    }
    @Override
    void addIngredients() {
        System.out.println("Adding lettuce, tomato, onions, and a LOT of cheese");
    }
}

public class Hamburger {
    public static void main(String[] args) {
        HamburgerTemplate veggieBurger = new VeggieBurger();
        veggieBurger.makeHamburger();
        System.out.println();
        HamburgerTemplate cheeseburger = new Cheeseburger();
        cheeseburger.makeHamburger();
    }
}
