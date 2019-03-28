package com.example.android.prueba1;

public class Escaneo {

    private int ID_INVENTARIOMES;
    private String ID_USUARIOMES;
    private String FECHAMES;
    private String HORAMES;
    private int RACKMES;
    private int FILAMES;
    private int COLUMNAMES;
    private String ID_PRODUCTOMES;
    private String DESCRIPCION_PRODUCTOMES;
    private String ID_ENVASEMES;
    private String ENVASEMES;
    private String LOTEMES;
    private String CAPACIDADMES;
    private String CONSECUTIVOMES;
    private int CANTIDADMES;

    public Escaneo() {
    }

    public Escaneo(int ID_INVENTARIOMES, String ID_USUARIOMES, String FECHAMES, String HORAMES, int RACKMES, int FILAMES, int COLUMNAMES, String ID_PRODUCTOMES, String DESCRIPCION_PRODUCTOMES, String ID_ENVASEMES, String ENVASEMES, String LOTEMES, String CAPACIDADMES, String CONSECUTIVOMES, int CANTIDADMES) {
        this.ID_INVENTARIOMES = ID_INVENTARIOMES;
        this.ID_USUARIOMES = ID_USUARIOMES;
        this.FECHAMES = FECHAMES;
        this.HORAMES = HORAMES;
        this.RACKMES = RACKMES;
        this.FILAMES = FILAMES;
        this.COLUMNAMES = COLUMNAMES;
        this.ID_PRODUCTOMES = ID_PRODUCTOMES;
        this.DESCRIPCION_PRODUCTOMES = DESCRIPCION_PRODUCTOMES;
        this.ID_ENVASEMES = ID_ENVASEMES;
        this.ENVASEMES = ENVASEMES;
        this.LOTEMES = LOTEMES;
        this.CAPACIDADMES = CAPACIDADMES;
        this.CONSECUTIVOMES = CONSECUTIVOMES;
        this.CANTIDADMES = CANTIDADMES;
    }

    public int getID_INVENTARIOMES() {
        return ID_INVENTARIOMES;
    }

    public void setID_INVENTARIOMES(int ID_INVENTARIOMES) {
        this.ID_INVENTARIOMES = ID_INVENTARIOMES;
    }

    public String getID_USUARIOMES() {
        return ID_USUARIOMES;
    }

    public void setID_USUARIOMES(String ID_USUARIOMES) {
        this.ID_USUARIOMES = ID_USUARIOMES;
    }

    public String getFECHAMES() {
        return FECHAMES;
    }

    public void setFECHAMES(String FECHAMES) {
        this.FECHAMES = FECHAMES;
    }

    public String getHORAMES() {
        return HORAMES;
    }

    public void setHORAMES(String HORAMES) {
        this.HORAMES = HORAMES;
    }

    public int getRACKMES() {
        return RACKMES;
    }

    public void setRACKMES(int RACKMES) {
        this.RACKMES = RACKMES;
    }

    public int getFILAMES() {
        return FILAMES;
    }

    public void setFILAMES(int FILAMES) {
        this.FILAMES = FILAMES;
    }

    public int getCOLUMNAMES() {
        return COLUMNAMES;
    }

    public void setCOLUMNAMES(int COLUMNAMES) {
        this.COLUMNAMES = COLUMNAMES;
    }

    public String getID_PRODUCTOMES() {
        return ID_PRODUCTOMES;
    }

    public void setID_PRODUCTOMES(String ID_PRODUCTOMES) {
        this.ID_PRODUCTOMES = ID_PRODUCTOMES;
    }

    public String getDESCRIPCION_PRODUCTOMES() {
        return DESCRIPCION_PRODUCTOMES;
    }

    public void setDESCRIPCION_PRODUCTOMES(String DESCRIPCION_PRODUCTOMES) {
        this.DESCRIPCION_PRODUCTOMES = DESCRIPCION_PRODUCTOMES;
    }

    public String getID_ENVASEMES() {
        return ID_ENVASEMES;
    }

    public void setID_ENVASEMES(String ID_ENVASEMES) {
        this.ID_ENVASEMES = ID_ENVASEMES;
    }

    public String getENVASEMES() {
        return ENVASEMES;
    }

    public void setENVASEMES(String ENVASEMES) {
        this.ENVASEMES = ENVASEMES;
    }

    public String getLOTEMES() {
        return LOTEMES;
    }

    public void setLOTEMES(String LOTEMES) {
        this.LOTEMES = LOTEMES;
    }

    public String getCAPACIDADMES() {
        return CAPACIDADMES;
    }

    public void setCAPACIDADMES(String CAPACIDADMES) {
        this.CAPACIDADMES = CAPACIDADMES;
    }

    public String getCONSECUTIVOMES() {
        return CONSECUTIVOMES;
    }

    public void setCONSECUTIVOMES(String CONSECUTIVOMES) {
        this.CONSECUTIVOMES = CONSECUTIVOMES;
    }

    public int getCANTIDADMES() {
        return CANTIDADMES;
    }

    public void setCANTIDADMES(int CANTIDADMES) {
        this.CANTIDADMES = CANTIDADMES;
    }

    @Override
    public String toString() {
        return ID_PRODUCTOMES;
    }
}
