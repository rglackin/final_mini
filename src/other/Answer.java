package other;

public class Answer {
    private int ansID;
    private String ansString;
    private int ans_qID;
    private boolean correct;
    public int getAnsID() {
        return ansID;
    }
    public void setAnsID(int ansID) {
        this.ansID = ansID;
    }
    public String getAnsString() {
        return ansString;
    }
    public void setAnsString(String ansString) {
        this.ansString = ansString;
    }
    public int getAns_qID() {
        return ans_qID;
    }
    public void setAns_qID(int ans_qID) {
        this.ans_qID = ans_qID;
    }
    public boolean isCorrect() {
        return correct;
    }
    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
    public Answer(int ansID, String ansString, int ans_qID, boolean correct) {
        this.ansID = ansID;
        this.ansString = ansString;
        this.ans_qID = ans_qID;
        this.correct = correct;
    }
    
}
