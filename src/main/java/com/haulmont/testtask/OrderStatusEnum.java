package com.haulmont.testtask;

enum OrderStatusEnum {
    scheduled("Запланирован"), 
    done("Выполнен"), 
    accepted("Принят клиентом");
    
    private String name;
    
    private OrderStatusEnum(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    } 
    
    public static OrderStatusEnum parseString(String inputString) {
        switch (inputString) {
            case "Запланирован":
                return scheduled;
            case "Выполнен":
                return done;
            case "Принят клиентом":
                return accepted;
            default:
                return accepted;
        }
    }
    
}
