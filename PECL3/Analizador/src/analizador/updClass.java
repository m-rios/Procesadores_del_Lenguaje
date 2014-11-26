/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador;

import java.util.Hashtable;

/**
 *
 * @author Mario
 */
public class updClass {

    private String Ident;
    private String Fecha;
    private int numRegs;
    private int numAllPurpose;
    private int numAccumulator;
    private int numProgramPC;
    private int numIndex;
    private int numFlagVector;
    private int numStackPointer;
    private int numBits;
    private Hashtable Regs = new Hashtable();
    private int ErrorsScan;

    //setters

    public void setIdent(String s) {
        Ident = s;
    }

    public void setFecha(String s) {
        Fecha = s;
    }

    //getters

    public int getNumErrorsScan() {
        return ErrorsScan;
    }

    //metodos add

    public void incRegs() {
        numRegs += 1;
    }

    public void incAllPurpose() {
        numAllPurpose += 1;
    }

    public void incAccumulator() {
        numAccumulator += 1;
    }

    public void incProgramPC() {
        numProgramPC += 1;
    }

    public void incIndex() {
        numIndex += 1;
    }

    public void incFlagVector() {
        numFlagVector += 1;
    }

    public void incStackPointer() {
        numStackPointer += 1;
    }

    public void incScanErrors() {
        ErrorsScan += 1;
    }

    public void addBits(int n) {
        numBits += n;
    }

    public Boolean putReg(String k, storeClass v) {
        if (Regs.containsKey(k)) {
            return false;
        } else {
            Regs.put(k, v);
            return true;
        }
    }
//toString
    @Override
    public String toString() {
        return "Identidad del procesador .....: " + Ident
                + "\n  Creado con fecha ...........: " + Fecha
                + "\n  Capacidad de los Reg. (bits): " + numBits
                + "\n  Numero de registros ........: " + numRegs
                + "\n\tLista de Registros ...: "
                + Regs.keySet().toString()
                + "\n\tAllPurpose ...: " + numAllPurpose
                + "\n\tAccumulators .: " + numAccumulator
                + "\n\tProgram PCs ..: " + numProgramPC
                + "\n\tIndex ........: " + numIndex
                + "\n\tVector Flags .: " + numFlagVector
                + "\n\tStack pointers: " + numStackPointer;
    }
}
