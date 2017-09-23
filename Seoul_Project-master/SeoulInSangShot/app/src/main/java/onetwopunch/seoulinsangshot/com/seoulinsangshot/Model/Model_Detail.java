package onetwopunch.seoulinsangshot.com.seoulinsangshot.Model;

/**
 * Created by 301 on 2017-09-17.
 */

public class Model_Detail {

    int num;
    String area;
    String img;
    String theme;
    String subway;
    String bus;
    String smartph;

    public Model_Detail(int num, String area, String img, String theme, String subway, String bus, String smartph) {
        this.num = num;
        this.area = area;
        this.img = img;
        this.theme = theme;
        this.subway = subway;
        this.bus = bus;
        this.smartph = smartph;
    }

    public int getNum() {
        return num;
    }

    public String getArea() {
        return area;
    }

    public String getImg() {
        return img;
    }

    public String getTheme() {
        return theme;
    }

    public String getSubway() {
        return subway;
    }

    public String getBus() {
        return bus;
    }

    public String getSmartph() {
        return smartph;
    }

    @Override
    public String toString() {
        return "Model_Detail{" +
                "num=" + num +
                ", ara='" + area + '\'' +
                ", img='" + img + '\'' +
                ", theme='" + theme + '\'' +
                ", subway='" + subway + '\'' +
                ", bus='" + bus + '\'' +
                ", smartph='" + smartph + '\'' +
                '}';
    }
}
