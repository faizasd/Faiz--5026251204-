package lw01.labwork;

public abstract class WashService implements Billable {
    private String id;
    private int days;
    private int units;
    

    protected WashService(String id, int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("days must be positive.");
        }
        this.id = id;
        this.days = days;
    }

    public String getId() {
        return id;
    }

    public int days() {
        return days;
    }

    @Override
    public abstract int calculateCharge();

    public int calculateCharge(int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Days must be positive.");
        }
        return days * calculateCharge();
    }

    public abstract String label();

    public final String summary() {
        return id + " | " + label() + " | " + calculateCharge();
    }
}