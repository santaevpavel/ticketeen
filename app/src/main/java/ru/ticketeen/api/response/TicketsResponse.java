package ru.ticketeen.api.response;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TicketsResponse {

    @SerializedName("document")
    public Document document;

    public TicketsResponse(Document document) {
        this.document = document;
    }

    public static class Document implements Serializable {

        @SerializedName("receipt")
        public Receipt receipt;

        public Document(Receipt receipt) {
            this.receipt = receipt;
        }
    }

    public static class Receipt implements Serializable {

        @SerializedName("ecashTotalSum")
        public Long ecashTotalSum;

        @SerializedName("fiscalSign")
        public Long fiscalSign;

        @SerializedName("fiscalDriveNumber")
        public String fiscalDriveNumber;

        @SerializedName("items")
        public List<Item> items = null;

        @SerializedName("operationType")
        public Long operationType;

        @SerializedName("nds10")
        public Long nds10;

        @SerializedName("taxationType")
        public Long taxationType;

        @SerializedName("totalSum")
        public Long totalSum;

        @SerializedName("fiscalDocumentNumber")
        public Long fiscalDocumentNumber;

        @SerializedName("receiptCode")
        public Long receiptCode;

        @SerializedName("userInn")
        public String userInn;

        @SerializedName("cashTotalSum")
        public Long cashTotalSum;

        @SerializedName("requestNumber")
        public Long requestNumber;

        @SerializedName("dateTime")
        public String dateTime;

        @SerializedName("operator")
        public String operator;

        @SerializedName("nds18")
        public Long nds18;

        @SerializedName("user")
        public String user;

        @SerializedName("shiftNumber")
        public Long shiftNumber;

        @SerializedName("kktRegId")
        public String kktRegId;

    }

    public static class Item implements Serializable {

        @SerializedName("nds18")
        public Long nds18;

        @SerializedName("price")
        public Long price;

        @SerializedName("name")
        public String name;

        @SerializedName("quantity")
        public Double quantity;

        @SerializedName("sum")
        public Long sum;

        @SerializedName("nds10")
        public Long nds10;

    }
}
