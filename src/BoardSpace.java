package src;

import java.awt.*;

public class BoardSpace {
    public int colNumber;
    public int x;
    public Boolean isCorrect;

    public boolean hasDisk=false;
    public Color color =new Color(232, 142, 29);
    public boolean spaceFilled=false;


    public BoardSpace(int colNumber){
        this.colNumber = colNumber;
    }

}
