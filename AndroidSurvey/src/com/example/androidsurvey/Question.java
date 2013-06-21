package com.example.androidsurvey;

import java.util.ArrayList;
import java.util.Collections;

public class Question {
	int ques_id;
	public int getQues_id() {
		return ques_id;
	}

	public void setQues_id(int ques_id) {
		this.ques_id = ques_id;
	}

	String question;
	int type;
	int count;
	ArrayList<String> answers;
	String userAnswers;

	public Question(String question, int type, int ques_id,int count) {
		this.question = question;
		this.type = type;
		this.ques_id=ques_id;
		this.count = count;
		answers = new ArrayList<String>();
		userAnswers = "0";
	}

	public Question() {

	}

	public Question(String... ans) {
		Collections.addAll(answers, ans);
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ArrayList<String> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<String> answers) {
		this.answers = answers;
	}

	public void setAnswers(String... ans) {
		Collections.addAll(answers, ans);
	}

	public void setUserAnser(String ans) {
		userAnswers = ans;
	}

	public void setUserAnserAppend(String ans) {
		userAnswers += userAnswers.length() > 0 ? "," + ans : ans;
	}

	public int getIndexByAnswer(String ans) {
		for (int i = 0; i < answers.size(); i++) {
			if (answers != null) {
				if (answers.get(i).equalsIgnoreCase(ans)) {
					return i;
				}
			}
		}
		return -1;
	}

	public ArrayList<String> getUserAnswer() {
		String[] ans = userAnswers.split(",");
		ArrayList<String> list = new ArrayList<String>(ans.length);
		for (String s : ans) {
			list.add(s);
		}
		return list;
	}
}
