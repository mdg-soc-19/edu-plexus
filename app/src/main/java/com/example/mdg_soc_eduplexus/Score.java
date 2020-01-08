package com.example.mdg_soc_eduplexus;

import java.util.Date;

public class Score {private int score;
    private String User;
    private int testNo;

    public Score(int score, String messageUser, int testNo) {
        this.score = score;
        this.User = messageUser;
        this.testNo = testNo;
    }

    public Score() {

    }

    public int getScore() {
        return score;
    }

    public void setScore( int score) {
        this.score = score;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String messageUser) {
        this.User = messageUser;
    }

    public String  getTestNo() {
        return "Test NO. "+testNo;
    }

    public void setMessageTime(int messageTime) {
        this.testNo = messageTime;
    }
}
