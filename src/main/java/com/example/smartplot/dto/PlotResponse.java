package com.example.smartplot.dto;

import java.math.BigDecimal;

public class PlotResponse {

    private Integer plotId;
    private String plotNumber;
    private String ownerName;
    private String location;
    private Integer areaSqft;
    private BigDecimal price;
    private String status;
    private String approvalStatus;
    private String imageUrl;
    private String bookedByName;
    private String bookedByEmail;

    public PlotResponse() {
    }

    public PlotResponse(
            Integer plotId,
            String plotNumber,
            String ownerName,
            String location,
            Integer areaSqft,
            BigDecimal price,
            String status,
            String approvalStatus,
            String imageUrl,
            String bookedByName,
            String bookedByEmail
    ) {
        this.plotId = plotId;
        this.plotNumber = plotNumber;
        this.ownerName = ownerName;
        this.location = location;
        this.areaSqft = areaSqft;
        this.price = price;
        this.status = status;
        this.approvalStatus = approvalStatus;
        this.imageUrl = imageUrl;
        this.bookedByName = bookedByName;
        this.bookedByEmail = bookedByEmail;
    }

    public Integer getPlotId() {
        return plotId;
    }

    public void setPlotId(Integer plotId) {
        this.plotId = plotId;
    }

    public String getPlotNumber() {
        return plotNumber;
    }

    public void setPlotNumber(String plotNumber) {
        this.plotNumber = plotNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getAreaSqft() {
        return areaSqft;
    }

    public void setAreaSqft(Integer areaSqft) {
        this.areaSqft = areaSqft;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBookedByName() {
        return bookedByName;
    }

    public void setBookedByName(String bookedByName) {
        this.bookedByName = bookedByName;
    }

    public String getBookedByEmail() {
        return bookedByEmail;
    }

    public void setBookedByEmail(String bookedByEmail) {
        this.bookedByEmail = bookedByEmail;
    }
}
