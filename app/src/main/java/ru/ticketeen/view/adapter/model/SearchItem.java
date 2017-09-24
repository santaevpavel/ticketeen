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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchItem that = (SearchItem) o;

        if (isWeightedGood != that.isWeightedGood) return false;
        if (!name.equals(that.name)) return false;
        if (!price.equals(that.price)) return false;
        if (sum != null ? !sum.equals(that.sum) : that.sum != null) return false;
        if (!quantity.equals(that.quantity)) return false;
        if (market != null ? !market.equals(that.market) : that.market != null) return false;
        return dateTime.equals(that.dateTime);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + (sum != null ? sum.hashCode() : 0);
        result = 31 * result + quantity.hashCode();
        result = 31 * result + (isWeightedGood ? 1 : 0);
        result = 31 * result + (market != null ? market.hashCode() : 0);
        result = 31 * result + dateTime.hashCode();
        return result;
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
