package com.ddes.smart_meter_system_back_end.reading;

public class ReadingService {
    
    int electricityReading;

    public ReadingService(){
        electricityReading = 0;
    }

    public void setInitialReading(int firstReading){
        this.electricityReading = firstReading;
 
    }

    public int calculateReadingDifference(int newReading){
        //calculate the difference between new reading and old reading
        int readingDifference = this.electricityReading - newReading;
        
        return readingDifference;
    }

}
