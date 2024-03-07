package Backend;

public class Workout{
    private int minutes;
    private Intensity intensity;
    private Date date;
    private static final double HIGH_CALORIES_PER_MINUTE = 10.0;
    private static final double MEDIUM_CALORIES_PER_MINUTE = 7.5;
    private static final double LOW_CALORIES_PER_MINUTE = 5.0;

    public Workout(int minutes, String intensityStr, Date date) {
        this.minutes = minutes;
        if (intensityStr.equals("High")) {
            this.intensity = Intensity.HIGH;
        } else if (intensityStr.equals("Medium")) {
            this.intensity = Intensity.MEDIUM;
        } else {
            this.intensity = Intensity.LOW;
        }
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public int getMinutes() {
        return minutes;
    }

    public Intensity getIntensity() {
        return intensity;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setIntensity(Intensity intensity) {
        this.intensity = intensity;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public double CaloriesBurned() {
        switch (intensity) {
            case HIGH:
                return minutes * HIGH_CALORIES_PER_MINUTE;
            case MEDIUM:
                return minutes * MEDIUM_CALORIES_PER_MINUTE;
            case LOW:
                return minutes * LOW_CALORIES_PER_MINUTE;
            default:
                return 0.0;
        }
    }
}
