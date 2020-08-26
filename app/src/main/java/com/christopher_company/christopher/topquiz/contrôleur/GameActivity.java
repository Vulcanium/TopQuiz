package com.christopher_company.christopher.topquiz.contrôleur;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.christopher_company.christopher.topquiz.R;
import com.christopher_company.christopher.topquiz.modèle.QuestionBank;
import com.christopher_company.christopher.topquiz.modèle.QuestionData;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_question;
    private Button b_reponse1;
    private Button b_reponse2;
    private Button b_reponse3;
    private Button b_reponse4;

    private QuestionBank mQuestionBank;
    private QuestionData mCurrentQuestion;

    private int mNumberQuestions;
    private int mScore;

    public final static String GAME_ACTIVITY_SCORE_ID = "GAME_ACTIVITY_SCORE_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tv_question = (TextView) findViewById(R.id.activity_game_tv_question);
        b_reponse1 = (Button) findViewById(R.id.activity_game_b_reponse1);
        b_reponse2 = (Button) findViewById(R.id.activity_game_b_reponse2);
        b_reponse3 = (Button) findViewById(R.id.activity_game_b_reponse3);
        b_reponse4 = (Button) findViewById(R.id.activity_game_b_reponse4);

        mNumberQuestions = 4;
        mScore = 0;

        mQuestionBank = genererQuestions(); //On génère la liste des questions à poser lors du quizz

        /*Assignation d'un identifiant pour les boutons de réponse, afin de les distinguer*/
        b_reponse1.setTag(0);
        b_reponse2.setTag(1);
        b_reponse3.setTag(2);
        b_reponse4.setTag(3);

        /*Lorsque le joueur cliquera sur l'un des boutons de réponse,
        cela appellera la méthode onClick de cette instance de GameActivity*/
        b_reponse1.setOnClickListener(this);
        b_reponse2.setOnClickListener(this);
        b_reponse3.setOnClickListener(this);
        b_reponse4.setOnClickListener(this);

        mCurrentQuestion = mQuestionBank.getQuestion();
        afficherQuestion(mCurrentQuestion);

    }

    private QuestionBank genererQuestions(){
        QuestionData qu1 = new QuestionData
                ("Qui était le président de la République Française en 1995 ?",
                Arrays.asList("Nicolas Sarkozy", "Jacques Chirac", "François Hollande", "François Mitterrand"),
                        1);

        QuestionData qu2 = new QuestionData
                ("En quelle année l'Homme a-t-il posé le premier pas sur la Lune ?",
                        Arrays.asList("1958", "1968", "1969", "1957"),
                        2);

        QuestionData qu3 = new QuestionData
                ("Comment se nomme le célèbre sorceleur de la saga The Witcher ?",
                        Arrays.asList("Gilbert DeLaRive", "Gerard De Riv", "Gisbert Du Riverain", "Geralt De Riv"),
                        3);

        QuestionData qu4 = new QuestionData
                ("Quelle est la capitale du Portugal ?",
                        Arrays.asList("Lisbonne", "Stockholm", "Madrid", "Porto"),
                        0);

        return new QuestionBank(Arrays.asList(qu1, qu2, qu3, qu4));
    }

    private void afficherQuestion(QuestionData question){
        tv_question.setText(question.getQuestion()); //On met à jour le Text View en lui mettant le texte de la question

        /* On met à jour les boutons de réponse en leur mettant le texte des réponses */
        b_reponse1.setText(question.getListeReponses().get(0));
        b_reponse2.setText(question.getListeReponses().get(1));
        b_reponse3.setText(question.getListeReponses().get(2));
        b_reponse4.setText(question.getListeReponses().get(3));
    }

    @Override
    public void onClick(View v) {
        int indiceReponse = (int) v.getTag();
        if(indiceReponse == mCurrentQuestion.getIndiceBonneReponse()){ //Si la réponse trouvée par le joueur est la bonne
            Toast.makeText(this, "Bonne réponse !", Toast.LENGTH_SHORT).show();
            mScore++;
        }
        else { //Si la réponse trouvée par le joueur est fausse
            Toast.makeText(this, "Mauvaise réponse !", Toast.LENGTH_SHORT).show();
        }

        mNumberQuestions--;
        if(mNumberQuestions == 0) { //Fin du quizz, la limite de questions a été atteinte
            finQuizz();
        }
        else { //Sinon, on passe à la question suivante
            mCurrentQuestion = mQuestionBank.getQuestion();
            afficherQuestion(mCurrentQuestion);
        }
    }

    /* Fonction qui crée une boîte de dialogue pour mettre fin au quizz (destruction de l'instance actuelle de GameActivity) */
    private void finQuizz(){
        AlertDialog.Builder dialogueBox = new AlertDialog.Builder(this);
        dialogueBox.setTitle("Bien joué !")
                .setMessage("Votre score est de " + mScore)
                .setPositiveButton("Retour à l'accueil", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.putExtra(GAME_ACTIVITY_SCORE_ID, mScore); //Pour envoyer le score à MainActivity
                        setResult(RESULT_OK, intent); //Pour notifier que l'activité s'est terminée avec succès
                        finish(); //Détruit l'instance de l'activité (équivalent au bouton Back du téléphone)
                    }
                })
                .create()
                .show();
    }
}
