package com.christopher_company.christopher.topquiz.contrôleur;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.christopher_company.christopher.topquiz.R;
import com.christopher_company.christopher.topquiz.modèle.UserData;

public class MainActivity extends AppCompatActivity {

    private TextView tv_bienvenue;
    private EditText saisie_prenom;
    private Button b_jouer;
    private UserData mUser;

    private static final int GAME_ACTIVITY_ID = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_bienvenue = (TextView) findViewById(R.id.activity_main_tv_bienvenue);
        saisie_prenom = (EditText) findViewById(R.id.activity_main_saisie_prenom);
        b_jouer = (Button) findViewById(R.id.activity_main_b_jouer);

        b_jouer.setEnabled(false); //Pour griser le bouton Jouer ! au lancement de l'application

        saisie_prenom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                b_jouer.setEnabled(s.toString().length() >= 3); //Il faut saisir au moins 3 lettres pour pouvoir cliquer sur Jouer !
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        b_jouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mUser.setPrenom(saisie_prenom.getText().toString());

                Intent gameActivity = new Intent(MainActivity.this, GameActivity.class);
                startActivityForResult(gameActivity, GAME_ACTIVITY_ID); //Pour récupérer un résultat de GameActivity
            }
        });

    }


    /* Pour récupérer le score envoyé par GameActivity */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == GAME_ACTIVITY_ID && resultCode == RESULT_OK){
            int score = data.getIntExtra(GameActivity.GAME_ACTIVITY_SCORE_ID, 0);
        }
    }
}
