package com.example.smartplot.dto;

public class AdminAnalyticsResponse {

    private long totalUsers;
    private long totalAdmins;
    private long totalInvestors;
    private long totalRegularUsers;
    private long totalPlots;
    private long approvedPlots;
    private long pendingPlots;
    private long rejectedPlots;
    private long availablePlots;
    private long reservedPlots;
    private long soldPlots;
    private long totalBookings;
    private long totalPayments;
    private long successfulPayments;
    private long failedPayments;

    public AdminAnalyticsResponse() {
    }

    public AdminAnalyticsResponse(
            long totalUsers,
            long totalAdmins,
            long totalInvestors,
            long totalRegularUsers,
            long totalPlots,
            long approvedPlots,
            long pendingPlots,
            long rejectedPlots,
            long availablePlots,
            long reservedPlots,
            long soldPlots,
            long totalBookings,
            long totalPayments,
            long successfulPayments,
            long failedPayments
    ) {
        this.totalUsers = totalUsers;
        this.totalAdmins = totalAdmins;
        this.totalInvestors = totalInvestors;
        this.totalRegularUsers = totalRegularUsers;
        this.totalPlots = totalPlots;
        this.approvedPlots = approvedPlots;
        this.pendingPlots = pendingPlots;
        this.rejectedPlots = rejectedPlots;
        this.availablePlots = availablePlots;
        this.reservedPlots = reservedPlots;
        this.soldPlots = soldPlots;
        this.totalBookings = totalBookings;
        this.totalPayments = totalPayments;
        this.successfulPayments = successfulPayments;
        this.failedPayments = failedPayments;
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public long getTotalAdmins() {
        return totalAdmins;
    }

    public void setTotalAdmins(long totalAdmins) {
        this.totalAdmins = totalAdmins;
    }

    public long getTotalInvestors() {
        return totalInvestors;
    }

    public void setTotalInvestors(long totalInvestors) {
        this.totalInvestors = totalInvestors;
    }

    public long getTotalRegularUsers() {
        return totalRegularUsers;
    }

    public void setTotalRegularUsers(long totalRegularUsers) {
        this.totalRegularUsers = totalRegularUsers;
    }

    public long getTotalPlots() {
        return totalPlots;
    }

    public void setTotalPlots(long totalPlots) {
        this.totalPlots = totalPlots;
    }

    public long getApprovedPlots() {
        return approvedPlots;
    }

    public void setApprovedPlots(long approvedPlots) {
        this.approvedPlots = approvedPlots;
    }

    public long getPendingPlots() {
        return pendingPlots;
    }

    public void setPendingPlots(long pendingPlots) {
        this.pendingPlots = pendingPlots;
    }

    public long getRejectedPlots() {
        return rejectedPlots;
    }

    public void setRejectedPlots(long rejectedPlots) {
        this.rejectedPlots = rejectedPlots;
    }

    public long getAvailablePlots() {
        return availablePlots;
    }

    public void setAvailablePlots(long availablePlots) {
        this.availablePlots = availablePlots;
    }

    public long getReservedPlots() {
        return reservedPlots;
    }

    public void setReservedPlots(long reservedPlots) {
        this.reservedPlots = reservedPlots;
    }

    public long getSoldPlots() {
        return soldPlots;
    }

    public void setSoldPlots(long soldPlots) {
        this.soldPlots = soldPlots;
    }

    public long getTotalBookings() {
        return totalBookings;
    }

    public void setTotalBookings(long totalBookings) {
        this.totalBookings = totalBookings;
    }

    public long getTotalPayments() {
        return totalPayments;
    }

    public void setTotalPayments(long totalPayments) {
        this.totalPayments = totalPayments;
    }

    public long getSuccessfulPayments() {
        return successfulPayments;
    }

    public void setSuccessfulPayments(long successfulPayments) {
        this.successfulPayments = successfulPayments;
    }

    public long getFailedPayments() {
        return failedPayments;
    }

    public void setFailedPayments(long failedPayments) {
        this.failedPayments = failedPayments;
    }
}
