package payment.domain;


public class Payment {
    private String type;
    long amount;

    public Payment(String type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    public static Payment of(String point, int i) {
        return new Payment(point, i);
    }

    public static Payment freePoint(int i) {
        return Payment.of("point", i);
    }

    public static Payment payPoint(int i) {
        return Payment.of("point2", i);
    }

    public static Payment card(int i) {
        return Payment.of("card", i);
    }

    public String getType() {
        return type;
    }

    public long getAmount() {
        return amount;
    }
}