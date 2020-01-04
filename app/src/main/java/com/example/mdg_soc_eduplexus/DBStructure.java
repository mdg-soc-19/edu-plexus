package com.example.mdg_soc_eduplexus;

import android.provider.BaseColumns;

import java.security.PublicKey;

public class DBStructure {
    public static class Eventstable implements BaseColumns {
        public static final String DB_NAME = "EVENTS_DB";
        public static final int DB_VERSION = 1;
        public static final String EVENT_TABLE_NAME = "eventable";
        public static final String EVENT = "event";
        public static final String TIME = "event";
        public static final String DATE = "event";
        public static final String MONTH = "event";
        public static final String YEAR = "event";
    }
    //public static class QuestionsTable implements BaseColumns {
    //public static final String TABLE_NAME = "quiz_questions";
    //public static final String COLUMN_QUESTION = "question";
    //public static final String COLUMN_OPTION1 = "option1";
    //public static final String COLUMN_OPTION2 = "option2";
    //public static final String COLUMN_OPTION3 = "option3";
    //public static final String COLUMN_ANSWER_NR = "answer_nr";
    //public static final String TEST_NUMBER = "test_no";
}
