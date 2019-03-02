package price;

import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings({"all"})
public class Price {
    private Integer number;
    private Date startDate;
    private Date endDate;
    private Date update;
    private Double price;

    public Price() {
    }

    public Price(Integer number, Date startDate, Date endDate, Date update, Double price) {
        this.number = number;
        this.startDate = startDate;
        this.endDate = endDate;
        this.update = update;
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    protected Object clone() {
        return new Price(number, startDate, endDate, update, price);
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(startDate) + "\t" + dateFormat.format(endDate) + "\t" + dateFormat.format(update) + "\t" + price;
    }
}
