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
%}

%eof{
System.out.println("------------------Report--------------------");
System.out.println(upd.toString());
%eof}

white = [\r\t\n" "]
letra = [a-zA-Z]
dig = [0-9]
ident = {letra}[^\r\t \n<]*
date = {dig}{dig}"/"{dig}{dig}"/"{dig}{dig}{dig}{dig}
name = "'"{letra}{letra}"'"
bitMask = [10]+(x+(yz*)*)*


%unicode
%integer
%line
%state ident, date, name, use, bitSize, insBitCode, comment, end, error

%%

<YYINITIAL> "<comment>" {yybegin(comment);}
<comment> (.|{white}) {}
<comment> "</comment>" {yybegin(YYINITIAL);System.out.println("fin comentario "+(int)(yyline+1));} 

<YYINITIAL> "<ident>" {yybegin(ident);}
<ident,end> "</ident>" {yybegin(YYINITIAL);}
<ident> {ident} {upd.setIdent(yytext());yybegin(end);}
<ident> . {System.out.println("error in line "+(int)(yyline+1)+": identifier expected");
           yybegin(error);}

<YYINITIAL> "<date>" {yybegin(date);}
<date> {date} {upd.setFecha(yytext());yybegin(end);}
<end> "</date>" {yybegin(YYINITIAL);}

<YYINITIAL> "<name>" {yybegin(name);}
<name> {name} {if(!upd.putReg(yytext(),"null")){
                  System.out.println("WARNING: Registro repetido: "+yytext());
              }else{
                upd.incRegs();
              }}
<name> "</name>" {yybegin(YYINITIAL);}

<YYINITIAL> "<use>" {yybegin(use);}
<use> \u0022AllPurpose\u0022 {upd.incAllPurpose();}
<use> \u0022Accumulator\u0022 {upd.incAccumulator();}
<use> \u0022ProgramPC\u0022 {upd.incProgramPC();}
<use> \u0022Index\u0022 {upd.incIndex();}
<use> \u0022FlagVector\u0022 {upd.incFlagVector();}
<use> \u0022StackPointer\u0022 {upd.incStackPointer();}
<use> "</use>" {yybegin(YYINITIAL);}

<YYINITIAL> "<bitSize>" {yybegin(bitSize);}
<bitSize> {dig}+ {upd.addBits(Integer.parseInt(yytext()));}
<bitSize> "</bitSize>" {yybegin(YYINITIAL);}

<YYINITIAL> "<insBitCode>" {yybegin(bitSize);}
<bitSize> {bitMask} {/*??*/}
<bitSize> "</bitSize>" {yybegin(YYINITIAL);}

<error> (.|[^\n\r])*"</".*">" {yybegin(YYINITIAL);}
<end> ([^\n\r]) {System.out.println("End of tag expected at line: "+(int)(yyline+1)); yybegin(error);}

{white} {}
. {System.out.println("unknown error at line: "+(int)(yyline+1));}
