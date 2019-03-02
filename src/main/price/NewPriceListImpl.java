package main.price;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 动态规划实现
 */
public class NewPriceListImpl extends NewPriceList {

    @Override
    public void addPrice(Price price) {
        if (price.getStartDate().getTime() >= price.getEndDate().getTime()) {
            System.out.println("添加的新价格时间区间不对");
            return;
        }
        //将新元素添加到 sourceList 中
        srcPrice.add(price);

        //若是第一个，直接添加到结果中
        if (result.isEmpty()) {
            result.add(price);
        }

        //在所有结果区间前或所有区间后，直接添加到前面或后面
        if (result.getFirst().getStartDate().getTime() >= price.getEndDate().getTime()) {
            result.addFirst(price);
        } else if (result.getLast().getEndDate().getTime() <= price.getStartDate().getTime()) {
            result.addLast(price);
        } else {
            //找出涉及更新的区间
            int head = -1, tail = -1;
            for (Price p:result ) {
                //startDate所在区间
                if (p.getEndDate().getTime() >= price.getStartDate().getTime()
                        && price.getStartDate().getTime() >= p.getStartDate().getTime()) {
                    //记录开始区间的位置
                    head = result.indexOf(p);
                }
                //endDate所在区间
                if (price.getEndDate().getTime() >= p.getStartDate().getTime()
                        && price.getEndDate().getTime() <= p.getEndDate().getTime()) {
                    tail = result.indexOf(p);
                    break;
                }
            }
            //处理前后区间
            //处理后时间区间
            if (tail > -1) {
                long a = result.get(tail).getUpdate().getTime();
                if (a < price.getUpdate().getTime()) {
                    Price headPrice, tailPrice;
                    Price old = result.get(tail);
                    headPrice = (Price) old.clone();
                    headPrice.setEndDate(price.getEndDate());
                    tailPrice = (Price) old.clone();
                    tailPrice.setStartDate(price.getEndDate());

                    if (tailPrice.getStartDate().getTime() < tailPrice.getEndDate().getTime()) {
                        result.add(tail, tailPrice);
                    }
                    if (headPrice.getStartDate().getTime() < headPrice.getEndDate().getTime()) {
                        result.add(tail, headPrice);
                    }
                    result.remove(old);
                    tail = tail - 1;
                }
            } else {
                Price tailPrice;
                tailPrice = (Price) price.clone();
                tailPrice.setStartDate(result.getLast().getEndDate());
                result.addLast(tailPrice);
                tail = result.size() - 2;
            }
            //处理前时间区间
            if (head > -1) {
                if (result.get(head).getUpdate().getTime() < price.getUpdate().getTime()) {
                    Price headPrice, tailPrice;
                    Price old = result.get(head);
                    headPrice = (Price) old.clone();
                    headPrice.setEndDate(price.getStartDate());
                    tailPrice = (Price) price.clone();
                    tailPrice.setEndDate(old.getEndDate());

                    if (tailPrice.getStartDate().getTime() < tailPrice.getEndDate().getTime()) {
                        result.add(head, tailPrice);
                    }
                    if (headPrice.getStartDate().getTime() < headPrice.getEndDate().getTime()) {
                        result.add(head, headPrice);
                    }
                    result.remove(old);
                    head = head + 2;
                    tail++;
                }
            } else {
                Price headPrice;
                headPrice = (Price) price.clone();
                headPrice.setEndDate(result.getFirst().getStartDate());
                result.addFirst(headPrice);
                head = 1;
            }

            //遍历中间区间
            for (int i = head; i <= tail; i++) {
                if (result.get(i).getUpdate().getTime() < price.getUpdate().getTime()) {
                    result.get(i).setPrice(price.getPrice());
                    result.get(i).setUpdate(price.getUpdate());
                }
                //补全非连续区间
                if (i < tail && result.get(i).getEndDate().getTime() < result.get(i + 1).getStartDate().getTime()) {
                    Price fixPrice = new Price(null, result.get(i).getEndDate()
                            , result.get(i + 1).getStartDate(), price.getUpdate(), price.getPrice());
                    result.add(i + 1, fixPrice);
                    i++;
                }
            }
            //合并相邻的、相同价格、相同更新时间的区间
            mergeSamePriceAndUpdate();
        }
    }

    @Override
    public void addPrices(Collection<Price> c) {
        for (Price p : c) {
            addPrice(p);
        }
    }

    @Override
    public List<Price> getResult() {
        return mergeSamePrice();
    }

    //输出前合并价格相同相邻区间
    public void print() {
        super.print(mergeSamePrice());
    }

    //合并相邻的相同价格区间
    private List<Price> mergeSamePrice() {
        List<Price> printPriceList = new ArrayList<>(result);
        for (int i = 0; i < printPriceList.size() - 1; i++) {
            Price price = printPriceList.get(i);
            Price price1 = printPriceList.get(i + 1);
            if (price.getEndDate().getTime() == price1.getStartDate().getTime()
                    && price.getPrice().equals(price1.getPrice())) {
                Price newPrice = new Price(null, price.getStartDate()
                        , price1.getEndDate(), null, price.getPrice());
                printPriceList.add(i, newPrice);
                printPriceList.remove(price);
                printPriceList.remove(price1);
                i--;
            }
        }
        return printPriceList;
    }

    //合并相邻的,价格和更新时间相同的区间
    private void mergeSamePriceAndUpdate() {
        for (int i = 0; i < result.size() - 1; i++) {
            Price price = result.get(i);
            Price price1 = result.get(i + 1);
            if (price.getEndDate().getTime() == price1.getStartDate().getTime()
                    && price.getPrice().equals(price1.getPrice())
                    && price.getUpdate().getTime() == price1.getUpdate().getTime()) {
                Price newPrice = new Price(null, price.getStartDate(), price1.getEndDate(), price.getUpdate(), price.getPrice());
                result.add(i, newPrice);
                result.remove(price);
                result.remove(price1);
                i--;
            }
        }
    }

}
