package com.example.mdg_soc_eduplexus;

public class MemberForQuiz {
    private String emailId;
    private Integer Score,testno;

    public MemberForQuiz(){
    }

    public MemberForQuiz(String emailId, Integer Score, Integer Testno) {
        this.emailId = emailId;
        this.Score = Score;
        this.testno = Testno;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Integer getScore() {
        return Score;
    }

    public void setScore(Integer Score) {
        this.Score = Score;
    }

    public Integer getTestno() {
        return testno;
    }

    public void setTestno(Integer testno) {
        this.testno = testno;
    }
}