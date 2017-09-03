package ru.ticketeen.api.response;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TicketsResponse {

    @SerializedName("document")
    public Document document;

    public TicketsResponse(Document document) {
        this.document = document;
    }

    public static class Document {

        @SerializedName("receipt")
        public Receipt receipt;

        public Document(Receipt receipt) {
            this.receipt = receipt;
        }
    }

    public static class Receipt {

        @SerializedName("ecashTotalSum")
        public Integer ecashTotalSum;

        @SerializedName("fiscalSign")
        public Integer fiscalSign;

        @SerializedName("fiscalDriveNumber")
        public String fiscalDriveNumber;

        @SerializedName("items")
        public List<Item> items = null;

        @SerializedName("operationType")
        public Integer operationType;

        @SerializedName("nds10")
        public Integer nds10;

        @SerializedName("taxationType")
        public Integer taxationType;

        @SerializedName("totalSum")
        public Integer totalSum;

        @SerializedName("fiscalDocumentNumber")
        public Integer fiscalDocumentNumber;

        @SerializedName("receiptCode")
        public Integer receiptCode;

        @SerializedName("userInn")
        public String userInn;

        @SerializedName("cashTotalSum")
        public Integer cashTotalSum;

        @SerializedName("requestNumber")
        public Integer requestNumber;

        @SerializedName("dateTime")
        public String dateTime;

        @SerializedName("operator")
        public String operator;

        @SerializedName("nds18")
        public Integer nds18;

        @SerializedName("user")
        public String user;

        @SerializedName("shiftNumber")
        public Integer shiftNumber;

        @SerializedName("kktRegId")
        public String kktRegId;

    }

    public static class Item {

        @SerializedName("nds18")
        public Integer nds18;

        @SerializedName("price")
        public Integer price;

        @SerializedName("name")
        public String name;

        @SerializedName("quantity")
        public Double quantity;

        @SerializedName("sum")
        public Integer sum;

        @SerializedName("nds10")
        public Integer nds10;

    }
}
