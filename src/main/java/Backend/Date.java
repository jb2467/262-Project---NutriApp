package Backend;

public class Date{
    private int day = -1;
    private int month = -1;
    private int year = -1;

    //constructor
    public Date(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }
    
    //add one day
    public void incrementDay(){
        if(month == 2){
            day+=1;
            if(year % 4 == 0){
                if(day > 28){
                month += 1;
                if(month > 12){
                    month = 1;
                    year+=1;
                }
                day = 1;
            }
            else{
                if(day > 29){
                month += 1;
                if(month > 12){
                    month = 1;
                    year+=1;
                }
                day = 1;
            }
            }
        }
        else if(month % 2 == 0){
            day+=1;
            if(month == 8){
                if(day > 31){
                    day = 1;
                    month += 1;
                    if(month > 12){
                        month = 1;
                        year +=1;
                    }
                }
            }
            else if(day > 30){
                month += 1;
                if(month > 12){
                    month = 1;
                    year+=1;
                }
                day = 1;
            }
        }
        else{
            day += 1;
            if(day > 31){
                    day = 1;
                    month += 1;
                    if(month > 12){
                        month = 1;
                        year +=1;
                    }
                }
        }
    }
}

    //Getters
    public int getDay(){
        return day;
    }

    public int getMonth(){
        return month;
    }

    public int getYear(){
        return year;
    }

    //Setters
    public void setDay(int day){
        this.day = day;
    }
    public void setMonth(int month){
        this.month = month;
    }
    public void setyear(int year){
        this.year = year;
    }

    public String toString(){
        String dayStr = Integer.toString(day);
        if(day < 10){
            dayStr = "0"+Integer.toString(day);
        }
        String monthStr = Integer.toString(month);
        if(month < 10){
            monthStr = "0"+Integer.toString(month);
        }
        String yearStr = Integer.toString(year);
        
        String date = monthStr+"/"+dayStr+"/"+yearStr;

        return date;
    }
}

