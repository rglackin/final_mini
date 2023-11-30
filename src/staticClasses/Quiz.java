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

    public void writeResult() {
        
        
        String query = String.format("INSERT INTO quiz_result (score,quiz_type,user_id,timer) VAlUES (%s,'%s',%s,%s)",mark,type.toString(),u.getID(),time);
        DAL.InsertQuery(query);

    }
    

    

}
