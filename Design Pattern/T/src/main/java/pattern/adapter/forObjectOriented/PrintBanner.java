package pattern.adapter.forObjectOriented;

/**합성 이용*/
/** Adapter : Target 인터페이스에 Adaptee 의 인터페이스를 맞춰주는 클래스 */
public class PrintBanner implements Print {

    private Banner banner;

    public PrintBanner(String str) {
        this.banner = new Banner(str);
    }

    @Override
    public String printWeek() {
        return banner.showWithParen();
    }

    @Override
    public String printStrong() {
        return banner.showWithAster();
    }
}
