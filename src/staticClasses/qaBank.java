package staticClasses;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.Random;

import other.*;

public class qaBank {
	final Question[] qArray = getQuestions();

	public int[] incOrRand(boolean incOrRand) {// incOrRand=true: Increasing difficulty. False: Random Questions
		int[] arr = new int[qArray.length];
		if (incOrRand) {
			return arr;
		} else {
			arr = randomise(arr);
			return arr;
		}

	}

	public int[] randomise(int[] arr) {
		Random rand = new Random();
		for (int i = 0; i < arr.length; i++) {
			int randomIndexToSwap = rand.nextInt(arr.length - 1);

			int temp = arr[randomIndexToSwap];
			arr[randomIndexToSwap] = arr[i];
			arr[i] = temp;

		}
		return arr;
	}

	public Question returnQuestionAtPointer(int q) {
		return qArray[q];
	}

	public Answer returnAnswerAtPointer(int questionID, int ansPosition) {
		Answer[] ansArray = getAnswers(questionID);
		return ansArray[ansPosition];
	}

	public int correctAnswer(int k) {// k+1 = question number, returns corresponding correct j value for answerChoice
										// method
		int[] correctAnswer = { 3, 1, 1, 2, 1, 1, 2, 1, 0, 3, 1, 0, 0, 2, 1, 3, 2, 1 };
		return correctAnswer[k];
	}

	public Question[] getQuestions() {
		String query = "SELECT * FROM question";
		Object[] objArray = DAL.SelectQuery(query, rset -> {
			ArrayList<Question> questions = new ArrayList<Question>();
			while (rset.next()) {
				Question q = new Question(rset.getInt("quest_id"), rset.getString("question_text"));
				questions.add(q);
			}
			return questions.toArray();
		});
		Question[] qArray = Arrays.copyOf(objArray, objArray.length, Question[].class);
		return qArray;
	}

	public Answer[] getAnswers(int questionID) {
		String query = String.format("SELECT * FROM answer WHERE question_id = %s", questionID);
		Object[] objArray = DAL.SelectQuery(query, rset -> {
			ArrayList<Answer> answers = new ArrayList<Answer>();
			while (rset.next()) {
				Answer a = new Answer(rset.getInt("ans_id"), rset.getString("ans_text"), rset.getInt("question_id"),
						rset.getBoolean("correct"));
				answers.add(a);
			}
			return answers.toArray();
		});
		Answer[] ansArray = Arrays.copyOf(objArray, objArray.length, Answer[].class);
		return ansArray;
	}
}
