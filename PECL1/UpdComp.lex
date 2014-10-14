import java.io.*;
import java.util.Hashtable;

/**
 * clase updClass
 */

class updClass {
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
  private Hashtable Regs = new Hashtable(); private int ErrorsScan;
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
 public Boolean putReg(String k, String v){
   if (Regs.containsKey(k)) {
     return false;
   } else {
    Regs.put(k,v);
    return true;
  }
}
//toString
public String toString() {
  return "Identidad del procesador .....: " + Ident +
  "\n  Creado con fecha ...........: " + Fecha +
  "\n  Capacidad de los Reg. (bits): " + numBits+
  "\n  Numero de registros ........: " + numRegs +
  "\n\tLista de Registros ...: " +
  Regs.keySet().toString()+
  "\n\tAllPurpose ...: " + numAllPurpose +
  "\n\tAccumulators .: " + numAccumulator +
  "\n\tProgram PCs ..: " + numProgramPC +
  "\n\tIndex ........: " + numIndex +
  "\n\tVector Flags .: " + numFlagVector +
  "\n\tStack pointers: " + numStackPointer;
} 
}

%%

%{

    private updClass upd = new updClass();
    private String campo;

    public static void main (String argv[]) throws java.io.IOException {
        Yylex yy;
        if (argv.length != 1) {   
            System.out.println("Usage: updcomp [options] [path]");
            return;
        }
        if (argv[0].equals("-i")) {
            yy = new Yylex(System.in);
        } else {
            yy = new Yylex(new FileInputStream(argv[0]));
        }
        while (yy.yylex() != -1) ;
    } 

    public void printError(String msg){
        System.out.println("error en linea "+(int)(yyline+1)+": "+msg);
    }
%}

%eof{
System.out.println("------------------Report--------------------");
System.out.println(upd.toString());
%eof}

white = [\r\t\n" "]
letra = [a-zA-Z]
dig = [0-9]
ident = {letra}({letra}|{dig})*
date = {dig}{dig}"/"{dig}{dig}"/"{dig}{dig}{dig}{dig}
name = "'"{letra}{letra}"'"
bitMask = [0-1]+(x+(yz*)*)*
use = ("AllPurpose"|"Accumulator"|"ProgramPc"|"Index"|"FlagVector"|"StackPointer")


%unicode
%integer
%line
%char
%state ident, date, name, use, bitSize, insBitCode, comment, endident, error

%%

<YYINITIAL> "<comment>" { yybegin(comment);  }
<comment> . {/*ignorar*/}
<comment> "</comment>" { yybegin(YYINITIAL);  } 


<YYINITIAL> "<ident>" { yybegin(ident);  }
<ident> {ident} { campo = yytext();  } 
<ident> "</ident>" {  upd.setIdent(campo);
                      System.out.println("campo: "+campo);
                      campo=null;
                      yybegin(YYINITIAL);}
<ident> . { printError("identificador no valido");
            upd.incScanErrors();
            yybegin(error); }


<YYINITIAL>"<date>" { yybegin(date);  }
<date>{date} {  campo = yytext(); }
<date>"</date>" { upd.setFecha(campo);
                  campo = null;
                  yybegin(YYINITIAL);}
<date> . {  printError("fecha no valida");
            upd.incScanErrors();
            yybegin(error);  }


<YYINITIAL> "<name>" {yybegin(name);}
<name> {name} { campo = yytext(); }
<name> "</name>" {  if(!upd.putReg(campo,campo)){
                    System.out.println("WARNING: Registro repetido: "+campo);
                    }else{
                    upd.incRegs();
                    }
                    campo = null;
                    yybegin(YYINITIAL);
                  }
<name> . {  printError("nombre no reconocido");
            upd.incScanErrors();
            yybegin(error); }


<YYINITIAL> "<use>" { yybegin(use); }
<use> {use} { campo = yytext(); }
<use> "</use>"  { if (campo.equals("\"AllPurpose\"")) {                    
                    upd.incAllPurpose();
                  }else if (campo.equals("\"Accumulator\"")) {
                    upd.incAccumulator();
                  }else if (campo.equals("\"ProgramPc\"")) {
                    upd.incProgramPC();
                  }else if (campo.equals("\"Index\"")) {
                    upd.incIndex();
                  }else if (campo.equals("\"FlagVector\"")) {
                    upd.incFlagVector();
                  }else if (campo.equals("\"StackPointer\"")) {
                    upd.incStackPointer();
                  }else{
                    yybegin(error);
                  }
                  yybegin(YYINITIAL);                   
                }
<use> . { printError("uso no reconocido");
          upd.incScanErrors();
          yybegin(error); 
        }


<YYINITIAL> "<bitSize>" { yybegin(bitSize); }
<bitSize>   {dig}+ {  campo=yytext(); }
<bitSize>   "</bitSize>"  { upd.addBits(Integer.parseInt(campo));
                            campo=null;
                            yybegin(YYINITIAL);
                          }
<bitSize>   . { printError("tamaño de bits no reconocido");
                upd.incScanErrors();
                yybegin(error);}                        


<YYINITIAL>   "<insBitCode>" {yybegin(insBitCode);}
<insBitCode>  {bitMask} {/*??*/}
<insBitCode>  "</insBitCode>" {yybegin(YYINITIAL);}
<insBitCode> .  { printError("Mascara de bits no reconocida");
                  upd.incScanErrors();
                  yybegin(error);}

<error> "</"{letra}*">"  {  yybegin(YYINITIAL);
                            System.out.println("recuperacion de error en linea: "+(int)(yyline+1));
                            System.out.println("------------------------------");  }
<error> .|[\n\r] {/*ignorar*/}


{white} {}
. {System.out.println("unknown error at line: "+(int)(yyline+1));}