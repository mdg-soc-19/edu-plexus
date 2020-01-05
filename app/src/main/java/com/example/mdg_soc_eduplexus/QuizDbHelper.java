package com.example.mdg_soc_eduplexus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION;

    static {
        DATABASE_VERSION = 1;
    }

    public static SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionsTable.TABLE_NAME);
        onCreate(db);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract.QuestionsTable.TABLE_NAME + " ( " +
                QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuizContract.QuestionsTable.TEST_NUMBER+ " INTEGER"+
                ")";
            db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
            fillQuestionsTable();
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }
    private void fillQuestionsTable() {
        QuestionGenerator q2 = new QuestionGenerator("The force of interaction between two atoms is given by F =αβexp(-x2/αkt)(α and β are constants). The dimension of β is", "MLT−2", "M2LT−4", "M2L2T-2", 2,1);
        addQuestion(q2);
        QuestionGenerator q3 = new QuestionGenerator("Let l, r, c and v represent their usual meaning. The dimension of 1/rcv in SI units will be", "LA-2", "A−1", "LTA", 2,2);
        addQuestion(q3);
        QuestionGenerator q4 = new QuestionGenerator("A particle is moving with a velocity v =K(yi + xj) where K is a constant. The general equation for its path is", " y2 = x2 + constant", "xy = constant", "y = x2 + constant", 1,1);
        addQuestion(q4);
        QuestionGenerator q5 = new QuestionGenerator("A simple harmonic motion is represented by y(t) = (5(sin(3*pi*t)+ sqrt(3)cos(3*pi*t))cm. The amplitude and time period of the motion are", "5cm, 0.67 seconds", "10 cm, 0.67 seconds ", "10 cm, 1.5 seconds", 2,2);
        addQuestion(q5);
        QuestionGenerator q6 = new QuestionGenerator("A closed organ pipe has a fundamental frequency of 1.5 kHz. The number of overtones that can be distinctly heard by a person with this organ pipe will be ", "4", "7", "6", 3,1);
        addQuestion(q6);
        QuestionGenerator q1 = new QuestionGenerator("Equation of traveling wave on a stretched string of linear density 5 g/m is y = 0.03sin(450t(in s) − 9x(in m)). The tension in die string is", "A", "B", "C", 1,1);
        addQuestion(q1);
        QuestionGenerator q7 = new QuestionGenerator("A is correct","A","B","C",1,2);
        addQuestion(q7);
        QuestionGenerator q8 = new QuestionGenerator("B is correct","A","B","C",2,2);
        addQuestion(q8);
        QuestionGenerator q9 = new QuestionGenerator("Most Abundant RadioActive Element on Earth ","Pu","U","Po",2,3);
        QuestionGenerator q10 = new QuestionGenerator("What happens to Litmus Paper when dipped in Acid","Turns Red","Turns Blue","Fades Away",1,3);
        QuestionGenerator q11= new QuestionGenerator("Which Orbital has no directional Orientation","s","p","d",1,3);
        QuestionGenerator q12= new QuestionGenerator("C is correct","A","B","C",3,3);
        QuestionGenerator q13 = new QuestionGenerator("1+1 = ?","3","1","2",3,4);
        QuestionGenerator q14= new QuestionGenerator("Find the Rank of QWERTY in the dictionary if only the words formed by the given letters are used to form words in that dictionary:","54","90","None of These",3,4);
        QuestionGenerator q15= new QuestionGenerator("Find SquareRoot of 3+18i","3.25946986 + 2.76118522 i","3.53367132 + 2.54692618 i","2.49868295 + 1.80094878 i",1,4);
        QuestionGenerator q16 = new QuestionGenerator("Find the probability of a solar eclipse happening in one place in one leap year","0.0455","0.0327","0.00942",2,4);
        addQuestion(q9);
        addQuestion(q10);
        addQuestion(q11);
        addQuestion(q12);
        addQuestion(q13);
        addQuestion(q14);
        addQuestion(q15);
        addQuestion(q16);
    }
    private void addQuestion(QuestionGenerator question) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuizContract.QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuizContract.QuestionsTable.TEST_NUMBER, question.getTestno());
        db.insert(QuizContract.QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<QuestionGenerator> getAllQuestions(int test_no) {
        List<QuestionGenerator> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM  " + QuizContract.QuestionsTable.TABLE_NAME + " WHERE "+ QuizContract.QuestionsTable.TEST_NUMBER +" = ? ", new String[]{String.valueOf(test_no)});

        if (c.moveToFirst()) {
            do {
                QuestionGenerator question = new QuestionGenerator();
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_ANSWER_NR)));
                question.setTestno(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.TEST_NUMBER)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}