package other;

public class Question {
    private int questionID;
    private String qString;
    public Question(int questionID, String qString) {
        this.questionID = questionID;
        this.qString = qString;
    }
    @Override
    public String toString() {
        return "Question [questionID=" + questionID + ", qString=" + qString + "]";
    }
    public int getQuestionID() {
        return questionID;
    }
    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }
    public String getqString() {
        return qString;
    }
    public void setqString(String qString) {
        this.qString = qString;
    }
    
}
