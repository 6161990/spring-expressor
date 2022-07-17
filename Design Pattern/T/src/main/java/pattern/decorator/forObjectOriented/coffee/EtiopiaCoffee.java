package pattern.decorator.forObjectOriented.coffee;

/**
 * ConcreteComponent : 추가적인 서비스가 필요한 실제 객체
 * */
public class EtiopiaCoffee extends Coffee{
    @Override
    public String brewing() {
        return "EtiopiaCoffee is brewing..";
    }
}
