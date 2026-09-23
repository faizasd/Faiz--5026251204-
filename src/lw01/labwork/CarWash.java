package lw01.labwork;

public class CarWash extends WashService {
    public CarWash(String id, int days) {
        super(id, days);
    }

    public CarWash(String id, int days, int units) {
        super(id, days, units);
    
    }

    @Override
    public int calculateCharge() {
        int days = days();
        int charge;
        
        if (days <= 3) {
            charge = days * 35000;
        } else {
            charge = (3* 35000) + ((days -3)*25000);
        }
        return charge + 15000;
    }

    @Override
    public String label() {
        return "Car";
    }
}