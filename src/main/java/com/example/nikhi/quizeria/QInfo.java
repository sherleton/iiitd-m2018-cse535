package com.example.nikhi.quizeria;

class QInfo {

    private String question;
    private int answer, input;

    QInfo(String question, int answer)
    {
        this.question = question;
        this.answer = answer;
        this.input = -1;
    }

    QInfo(String question, int answer, int input)
    {
        this.question = question;
        this.answer = answer;
        this.input = input;
    }

    public String getQuestion() {
        return question;
    }

    public int getAnswer() {
        return answer;
    }

    public int getInput() { return input; }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public void setInput(int input) {
        this.input = input;
    }
}
