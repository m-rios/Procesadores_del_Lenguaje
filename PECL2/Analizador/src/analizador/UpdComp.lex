package analizador;

import java_cup.runtime.Symbol;

%%

%{
    public void printError(String msg){
        System.out.println("error en linea "+(int)(yyline+1)+": "+msg);
    }
%}

white = [\r\t\n" "]
letra = [a-zA-Z]
dig = [0-9]
text = {letra}[^\n\r\t \""<'[],"]*
date = {dig}{dig}"/"{dig}{dig}"/"{dig}{dig}{dig}{dig}
name = "'"{letra}{letra}?"'"
bitMask = [0-1]+(x+(yz*)*)*
use = \"(AllPurpose|Accumulator|ProgramPC|Index|FlagVector|StackPointer)\"
regbitcode = [0-1]+b
marca = (ident|date|name|use|bitSize|insBitCode)
aEtiqueta = "<"{marca}">"
cEtiqueta = "</"{marca}">"
opcode = [A-Z]+
in = {letra}{letra}?|DATA|MEM

%unicode
%line
%state comment
%class UpdComp

%cup

%%

<YYINITIAL> "<comment>" 	{ yybegin(comment);}
<comment> . {/*ignorar*/}
<comment> "</comment>" 		{ yybegin(YYINITIAL);}

<YYINITIAL> {in}			{ return new Symbol(sym.IN, yyline, yyline, yytext());}
<YYINITIAL> "<descr>" 		{ return new Symbol(sym.ADESCR, yyline, yyline, yytext());}
<YYINITIAL> "</descr>" 		{ return new Symbol(sym.CDESCR, yyline, yyline, yytext());}
<YYINITIAL> "<ident>" 		{ return new Symbol(sym.AIDENT, yyline, yyline, yytext());}
<YYINITIAL> "</ident>" 		{ return new Symbol(sym.CIDENT, yyline, yyline, yytext());}
<YYINITIAL> "<date>" 		{ return new Symbol(sym.ADATE, yyline, yyline, yytext());}
<YYINITIAL> "</date>" 		{ return new Symbol(sym.CDATE, yyline, yyline, yytext());}

<YYINITIAL> {text} 		{ return new Symbol(sym.TEXT,yyline, yyline, yytext());}
<YYINITIAL> {date}  		{ return new Symbol(sym.DATE, yyline, yyline, yytext());}

<YYINITIAL> "<storage>"		{ return new Symbol(sym.ASTORAGE, yyline, yyline, yytext());}
<YYINITIAL> "</storage>"	{ return new Symbol(sym.CSTORAGE, yyline, yyline, yytext());}
<YYINITIAL>	"<register>"	{ return new Symbol(sym.AREGISTER, yyline, yyline, yytext());}
<YYINITIAL>	"</register>"	{ return new Symbol(sym.CREGISTER, yyline, yyline, yytext());}
<YYINITIAL>	"<name>"		{ return new Symbol(sym.ANAME, yyline, yyline, yytext());}
<YYINITIAL> {name}			{ return new Symbol(sym.NAME, yyline, yyline, yytext());}
<YYINITIAL> "</name>"		{ return new Symbol(sym.CNAME, yyline, yyline, yytext());}
<YYINITIAL> "<use>"			{ return new Symbol(sym.AUSE, yyline, yyline, yytext());}
<YYINITIAL> {use}			{ return new Symbol(sym.USE, yyline, yyline, yytext());}
<YYINITIAL> "</use>"		{ return new Symbol(sym.CUSE, yyline, yyline, yytext());}
<YYINITIAL> "<bitSize>"		{ return new Symbol(sym.ABITSIZE, yyline, yyline, yytext());}
<YYINITIAL> [1-9][0-9]*		{ return new Symbol(sym.BITSIZE, yyline, yyline, yytext());}
<YYINITIAL> "</bitSize>"	{ return new Symbol(sym.CBITSIZE, yyline, yyline, yytext());}
<YYINITIAL> "<regbitcode>"	{ return new Symbol(sym.AREGBITCODE, yyline, yyline, yytext());}
<YYINITIAL> {regbitcode}	{ return new Symbol(sym.REGBITCODE, yyline, yyline, yytext());}
<YYINITIAL>	"</regbitcode>"	{ return new Symbol(sym.CREGBITCODE, yyline, yyline, yytext());}
<YYINITIAL> "<group>"		{ return new Symbol(sym.AGROUP, yyline, yyline, yytext());}
<YYINITIAL> "<registers>"	{ return new Symbol(sym.AREGISTERS, yyline, yyline, yytext());}
<YYINITIAL> "</registers>"	{ return new Symbol(sym.CREGISTERS, yyline, yyline, yytext());}
<YYINITIAL> "</group>"		{ return new Symbol(sym.CGROUP, yyline, yyline, yytext());}
<YYINITIAL> "<upd>"			{ return new Symbol(sym.AUPD, yyline, yyline, yytext());}
<YYINITIAL> "</upd>"		{ return new Symbol(sym.CUPD, yyline, yyline, yytext());}
<YYINITIAL> ","				{ return new Symbol(sym.COMA, yyline, yyline, yytext());}

<YYINITIAL> "<insns>"		{ return new Symbol(sym.AINSNS, yyline, yyline, yytext());}
<YYINITIAL> "</insns>"		{ return new Symbol(sym.CINSNS, yyline, yyline, yytext());}
<YYINITIAL> "<opcode>"		{ return new Symbol(sym.AOPCODE, yyline, yyline, yytext());}
<YYINITIAL> {opcode}		{ return new Symbol(sym.OPCODE, yyline, yyline, yytext());}
<YYINITIAL> "</opcode>"		{ return new Symbol(sym.COPCODE, yyline, yyline, yytext());}
<YYINITIAL> "<insbitcode>"	{ return new Symbol(sym.AINSBITCODE, yyline, yyline, yytext());}
<YYINITIAL> "</insbitcode>"	{ return new Symbol(sym.CINSBITCODE, yyline, yyline, yytext());}
<YYINITIAL> {bitMask}		{ return new Symbol(sym.INSBITCODE, yyline, yyline, yytext());}
<YYINITIAL> "<behav>"		{ return new Symbol(sym.ABEHAV, yyline, yyline, yytext());}
<YYINITIAL> "</behav>"		{ return new Symbol(sym.CBEHAV, yyline, yyline, yytext());}
<YYINITIAL> "<in>"			{ return new Symbol(sym.AIN, yyline, yyline, yytext());}
<YYINITIAL> "</in>"			{ return new Symbol(sym.CIN, yyline, yyline, yytext());}
<YYINITIAL> "<out>"			{ return new Symbol(sym.AOUT, yyline, yyline, yytext());}
<YYINITIAL> "</out>"		{ return new Symbol(sym.COUT, yyline, yyline, yytext());}
<YYINITIAL> "["				{ return new Symbol(sym.ACORCHETE, yyline, yyline, yytext());}
<YYINITIAL> "]"				{ return new Symbol(sym.CCORCHETE, yyline, yyline, yytext());}


{white}         { /*ignorar*/ }
.               { printError("caracter "+yytext()+" no reconocido"); }