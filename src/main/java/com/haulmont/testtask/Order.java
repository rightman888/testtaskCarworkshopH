package com.haulmont.testtask;

import java.util.Date;

public class Order {
    private final long ID;
    private final String description;
    private final long client;
    private final long mechanic;
    private final Date startDate;
    private final Date finishDate;
    private final String cost;
    private final OrderStatusEnum status;
    
    public Order(long ID, String description, long client, 
            long mechanic, Date startDate, Date finishDate, 
            String cost, OrderStatusEnum status) {
        this.ID = ID;
        this.description = description;
        this.client = client;
        this.mechanic = mechanic;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.cost = cost;
        this.status = status;
    }
    
    public long getID() {
        return ID;
    }
    
    public String getDescription() {
        return description;
    }
    
    public long getClient() {
        return client;
    }
    
    public long getMechanic() {
        return mechanic;
    }
    
    public String getCost() {
        return cost;
    }
    
    public Date getStartDate() {
        return startDate;
    }
    
    public Date getFinishDate() {
        return finishDate;
    }
    
    public OrderStatusEnum getStatus() {
        return status;
    } 
    
    @Override
    public int hashCode() {
        return (int) ID;
    } 
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Order))
            return false;
        Order tempOrder = (Order) obj;
        return ID == tempOrder.ID;
    }    
}
