package Builder;

class IceCream {
    private final String type;
    private final String flavor;
    private final boolean hasToppings;

    public IceCream(String type, String flavor, boolean hasToppings) {
        this.type = type;
        this.flavor = flavor;
        this.hasToppings = hasToppings;
    }

    @Override
    public String toString() {
        return "IceCream{" +
                "type='" + type + '\'' +
                ", flavor='" + flavor + '\'' +
                ", hasToppings=" + hasToppings +
                '}';
    }
}

interface IceCreamBuilder {
    IceCreamBuilder chooseType(String type);
    IceCreamBuilder chooseFlavor(String flavor);
    IceCreamBuilder addToppings(boolean hasToppings);
    IceCream build();
}

class SimpleIceCreamBuilder implements IceCreamBuilder {
    private String type;
    private String flavor;
    private boolean hasToppings;

    @Override
    public IceCreamBuilder chooseType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public IceCreamBuilder chooseFlavor(String flavor) {
        this.flavor = flavor;
        return this;
    }

    @Override
    public IceCreamBuilder addToppings(boolean hasToppings) {
        this.hasToppings = hasToppings;
        return this;
    }

    @Override
    public IceCream build() {
        return new IceCream(type, flavor, hasToppings);
    }
}

class IceCreamVendor {
    public IceCream createIceCream(IceCreamBuilder builder) {
        return builder.build();
    }
}

public class IceCreamShop {
    public static void main(String[] args) {
        IceCreamBuilder builder = new SimpleIceCreamBuilder();
        IceCreamVendor vendor = new IceCreamVendor();

        IceCream vanillaIceCream = vendor.createIceCream(builder
                .chooseType("Cup")
                .chooseFlavor("Mint-Chocolate")
                .addToppings(true));

        IceCream chocolateIceCream = vendor.createIceCream(builder
                .chooseType("Cone")
                .chooseFlavor("Smurf/Watermelon")
                .addToppings(false));

        System.out.println(vanillaIceCream);
        System.out.println(chocolateIceCream);
    }
}
