package com.example.deko.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private boolean flagPesoOK = false;
    private boolean flagAlturaOK = false;
    private String sDiag = "";
        @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
     }

    private String getsDiag(double dImc) {
        this.setsDiag(dImc);
        return this.sDiag;
    }

    private void setsDiag(double dImc) {
        if (dImc<17)
            this.sDiag = getString(R.string.diag_0);
        else if (dImc>=17 && dImc < 18.5)
            this.sDiag = getString(R.string.diag_1);
        else if (dImc>=18.5 && dImc < 25)
            this.sDiag = getString(R.string.diag_2);
        else if (dImc>=25 && dImc < 30)
            this.sDiag = getString(R.string.diag_3);
        else if (dImc>=30 && dImc < 35)
            this.sDiag = getString(R.string.diag_4);
        else if (dImc>=35 && dImc < 40)
            this.sDiag = getString(R.string.diag_5);
        else
            this.sDiag = getString(R.string.diag_6);
    }

    private boolean isFlagsOK(){
        return (flagAlturaOK && flagPesoOK);
    }

    private void setFlagAlturaOK(boolean flagAlturaOK) {
        this.flagAlturaOK = flagAlturaOK;
    }

    private void setFlagPesoOK(boolean flagPesoOK) {
        this.flagPesoOK = flagPesoOK;
    }

    private double getPeso(){
        double dPeso;
        EditText txtPeso = (EditText) findViewById(R.id.txtPeso);
        String sPeso = txtPeso.getText().toString();
        if (!sPeso.isEmpty()) {
            setFlagPesoOK(true);
            dPeso = Double.parseDouble(sPeso);
            return dPeso;
        }
        else setFlagPesoOK(false);

        return 0;
    }

    private double getAltura(){
        double dAltura;
        EditText txtAltura = (EditText) findViewById(R.id.txtAltura);
        String sAltura = txtAltura.getText().toString();
        if (!sAltura.isEmpty()){
            setFlagAlturaOK(true);
            dAltura = Double.parseDouble(sAltura);
            return dAltura;
        }
        else setFlagAlturaOK(false);
        return 0;
    }

    private void setPeso(String sPeso){
        EditText txtPeso = (EditText) findViewById(R.id.txtPeso);
        txtPeso.setText(sPeso);
    }

    private void setAltura(String sAltura){
        EditText txtAltura = (EditText) findViewById(R.id.txtAltura);
        txtAltura.setText(sAltura);
    }

    private void setIMC(String sTimc){
        TextView tvTimc = (TextView) findViewById(R.id.tvIMC);
        tvTimc.setText(sTimc);
    }

    private void setDiag(String sDiag){
        //Sets TextView tvDiag
        TextView tvDiag = (TextView) findViewById(R.id.tvDiag);
        tvDiag.setText(sDiag);
    }

    public void limpaForm(View view){
        this.setAltura("");
        this.setPeso("");
        this.setIMC("");
        this.setDiag("");
        changeVisiblity(false);
    }

    public void prepIMC(View view) {
        double dPeso, dAltura,dIMC;
        dPeso = this.getPeso();
        dAltura = this.getAltura();
        if (isFlagsOK()){
            dIMC=this.calcIMC(dPeso, dAltura);
            this.setIMC(String.format(Locale.ENGLISH,"%.2f",dIMC));
            setDiag(getsDiag(dIMC));
            changeVisiblity(true);
        }

    }

    private double calcIMC(double dPeso, double dAltura){
        return (dPeso)/(dAltura*dAltura);
    }

    private void changeVisiblity (boolean flagVisible){
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
