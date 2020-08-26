package com.christopher_company.christopher.topquiz.modèle;

import java.util.List;

public class QuestionData {
    private String mQuestion;
    private List<String> mListeReponses;
    private int mIndiceBonneReponse;

    public QuestionData(String mQuestion, List<String> mListeReponses, int mIndiceBonneReponse) {
        this.mQuestion = mQuestion;
        this.mListeReponses = mListeReponses;

        if(mIndiceBonneReponse >= 0 && mIndiceBonneReponse <= 3)
            this.mIndiceBonneReponse = mIndiceBonneReponse;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String mQuestion) {
        this.mQuestion = mQuestion;
    }

    public List<String> getListeReponses() {
        return mListeReponses;
    }

    public void setListeReponses(List<String> mListeReponses) {
        this.mListeReponses = mListeReponses;
    }

    public int getIndiceBonneReponse() {
        return mIndiceBonneReponse;
    }

    public void setIndiceBonneReponse(int mIndiceBonneReponse) {
        this.mIndiceBonneReponse = mIndiceBonneReponse;
    }
}
