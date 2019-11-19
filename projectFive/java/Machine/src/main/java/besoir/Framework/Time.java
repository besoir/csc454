package besoir.Framework;

public class Time {
    private double tr, ti;

    public Time(double tr, double ti) {
        this.tr = tr;
        this.ti = ti;
    }

    public void setTime(double t) {
        if(tr == t) {
            ti = 2.0;
        } else {
            tr = t;
        }
    }

    public double getReal() {
        return this.tr;
    }

    public double getImaginary() {
        return this.ti;
    }

    @Override
    public String toString() {
        return "(" + tr + ", " + ti + ")";
    }
}