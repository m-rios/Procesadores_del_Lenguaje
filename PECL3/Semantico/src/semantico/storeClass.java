/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semantico;

import java.util.Hashtable;
import java.util.Stack;

/**
 *
 * @author Mario
 */
public class storeClass {

    private String regName;
    private boolean group;
    private Integer bitSize;
    private String bitCode;
    private Hashtable registros_size;
// in java.util.Stack and
    public Stack use; // represents a last-in-first-out (LIFO) stack
    public Stack registers; //registers in a group

    storeClass(String r, Stack u, Integer i) {
        regName = r;
        bitSize = i;
        use = u;
    }

    storeClass(String r, Stack s, Integer i, String bc) {
        this(r, s, i);
        bitCode = bc;
    }

    storeClass(String r, Integer i, String bc, Stack s, Stack n) {
        this(r, s, i, bc);
        registers = n;
    }

    public void setRegistrosGlobal(Hashtable t) {
        registros_size = t;
    }

    public boolean isCorrect() {
        int size = 0;
        while (!registers.isEmpty()) {
            String r = (String) registers.pop();
            if (registros_size.containsKey(r)) {
                size += (int)registros_size.get(r);
            }
        }
        return size >= this.bitSize;
    }

    public void setRegName(String n) {
        regName = new String(n);
    }

    public void setGroup() {
        group = true;
    }

    public void setRegister() {
        group = false;
    }

    public void setBitSize(Integer i) {
        bitSize = new Integer(i);
    }

    public void setBitCode(String b) {
        bitCode = new String(b);
    }

    public void setRegistros(Stack r){
        registers = r;
    }

    public String getRegName() {
        return regName;
    }

    public int getSize() {
        return bitSize;
    }

    public Stack getUse() {
        return use;
    }

    public boolean isGroup() {
        return group;
    }

    public String toString() {
        String s = "Register: " + regName
                + "\n\tUse: " + use.toString()
                + "\n\tBitsize: " + bitSize
                + "\n\tBinary code: " + bitCode;
        if (group) {
            s = s + "\n\tRegisters grouped: "
                    + registers.toString();
        }
        s = s + "\n";
        return s;
    }
}
