package com.haulmont.testtask;

public class Client {
    private final long ID;
    private final String firstName;
    private final String lastName;
    private final String patronymic;
    private final String phone;
    
    public Client(long ID, String firstName, String lastName, 
            String patronymic, String phone) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.phone = phone;
    }
    
    public long getID() {
        return ID;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getPatronymic() {
        return patronymic;
    }
    
    public String getPhone() {
        return phone;
    } 
    
    public String getFullName() {
        return firstName + patronymic + lastName;
    }
    
    @Override
    public int hashCode() {
        return (int)ID;
    } 
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Client))
            return false;
        Client tempClient = (Client) obj;
        return ID == tempClient.ID;
    }
    
    @Override
    public String toString() {
        return getFullName();
    }  
}
