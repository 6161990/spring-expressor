package payment.domain;

public class RecodeMaker {

    public Record createCommunityRecode(Command command) {
        Payment payment = command.paymentList.get(0);
        int supplyPrice = (int) Math.floor(payment.getAmount() / 1.1);
        int taxPrice = (int)payment.getAmount() - supplyPrice;
        return new Record(supplyPrice, taxPrice); // TODO supplyPoint를 Payment에서 하느냐 Calculator에 넘기느냐
        //return new Record(supplyPrice, taxPrice, noExchangeablePointSupplyPoint, noExchangeablePoint - noExchangeablePointSupplyPoint);
    }

/*    public Record createCommunityRecode(Calculator calculator) {
        int price = calculator.getAmountCommunityPrice();
        int amountSupplyPrice = (int) Math.ceil((price / 1.1));
        int noExchangeablePoint = calculator.getCommunityNoExchangeablePoint();
        int noExchangeablePointSupplyPoint = (int) Math.ceil((noExchangeablePoint / 1.1));
        return new Record(amountSupplyPrice, price - amountSupplyPrice, noExchangeablePointSupplyPoint, noExchangeablePoint - noExchangeablePointSupplyPoint);
    }*/

    public Record createMeetingRecode(Calculator calculator) {
        return new Record(57500, 0, 0, 0);
    }
}
