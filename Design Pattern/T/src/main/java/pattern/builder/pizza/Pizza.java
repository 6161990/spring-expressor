package pattern.builder.pizza;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public abstract class Pizza {

    public enum Topping { 햄, 머시룸, 양파, 초리조, 바질}
    final Set<Topping> toppings;

    public abstract static class Builder { // 피자가 생성되지 않아도 Builder를 불러서 피자를 생성할 수 있게. 생성자를 통해 생성하지 않겠다!
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public Builder addTopping(Topping topping) {
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }

        public Builder sauceInside() {
            return self();
        }

        public abstract Pizza build();
        protected abstract Builder self();
    }

    public Pizza (Builder builder) {
        toppings = builder.toppings.clone();
    }

     public String toString() {
        return toppings.toString();
     }
}
