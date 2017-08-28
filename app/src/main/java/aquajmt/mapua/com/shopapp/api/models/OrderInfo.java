package aquajmt.mapua.com.shopapp.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Jabito on 27/08/2017.
 */

public class OrderInfo {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("orderedBy")
    @Expose
    private String orderedBy;
    @SerializedName("orderedFrom")
    @Expose
    private String orderedFrom;
    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("customerAddress")
    @Expose
    private String customerAddress;
    @SerializedName("shopName")
    @Expose
    private String shopName;
    @SerializedName("longitude")
    @Expose
    private Float longitude;
    @SerializedName("latitude")
    @Expose
    private Float latitude;
    @SerializedName("waterType")
    @Expose
    private String waterType;
    @SerializedName("roundOrdered")
    @Expose
    private int roundOrdered;
    @SerializedName("slimOrdered")
    @Expose
    private int slimOrdered;
    @SerializedName("costPerItem")
    @Expose
    private double costPerItem;
    @SerializedName("totalCost")
    @Expose
    private double totalCost;
    @SerializedName("moreDetails")
    @Expose
    private String moreDetails;
    @SerializedName("createdOn")
    @Expose
    private String createdOn;
    @SerializedName("updatedOn")
    @Expose
    private String updatedOn;
    @SerializedName("updatedBy")
    @Expose
    private String updatedBy;
    @SerializedName("ratingGiven")
    @Expose
    private Integer ratingGiven;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("comments")
    @Expose
    private String comments;
    @SerializedName("customerContact")
    @Expose
    private String customerContact;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(String orderedBy) {
        this.orderedBy = orderedBy;
    }

    public String getOrderedFrom() {
        return orderedFrom;
    }

    public void setOrderedFrom(String orderedFrom) {
        this.orderedFrom = orderedFrom;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public String getWaterType() {
        return waterType;
    }

    public void setWaterType(String waterType) {
        this.waterType = waterType;
    }

    public Integer getRoundOrdered() {
        return roundOrdered;
    }

    public void setRoundOrdered(Integer roundOrdered) {
        this.roundOrdered = roundOrdered;
    }

    public Integer getSlimOrdered() {
        return slimOrdered;
    }

    public void setSlimOrdered(Integer slimOrdered) {
        this.slimOrdered = slimOrdered;
    }

    public Double getCostPerItem() {
        return costPerItem;
    }

    public void setCostPerItem(Double costPerItem) {
        this.costPerItem = costPerItem;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public String getMoreDetails() {
        return moreDetails;
    }

    public void setMoreDetails(String moreDetails) {
        this.moreDetails = moreDetails;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Integer getRatingGiven() {
        return ratingGiven;
    }

    public void setRatingGiven(Integer ratingGiven) {
        this.ratingGiven = ratingGiven;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setRoundOrdered(int roundOrdered) {
        this.roundOrdered = roundOrdered;
    }

    public void setSlimOrdered(int slimOrdered) {
        this.slimOrdered = slimOrdered;
    }

    public void setCostPerItem(double costPerItem) {
        this.costPerItem = costPerItem;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }
}
