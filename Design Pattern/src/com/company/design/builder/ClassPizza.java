package com.company.design.builder;


public class ClassPizza extends Pizza {
    private final boolean sauceInside;

    public static class Builder extends Pizza.Builder {

        private boolean sauceInside=false;

        @Override
        public Pizza.Builder sauceInside() {
            sauceInside = true;
            return this;
        }

        @Override
        Pizza build() {
            return new ClassPizza(this);
        }

        @Override
        protected Pizza.Builder self() {
            return this;
        }
    }

    ClassPizza(Builder builder) {
        super(builder);
        sauceInside = builder.sauceInside;
    }

    @Override
    public String toString() {
        return "ClassPizza :" +
                "topping= " + toppings +
                ", sauceInside= " + sauceInside ;
    }
}
