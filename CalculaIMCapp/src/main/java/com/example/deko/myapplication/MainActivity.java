package com.example.deko.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private boolean flagPesoOK = false;
    private boolean flagAlturaOK = false;
    EditText txtPeso;
    EditText txtAltura;
    TextView tvTimc;
    TextView tvDiag;
    Button btnCalc;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            txtPeso = (EditText) findViewById(R.id.txtPeso);
            txtAltura = (EditText) findViewById(R.id.txtAltura);
            tvTimc = (TextView) findViewById(R.id.tvIMC);
            tvDiag = (TextView) findViewById(R.id.tvDiag);
            btnCalc = (Button) findViewById(R.id.btnCalc);
            btnCalc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    double dPeso, dAltura,dIMC;
                    dPeso = getPeso();
                    dAltura = getAltura();
                    if (isFlagsOK()){
                        dIMC= (dPeso)/(dAltura*dAltura);
                        tvTimc.setText(String.format(Locale.ENGLISH,"%.2f",dIMC));
                        tvDiag.setText(getsDiag(dIMC));
                        toggleVisibility(true);
                    }
                }
            });
        }

    private String getsDiag(double dImc) {
        String sDiag;
        if (dImc<17)
            sDiag = getString(R.string.diag_0);
        else if (dImc>=17 && dImc < 18.5)
            sDiag = getString(R.string.diag_1);
        else if (dImc>=18.5 && dImc < 25)
            sDiag = getString(R.string.diag_2);
        else if (dImc>=25 && dImc < 30)
            sDiag = getString(R.string.diag_3);
        else if (dImc>=30 && dImc < 35)
            sDiag = getString(R.string.diag_4);
        else if (dImc>=35 && dImc < 40)
            sDiag = getString(R.string.diag_5);
        else
            sDiag = getString(R.string.diag_6);
        return sDiag;
    }

    private boolean isFlagsOK(){
        return (flagAlturaOK && flagPesoOK);
    }

    private double getPeso(){
        double dPeso;
        String sPeso = txtPeso.getText().toString();
        if (!sPeso.isEmpty()) {
            flagPesoOK =true;
            dPeso = Double.parseDouble(sPeso);
            return dPeso;
        }
        else flagPesoOK=false;

        return 0;
    }

    private double getAltura(){
        double dAltura;
        String sAltura = txtAltura.getText().toString();
        if (!sAltura.isEmpty()){
            flagAlturaOK=true;
            dAltura = Double.parseDouble(sAltura);
            return dAltura;
        }
        else flagAlturaOK=false;
        return 0;
    }

    public void limpaForm(View view){
        txtAltura.setText("");
        txtPeso.setText("");
        tvTimc.setText ("");
        tvDiag.setText("");
        toggleVisibility(false);
    }

    private void toggleVisibility(boolean flagVisible){
        TextView txP = (TextView) findViewById(R.id.tvPhrase);
        if (!flagVisible)
            txP.setVisibility(View.INVISIBLE);
        else txP.setVisibility(View.VISIBLE);
        TextView txI = (TextView) findViewById(R.id.tvIMC);
        if (!flagVisible)
            txI.setVisibility(View.INVISIBLE);
        else txI.setVisibility(View.VISIBLE);
        TextView txD = (TextView) findViewById(R.id.tvDiag);
        if (!flagVisible)
            txD.setVisibility(View.INVISIBLE);
        else txD.setVisibility(View.VISIBLE);
    }
}
