package staticClasses;

import other.*;

public class Quiz {
    private User u;
    private int mark; // quiztype 0 = inc difficulty, 1 = random, 2 = timed
    private double time;
    public enum quizType{
        INC_DIFF,
        RAND,
        TIMED
    }
    private quizType type;

    public quizType getType() {
        return type;
    }

    public void setType(quizType type) {
        this.type = type;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    /*public int getQuizType() {
        return quizType;
    }

    public void setQuizType(int quizType) {
        if (quizType >= 0 && quizType <= 2) {
            this.quizType = quizType;
        }
    }*/

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    

    public Quiz() {
        
    }

    public void writeResult() {
        /*String pathnameString = "";
        switch (type) {
            case INCREASING_DIFFICULTY:
                pathnameString = "incDiffResults.txt";
                break;
            case RANDOM:
                pathnameString = "randResults.txt";
                break;
            case TIMED:
                writeTimerResult();
                return;
        }
        try {
            File myObj = new File(pathnameString);
            myObj.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(pathnameString, true));

            Scanner reader = new Scanner(pathnameString);
            while (reader.hasNextLine()) {
                // writer.write("\n");
                reader.nextLine();

            }
            reader.close();

            writer.write("\n"+u.getID() + " " + getType().toString() + " " + u.getUsername() + " " + this.getMark()+"\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        
        String query = String.format("INSERT INTO quiz_result (score,quiz_type,user_id,timer) VAlUES (%s,'%s',%s,%s)",mark,type.toString(),u.getID(),time);
        DAL.InsertQuery(query);

    }
    /*public void writeTimerResult(){
        try {
            File myObj = new File("timerResults.txt");
            myObj.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("timerResults.txt", true));

            Scanner reader = new Scanner("timerResults.txt");
            while (reader.hasNextLine()) {
                // writer.write("\n");
                reader.nextLine();

            }
            reader.close();

            //writer.write(u.getID() + " " + this.getQuizType() + " " + u.getUsername() + " " + this.getMark() + " " +this.getTime()+"\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    

}
