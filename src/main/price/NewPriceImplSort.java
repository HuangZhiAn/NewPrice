package main.price;

import java.util.*;

public class NewPriceImplSort extends NewPriceList {
    @Override
    public void addPrice(Price price) {
        if(price.getStartDate().getTime()>=price.getEndDate().getTime()){
            System.out.println("添加的新价格时间区间不对");
            return;
        }
        srcPrice.add(price);
        analyse();
    }

    @Override
    public void addPrices(Collection<Price> c) {
        for(Price p:c){
            addPrice(p);
        }
        analyse();
    }

    @Override
    public List<Price> getResult() {
        return result;
    }

    private void analyse() {
        result.clear();
        //开始时间结束时间合并去重排序
        TreeSet<Date> dateSet = new TreeSet<>();
        for (Price p : srcPrice) {
            dateSet.add(p.getStartDate());
            dateSet.add(p.getEndDate());
        }
        //srcPrice 按更新时间排序
        sortSrcPrice();
        //分成不同的小区间,并取最新价格
        Date d1 = dateSet.pollFirst();
        for (; dateSet.size() > 0; ) {
            Date d2 = dateSet.pollFirst();
            for (int i = srcPrice.size() - 1; i > -1; i--) {
                Price price = srcPrice.get(i);
                assert d1 != null;
                assert d2 != null;
                if (price.getStartDate().getTime() <= d1.getTime() && price.getEndDate().getTime() >= d2.getTime()) {
                    result.add(new Price(null, d1, d2, price.getUpdate(), price.getPrice()));
                    break;
                }
            }
            d1 = d2;
        }
        //合并日期相邻，价格相同区间
        mergeSamePrice();
    }
    //按更新时间排序
    private void sortSrcPrice() {
        srcPrice.sort(Comparator.comparingLong((Price a) -> a.getUpdate().getTime()));
    }

    //合并相邻的相同价格区间
    private void mergeSamePrice() {
        for (int i = 0; i < result.size() - 1; i++) {
            Price price = result.get(i);
            Price price1 = result.get(i + 1);
            if (price.getEndDate().getTime() == price1.getStartDate().getTime()
                    && price.getPrice().equals(price1.getPrice())) {
                Price newPrice = new Price(null, price.getStartDate(), price1.getEndDate(), null, price.getPrice());
                result.add(i, newPrice);
                result.remove(price);
                result.remove(price1);
                i--;
            }
        }
    }

}
