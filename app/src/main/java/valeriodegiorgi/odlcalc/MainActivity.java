package valeriodegiorgi.odlcalc;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void calcola(View view) {
        TextView ora = (TextView) findViewById(R.id.ora);
        int oraInizio = Integer.parseInt(ora.getText().toString());

        TextView minuti = (TextView) findViewById(R.id.minuti);
        int minutiInizio = Integer.parseInt(minuti.getText().toString());

        TextView nPezzi = (TextView) findViewById(R.id.Pezzi);
        int numPezzi = Integer.parseInt(nPezzi.getText().toString());

        TextView tPezzi = (TextView) findViewById(R.id.tempoPezzi);
        int tempoPezzi = Integer.parseInt(tPezzi.getText().toString());

        TextView attrezzaggio = (TextView) findViewById(R.id.attrezzaggio);
        int attSecondi = Integer.valueOf(attrezzaggio.getText().toString());


        int oraSecondi = (oraInizio * 60) * 60;
        int minutiSecondi = minutiInizio * 60;
        int odl = numPezzi * tempoPezzi;
        double tempoTot = oraSecondi + minutiSecondi + odl + attSecondi;
        double oraParziale = (tempoTot / 60) / 60;
        int oraFine = (int) oraParziale;
        double minFine = oraParziale - oraFine;
        double Minuti = (minFine * 100) * 0.6;
        int MinFine2 = (int) Minuti;

        CheckBox mensaCheck = (CheckBox) findViewById(R.id.mensa_checkbox1);
        boolean isMensaChecked = mensaCheck.isChecked();

        if(isMensaChecked) {
            if (oraParziale >= 12.50 && oraParziale <= 13.50 || oraInizio <= 12 && oraParziale >= 12.50 || oraInizio <= 12 && minutiInizio <= 30 && minutiInizio >= 0 && oraParziale >= 12.50) {
                oraFine++;
            }
        }

        TextView Ris = (TextView) findViewById(R.id.risultato);
        String number = "" + oraFine + " e minuti " + MinFine2;
        Ris.setText(number);

    }

    @Override
    public void onPause() {
        CheckBox mensaCheck = (CheckBox) findViewById(R.id.mensa_checkbox1);
        super.onPause();
        save(mensaCheck.isChecked());
    }

    @Override
    public void onResume() {
        CheckBox mensaCheck = (CheckBox) findViewById(R.id.mensa_checkbox1);
        super.onResume();
        mensaCheck.setChecked(load());
    }

    private void save(final boolean isChecked) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("check", isChecked);
        editor.commit();
    }

    private boolean load() {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("check", false);
    }

}

