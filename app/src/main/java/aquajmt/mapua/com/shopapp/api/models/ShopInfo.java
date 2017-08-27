package aquajmt.mapua.com.shopapp.api.models;

import org.json.JSONObject;

/**
 * Created by Jabito on 26/08/2017.
 */

public class ShopInfo {

    private String id;
    private String businessName;
    private String address;
    private String cellphoneNo;
    private String alternateNo;
    private String timeOpen;
    private String timeClose;
    private Boolean allowSwap;
    private Boolean accountVerified;
    private String daysAvailable;
    private Boolean openOnHolidays;
    private String createdOn;
    private String updatedOn;
    private String updatedBy;
    private Float longitude;
    private Float latitude;
    private Float rating;
    private Boolean roundOffered;
    private Boolean slimOffered;
    private Integer roundStock;
    private Integer slimStock;
    private Boolean distilled;
    private Boolean purified;
    private Boolean mineral;
    private Boolean alkaline;
    private Double distilledPrice;
    private Double purifiedPrice;
    private Double mineralPrice;
    private Double alkalinePrice;
    private Double slimContainerCost;
    private Double roundContainerCost;

    public ShopInfo(){}

    public ShopInfo(JSONObject sij) {
        try {
            setId(null == sij.getString("id") ? "" : sij.getString("id"));
            setBusinessName(null == sij.getString("businessName") ? "" : sij.getString("businessName"));
            setAddress(null == sij.getString("address") ? "" : sij.getString("address"));
            setCellphoneNo(null == sij.getString("cellphoneNo") ? "" : sij.getString("cellphoneNo"));
            setAlternateNo(null == sij.getString("alternateNo") ? "" : sij.getString("alternateNo"));
            setTimeOpen(null == sij.getString("timeOpen") ? "" : sij.getString("timeOpen"));
            setTimeClose(null == sij.getString("timeClose") ? "" : sij.getString("timeClose"));
            setAllowSwap(sij.getBoolean("allowSwap"));
            setAccountVerified(sij.getBoolean("accountVerified"));
            setDaysAvailable(null == sij.getString("daysAvailable") ? "" : sij.getString("daysAvailable"));
            setOpenOnHolidays(sij.getBoolean("openOnHolidays"));
            setCreatedOn(null == sij.getString("createdOn") ? "" : sij.getString("createdOn"));
            setUpdatedOn(null == sij.getString("updatedOn") ? "" : sij.getString("updatedOn"));
            setUpdatedBy(null == sij.getString("updatedBy") ? "" : sij.getString("updatedBy"));
            setLongitude(null == sij.getString("longitude") ? 0.0f : Float.valueOf(String.valueOf(sij.getString("longitude"))));
            setLatitude(null == sij.getString("latitude") ? 0.0f : Float.valueOf(String.valueOf(sij.getString("latitude"))));
            setRating(null == sij.getString("rating") ? 0.0f : Float.valueOf(String.valueOf(sij.getString("rating"))));
            setRoundOffered(sij.getBoolean("roundOffered"));
            setSlimOffered(sij.getBoolean("slimOffered"));
            setRoundStock(null == sij.getString("roundStock") ? 0 : sij.getInt("roundStock"));
            setSlimStock(null == sij.getString("slimStock") ? 0 : sij.getInt("slimStock"));
            setDistilled(sij.getBoolean("distilled"));
            setPurified(sij.getBoolean("purified"));
            setMineral(sij.getBoolean("mineral"));
            setAlkaline(sij.getBoolean("alkaline"));
            setDistilledPrice(null == sij.getString("distilledPrice") ? 0.0d : sij.getDouble("distilledPrice"));
            setPurifiedPrice(null == sij.getString("purifiedPrice") ? 0.0d : sij.getDouble("purifiedPrice"));
            setMineralPrice(null == sij.getString("mineralPrice") ? 0.0d : sij.getDouble("mineralPrice"));
            setAlkalinePrice(null == sij.getString("alkalinePrice") ? 0.0d : sij.getDouble("alkalinePrice"));
            setSlimContainerCost(null == sij.getString("slimContainerCost") ? 0.0d : sij.getDouble("slimContainerCost"));
            setRoundContainerCost(null == sij.getString("roundContainerCost") ? 0.0d : sij.getDouble("roundContainerCost"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCellphoneNo() {
        return cellphoneNo;
    }

    public void setCellphoneNo(String cellphoneNo) {
        this.cellphoneNo = cellphoneNo;
    }

    public String getAlternateNo() {
        return alternateNo;
    }

    public void setAlternateNo(String alternateNo) {
        this.alternateNo = alternateNo;
    }

    public String getTimeOpen() {
        return timeOpen;
    }

    public void setTimeOpen(String timeOpen) {
        this.timeOpen = timeOpen;
    }

    public String getTimeClose() {
        return timeClose;
    }

    public void setTimeClose(String timeClose) {
        this.timeClose = timeClose;
    }

    public Boolean getAllowSwap() {
        return allowSwap;
    }

    public void setAllowSwap(Boolean allowSwap) {
        this.allowSwap = allowSwap;
    }

    public Boolean getAccountVerified() {
        return accountVerified;
    }

    public void setAccountVerified(Boolean accountVerified) {
        this.accountVerified = accountVerified;
    }

    public String getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(String daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

    public Boolean getOpenOnHolidays() {
        return openOnHolidays;
    }

    public void setOpenOnHolidays(Boolean openOnHolidays) {
        this.openOnHolidays = openOnHolidays;
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

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Boolean getRoundOffered() {
        return roundOffered;
    }

    public void setRoundOffered(Boolean roundOffered) {
        this.roundOffered = roundOffered;
    }

    public Boolean getSlimOffered() {
        return slimOffered;
    }

    public void setSlimOffered(Boolean slimOffered) {
        this.slimOffered = slimOffered;
    }

    public Integer getRoundStock() {
        return roundStock;
    }

    public void setRoundStock(Integer roundStock) {
        this.roundStock = roundStock;
    }

    public Integer getSlimStock() {
        return slimStock;
    }

    public void setSlimStock(Integer slimStock) {
        this.slimStock = slimStock;
    }

    public Boolean getDistilled() {
        return distilled;
    }

    public void setDistilled(Boolean distilled) {
        this.distilled = distilled;
    }

    public Boolean getPurified() {
        return purified;
    }

    public void setPurified(Boolean purified) {
        this.purified = purified;
    }

    public Boolean getMineral() {
        return mineral;
    }

    public void setMineral(Boolean mineral) {
        this.mineral = mineral;
    }

    public Boolean getAlkaline() {
        return alkaline;
    }

    public void setAlkaline(Boolean alkaline) {
        this.alkaline = alkaline;
    }

    public Double getDistilledPrice() {
        return distilledPrice;
    }

    public void setDistilledPrice(Double distilledPrice) {
        this.distilledPrice = distilledPrice;
    }

    public Double getPurifiedPrice() {
        return purifiedPrice;
    }

    public void setPurifiedPrice(Double purifiedPrice) {
        this.purifiedPrice = purifiedPrice;
    }

    public Double getMineralPrice() {
        return mineralPrice;
    }

    public void setMineralPrice(Double mineralPrice) {
        this.mineralPrice = mineralPrice;
    }

    public Double getAlkalinePrice() {
        return alkalinePrice;
    }

    public void setAlkalinePrice(Double alkalinePrice) {
        this.alkalinePrice = alkalinePrice;
    }

    public Double getSlimContainerCost() {
        return slimContainerCost;
    }

    public void setSlimContainerCost(Double slimContainerCost) {
        this.slimContainerCost = slimContainerCost;
    }

    public Double getRoundContainerCost() {
        return roundContainerCost;
    }

    public void setRoundContainerCost(Double roundContainerCost) {
        this.roundContainerCost = roundContainerCost;
    }
}
