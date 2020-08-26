package com.christopher_company.christopher.topquiz.modèle;

import java.util.Collections;
import java.util.List;

public class QuestionBank {

    private List<QuestionData> mQuestionList;
    private int mNextQuestionIndex;

    public QuestionBank(List<QuestionData> questionList) {
        mQuestionList = questionList;
        Collections.shuffle(mQuestionList); //On mélange les questions de la liste pour générer aléatoirement leur ordre d'apparition
        mNextQuestionIndex = 0;
    }

    public QuestionData getQuestion() {
        if(mNextQuestionIndex == mQuestionList.size()) //Si on a atteint la fin de la liste de questions
            mNextQuestionIndex = 0;

        return mQuestionList.get(mNextQuestionIndex++);
    }
}
