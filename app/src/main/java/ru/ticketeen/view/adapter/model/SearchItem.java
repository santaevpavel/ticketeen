package ru.ticketeen.view.adapter.model;


public class SearchItem {
    private String name;

    private Double price;
    private Double sum;
    private Double quantity;
    private boolean isWeightedGood;

    private String market;
    private String dateTime;

    public SearchItem() {
    }

    public boolean isWeightedGood() {
        return isWeightedGood;
    }

    public void setWeightedGood(boolean weightedGood) {
        isWeightedGood = weightedGood;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Double getSum() {
        return sum;
    }

    public Double getQuantity() {
        return quantity;
    }

    public String getMarket() {
        return market;
    }

    public String getDateTime() {
        return dateTime;
    }
}
