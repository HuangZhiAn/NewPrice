package price;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PriceMain {
    public static void main(String[] args){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        NewPriceListImpl newPriceList = new NewPriceListImpl();
        NewPriceImplSort newPriceList2 = new NewPriceImplSort();
        List<Price> prices = new ArrayList<>(8);
        try {
            Price p1,p2,p3,p4,p5,p6;
            p1 = new Price(1,dateFormat.parse("2018-01-01")
                    ,dateFormat.parse("2018-01-31"),dateFormat.parse("2018-01-01"),100D);
            p2 = new Price(2,dateFormat.parse("2018-01-02")
                    ,dateFormat.parse("2018-01-04"),dateFormat.parse("2018-01-02"),300D);
            p3 = new Price(3,dateFormat.parse("2018-01-05")
                    ,dateFormat.parse("2018-01-22"),dateFormat.parse("2018-01-04"),400D);
            p4 = new Price(4,dateFormat.parse("2018-01-20")
                    ,dateFormat.parse("2018-02-28"),dateFormat.parse("2018-01-15"),500D);
            p5 = new Price(5,dateFormat.parse("2018-02-05")
                    ,dateFormat.parse("2018-02-15"),dateFormat.parse("2018-02-01"),600D);
            p6 = new Price(6,dateFormat.parse("2018-02-15")
                    ,dateFormat.parse("2018-02-25"),dateFormat.parse("2018-02-01"),700D);
            prices.add(p1);
            prices.add(p2);
            prices.add(p3);
            prices.add(p4);
            prices.add(p5);
            prices.add(p6);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("普通集合实现");
        newPriceList2.addPrices(prices);
        //打印
        newPriceList2.print(newPriceList2.getResult());

        System.out.println("动态规划实现");
        newPriceList.addPrices(prices);
        //打印
        newPriceList.print(newPriceList.getResult());

    }
}
