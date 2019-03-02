package price;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class NewPriceList {
    LinkedList<Price> result = new LinkedList<>();
    List<Price> srcPrice = new ArrayList<>();

    abstract public void addPrice(Price price) throws Exception;

    abstract public void addPrices(Collection<Price> c);

    abstract public List<Price> getResult();

    public List<Price> getSrcPrice() {
        return srcPrice;
    }

    public void print(List<Price> result) {
        prt(result);
    }

    public void print() {
        prt(result);
    }

    private void prt(List<Price> result) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (Price p : result) {
            System.out.println(dateFormat.format(p.getStartDate()) + "\t" + dateFormat.format(p.getEndDate()) + "\t" + p.getPrice());
        }
        System.out.println("---------------------");
    }
}
