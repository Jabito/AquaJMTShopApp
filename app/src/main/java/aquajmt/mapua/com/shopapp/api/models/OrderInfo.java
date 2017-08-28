package aquajmt.mapua.com.shopapp.api.models;

/**
 * Created by Jabito on 27/08/2017.
 */

public class OrderInfo {

    private String id;
    private String orderedBy;
    private String orderedFrom;
    private String customerAddress;
    private Float longitude;
    private Float latitude;
    private String waterType;
    private Integer roundOrdered;
    private Integer slimOrdered;
    private Double costPerItem;
    private Double totalCost;
    private String moreDetails;
    private String createdOn;
    private String updatedOn;
    private String updatedBy;
    private Integer ratingGiven;
    private String status;
    private String comments;
    private String customerName;
    private String shopName;

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
}
