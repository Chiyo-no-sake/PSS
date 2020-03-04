package ch.supsi.labingsw1.model;

import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

//Primary are red yellow blue
/**
 * RED --> GREEN
 * YELLOW --> PURPLE
 * BLUE --> ORANGE
 */

public enum Colors {
    RED(new Color(231/255., 76/255., 60/255.,1.0)),
    BLUE(new Color(41/255., 128/255., 185/255.,1.0)),
    GREEN(new Color(46/255., 204/255., 113/255.,1.0)),
    YELLOW(new Color(241/255., 196/255., 15/255.,1.0)),
    PURPLE(new Color(155/255., 89/255., 182/255.,1.0)),
    ORANGE(new Color(211/255., 84/255., 0,1.0)),
    BLACK(new Color(0,0,0,1)),
    WHITE(new Color(1,1,1,1));

    private Color color;

    Colors(Color color){
        this.color = color;
    }

    Color getVal(){
        return this.color;
    }

    Colors getComplementary(){
        switch(this){
            case RED:
                return Colors.GREEN;
            case BLUE:
                return Colors.ORANGE;
            case PURPLE:
                return Colors.YELLOW;
            case GREEN:
                return Colors.RED;
            case YELLOW:
                return Colors.PURPLE;
            case ORANGE:
                return Colors.BLUE;
            case BLACK:
                return Colors.WHITE;
            case WHITE:
                return Colors.BLACK;
        }

        return null;
    }

    Color getSuggestedTextColor(){
        switch(this){
            case RED:
            case BLUE:
            case PURPLE:
            case ORANGE:
            case GREEN:
            case BLACK:
                return Colors.WHITE.getVal();
            default:
                return Colors.BLACK.getVal();
        }
    }
}
