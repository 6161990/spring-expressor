package pattern.builder.pizza;

import java.util.Objects;

public class YoonPizza extends Pizza {

    public enum Size { 작은거, 중간, 큰거 };
    private final Size size;

    public static class Builder extends Pizza.Builder {
        private final Size size;

        public Builder(Size size){
            this.size = Objects.requireNonNull(size);
        }

        public YoonPizza build() {
            return new YoonPizza(this);
        }

        @Override
        protected Pizza.Builder self() {
            return this;
        }
    }

    private YoonPizza(Builder builder) {
        super(builder);
        size = builder.size;
    }
}
