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
        UpdComp yy;
        if (argv.length != 1) {   
            System.out.println("Usage: updcomp [options] [path]");
            return;
        }
        if (argv[0].equals("-i")) {
            yy = new UpdComp(System.in);
        } else {
            String path_to_tokens[] = argv[0].split("\\.");
            if (path_to_tokens[path_to_tokens.length-1].equals("upd")) {
                yy = new UpdComp(new FileInputStream(argv[0]));
            } else{
              System.out.println("extension de archivo no reconocida");
              return;
            }
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
System.out.println("Errores totales: "+upd.getNumErrorsScan());
%eof}

white = [\r\t\n" "]
letra = [a-zA-Z]
dig = [0-9]
ident = {letra}[^\n\r\t \"<']*
date = {dig}{dig}"/"{dig}{dig}"/"{dig}{dig}{dig}{dig}
name = "'"{letra}{letra}?"'"
bitMask = [0-1]+(x*|x+y*|x+y+z*)

marca = (ident|date|name|use|bitSize|insBitCode)
aEtiqueta = "<"{marca}">"
cEtiqueta = "</"{marca}">"

%unicode
%integer
%line
%state comment
%class UpdComp

%%

<YYINITIAL> "<comment>" { yybegin(comment);}
<comment> . {/*ignorar*/}
<comment> "</comment>" {  yybegin(YYINITIAL);}

<YYINITIAL> {aEtiqueta} {/*se acepta, no se hace nada*/}
<YYINITIAL> {cEtiqueta} {/*se acepta, no se hace nada*/}

<YYINITIAL> {ident} { upd.setIdent(yytext());  }

<YYINITIAL> {date}  { upd.setFecha(yytext()); }
<YYINITIAL> {name}  {  if(!upd.putReg(yytext(),yytext())){
                      System.out.println("WARNING: Registro repetido: "+yytext());
                    }else{
                      upd.incRegs();
                    }
                    campo = null;
                }
<YYINITIAL> \"AllPurpose\"  { upd.incAllPurpose();  }
<YYINITIAL> \"Accumulator\"  { upd.incAccumulator(); }
<YYINITIAL> \"ProgramPC\"   { upd.incProgramPC(); }
<YYINITIAL> \"Index\"       { upd.incIndex(); }
<YYINITIAL> \"FlagVector\"  { upd.incFlagVector();  }
<YYINITIAL> \"StackPointer\"  { upd.incStackPointer();  }

<YYINITIAL> [1-9][0-9]*     { upd.addBits(Integer.parseInt(yytext())); }

<YYINITIAL> {bitMask}       { /*no tenemos nada que hacer*/ }

{white}         { /*ignorar*/ }
.               { printError("token "+yytext()+" no reconocido"); upd.incScanErrors();}