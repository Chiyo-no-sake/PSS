package ch.supsi.pss.misc;

public enum WeekDays {
    MON(1), TUE(2), WED(3), THU(4), FRI(5), SAT(6), SUN(7);
    int day;

    WeekDays(int day){
        this.day = day;
    }


    @Override
    public String toString() {
        switch(day){
            case 1:
                return "MON";
            case 2:
                return "TUE";
            case 3:
                return "WED";
            case 4:
                return "THU";
            case 5:
                return "FRI";
            case 6:
                return "SAT";
            case 7:
                return "SUN";
        }

        return "NONE";
    }

    public static String toString(int day) {
        switch(day){
            case 1:
                return "MON";
            case 2:
                return "TUE";
            case 3:
                return "WED";
            case 4:
                return "THU";
            case 5:
                return "FRI";
            case 6:
                return "SAT";
            case 7:
                return "SUN";
        }

        return "NONE";
    }
}
