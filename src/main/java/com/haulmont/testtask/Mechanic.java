package com.haulmont.testtask;

public class Mechanic {
    private final long ID;
    private final String firstName;
    private final String lastName;
    private final String patronymic;
    private final String hourlyRate;
    
    public Mechanic(long ID, String firstName, String lastName, 
            String patronymic, String hourlyRate) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.hourlyRate = hourlyRate;
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
    
    public String getHourlyRate() {
        return hourlyRate;
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
        if (!(obj instanceof Mechanic))
            return false;
        Mechanic tempMechanic = (Mechanic) obj;
        return ID == tempMechanic.ID;
    }
    
    @Override
    public String toString() {
        return getFullName();
    } 
}
