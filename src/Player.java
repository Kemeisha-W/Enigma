package src;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    public int numDisks;
    public int correctCounter=0;
    public int hearts= 3;
    public Disk[] disks ;
    public ArrayList<String> questions;
    public HashMap<String, Integer>  answer;

    public  int chosenCol;
    public Player(int num){
        this.numDisks = num;
        disks = new Disk[numDisks];

        for (int i =0;i<numDisks;i++){
            disks[i] = new Disk();
        }

        //Set Question
//        questions = new ArrayList<String>();
//        questions.add("<html>In reply to an inquiry about the animals on his farm, the farmer says: " +
//                "“I only ever keep sheep, goats, and horses.<br/> In fact, at the moment they are all sheep bar " +
//                "three, all goats bar four, and all horses bar five.<br/>" +
//                " How many sheep does he have?</html>");
//        questions.add("<html>In reply to an inquiry about the animals on his farm, the farmer says:<br/> " +
//                "“I only ever keep sheep, goats, and horses.<br/> In fact, at the moment they are all sheep bar " +
//                "three, all goats bar four, and all horses bar five.<br/>" +
//                " How many goat does he have?</html>");
//        questions.add("<html>In reply to an inquiry about the animals on his farm, the farmer says: " +
//                "“I only ever keep sheep, goats, and horses.<br/> In fact, at the moment they are all sheep bar " +
//                "three, all goats bar four, and all horses bar five.<br/>" +
//                "” How many horse does he have?</html>");
//        questions.add("<html>I add six to eleven, What time do I need to wake up?</html>");
//
//        questions.add("<html>Turn me on my side and I am everything. Cut me in half and I am nothing. What am I?</html>");
//        questions.add("<html>One is to three as three is to five and five is to four and four is the magic number.<br/> What is the number pattern here?</html>");
//        questions.add("<html>I have 6 eggs. I broke 2, I cooked 2 and ate 2.<br/> How many eggs do I have?</html>");
//        questions.add("<html>BLANK ate nine and ten.</html>");
//
//        //Set answers
//        answer = new HashMap<String, Integer>();
//        answer.put(questions.get(0),3);
//        answer.put(questions.get(1),2);
//        answer.put(questions.get(2),1);
//        answer.put(questions.get(3),5);
//        answer.put(questions.get(4),8);
//        answer.put(questions.get(5),4);
//        answer.put(questions.get(6),6);
//        answer.put(questions.get(7),7);

        System.out.println(answer);
    }

}
