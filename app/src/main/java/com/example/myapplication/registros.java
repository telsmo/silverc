package com.example.myapplication;

public class registros {
    private String uno;
    private String dos;
    private String tres;

    public registros(String UU,String DD, String TT){
        uno = UU;
        dos = DD;
        tres = TT;
    }

    public String getuno() {
        return uno;
    }

    public void setuno(String Uno) {
        uno = Uno;
    }

    public String getdos() {
        return dos;
    }

    public void setdos(String Dos) {
        dos = Dos;
    }

    public String gettres() {
        return tres;
    }

    public void settres(String Tres) {
        tres = Tres;
    }
}