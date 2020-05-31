package ch.supsi.pss.misc;

public enum WeekDays {
    MON(1), TUE(2), WED(3), THU(4), FRI(5), SAT(6), SUN(7);
    int day;

    WeekDays(int day){
        this.day = day;
    }

    public static String toString(int day) {
        switch(day){
            case 1:
                return "SUN";
            case 2:
                return "MON";
            case 3:
                return "TUE";
            case 4:
                return "WED";
            case 5:
                return "THU";
            case 6:
                return "FRI";
            case 7:
                return "SAT";

        }

        return "NONE";
    }
}
