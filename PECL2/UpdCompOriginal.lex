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
bitMask = [0-1]+(x+(yz*)*)*

marca = (ident|date|name|use|bitSize|insBitCode)
aEtiqueta = "<"{marca}">"
cEtiqueta = "</"{marca}">"

%unicode
%integer
%line
%state comment
%class UpdComp

%cup

%%

<YYINITIAL> "<comment>" { yybegin(comment);}
<comment> . {/*ignorar*/}
<comment> "</comment>" {  yybegin(YYINITIAL);}


<YYINITIAL> "<upd>" { return new Symbol(sym.AUPD);}
<YYINITIAL> "</upd>" { return new Symbol(sym.CUPD);}
<YYINITIAL> "<descr>" { return new Symbol(sym.ADESCR);}
<YYINITIAL> "</descr>" { return new Symbol(sym.CDESCR);}
<YYINITIAL> "<ident>" { return new Symbol(sym.AIDENT);}
<YYINITIAL> "</ident>" { return new Symbol(sym.CIDENT);}
<YYINITIAL> "<date>" { return new Symbol(sym.ADATE);}
<YYINITIAL> "</date>" { return new Symbol(sym.CDATE);}
<YYINITIAL> "<storage>" { return new Symbol(sym.ASTORAGE);}
<YYINITIAL> "</storage>" { return new Symbol(sym.CSTORAGE);}
<YYINITIAL> "<register>" { return new Symbol(sym.AREGISTER);}
<YYINITIAL> "</register>" { return new Symbol(sym.CREGISTER);}
<YYINITIAL> "<name>" { return new Symbol(sym.ANAME);}
<YYINITIAL> "</name>" { return new Symbol(sym.CNAME);}
<YYINITIAL> "<use>" { return new Symbol(sym.AUSE);}
<YYINITIAL> "</use>" { return new Symbol(sym.CUSE);}
<YYINITIAL> "<bitSize>" { return new Symbol(sym.ABITSIZE);}
<YYINITIAL> "</bitSize>" { return new Symbol(sym.CBITSIZE);}
<YYINITIAL> "<regbitcode>" { return new Symbol(sym.AREGBITCODE);}
<YYINITIAL> "</regbitcode>" { return new Symbol(sym.CREGBITCODE);}
<YYINITIAL> "<group>" { return new Symbol(sym.AGROUP);}
<YYINITIAL> "</group>" { return new Symbol(sym.CGROUP);}
<YYINITIAL> "<registers>" { return new Symbol(sym.AREGISTERS);}
<YYINITIAL> "</registers>" { return new Symbol(sym.CREGISTERS);}
<YYINITIAL> "<insns>" { return new Symbol(sym.AINSNS);}
<YYINITIAL> "</insns>" { return new Symbol(sym.CINSNS);}
<YYINITIAL> "<ins>" { return new Symbol(sym.AINS);}
<YYINITIAL> "</ins>" { return new Symbol(sym.CINS);}
<YYINITIAL> "<opcode>" { return new Symbol(sym.AOPCODE);}
<YYINITIAL> "</opcode>" { return new Symbol(sym.COPCODE);}
<YYINITIAL> "<in>" { return new Symbol(sym.AIN);}
<YYINITIAL> "</in>" { return new Symbol(sym.CIN);}
<YYINITIAL> "<out>" { return new Symbol(sym.AOUT);}
<YYINITIAL> "</out>" { return new Symbol(sym.COUT);}
<YYINITIAL> "<insbitcode>" { return new Symbol(sym.AINSBITCODE);}
<YYINITIAL> "</insbitcode>" { return new Symbol(sym.CINSBITCODE);}
<YYINITIAL> "<behav>" { return new Symbol(sym.ABEHAV);}
<YYINITIAL> "</behav>" { return new Symbol(sym.CBEHAV);}


<YYINITIAL> {ident} { upd.setIdent(yytext()); 
return new Symbol(sym.IDENT); }

<YYINITIAL> {date}  { upd.setFecha(yytext()); 
return new Symbol(sym.DATE);}
<YYINITIAL> {name}  {  if(!upd.putReg(yytext(),yytext())){
                      System.out.println("WARNING: Registro repetido: "+yytext());
                    }else{
                      upd.incRegs();
                    }
                    campo = null;
                    return new Symbol(sym.NAME);
                }
<YYINITIAL> \"AllPurpose\"  { upd.incAllPurpose(); return new Symbol(symbol.ALLPURPOSE); }
<YYINITIAL> \"Accumulator\"  { upd.incAccumulator(); }
<YYINITIAL> \"ProgramPC\"   { upd.incProgramPC(); }
<YYINITIAL> \"Index\"       { upd.incIndex(); }
<YYINITIAL> \"FlagVector\"  { upd.incFlagVector();  }
<YYINITIAL> \"StackPointer\"  { upd.incStackPointer();  }

<YYINITIAL> [1-9][0-9]*     { upd.addBits(Integer.parseInt(yytext())); }

<YYINITIAL> {bitMask}       { /*no tenemos nada que hacer*/ }

{white}         { /*ignorar*/ }
.               { printError("token "+yytext()+" no reconocido"); upd.incScanErrors();}