package pattern.adapter.forObjectOriented;

/** Adaptee : 실제의 새롭거나 적용될 기능이 제공되는 클래스 */
public class Banner {
    private String str;

    public Banner(String str) {
        this.str = str;
    }

    public String showWithParen(){
        return "("+str+")";
    }

    public String showWithAster(){
        return "**"+str+"**";
    }
}
