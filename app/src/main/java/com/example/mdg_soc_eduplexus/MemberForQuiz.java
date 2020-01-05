package com.example.mdg_soc_eduplexus;

public class MemberForQuiz {
    private String emailId;
    private Integer Score;

    public MemberForQuiz(){
    }

    public MemberForQuiz(String emailId, Integer Score) {
        this.emailId = emailId;
        this.Score = Score;
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
}