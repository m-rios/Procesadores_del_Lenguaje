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
ident = {letra}[^\n\r\t \""<'[]"]*
date = {dig}{dig}"/"{dig}{dig}"/"{dig}{dig}{dig}{dig}
name = "'"{letra}{letra}?"'"
bitMask = [0-1]+(x+(yz*)*)*
use = \"(AllPurpose|Accumulator|ProgramPC|Index|FlagVector|StackPointer)\"
regbitcode = [0-1]+b
marca = (ident|date|name|use|bitSize|insBitCode)
aEtiqueta = "<"{marca}">"
cEtiqueta = "</"{marca}">"
opcode = [A-Z]+
in = \[(DATA|MEM|{letra}{letra}?)","*(DATA|MEM|{letra}{letra}?)\]
out = \[{letra}{letra}?\]
behav = "["[^\n]"]"

%unicode
%line
%state comment
%class UpdComp

%cup

%%

<YYINITIAL> "<comment>" 	{ yybegin(comment);}
<comment> . {/*ignorar*/}
<comment> "</comment>" 		{ yybegin(YYINITIAL);}

<YYINITIAL> "<descr>" 		{ return new Symbol(sym.ADESCR, yyline, yyline);}
<YYINITIAL> "</descr>" 		{ return new Symbol(sym.CDESCR, yyline, yyline);}
<YYINITIAL> "<ident>" 		{ return new Symbol(sym.AIDENT, yyline, yyline);}
<YYINITIAL> "</ident>" 		{ return new Symbol(sym.CIDENT, yyline, yyline);}
<YYINITIAL> "<date>" 		{ return new Symbol(sym.ADATE, yyline, yyline);}
<YYINITIAL> "</date>" 		{ return new Symbol(sym.CDATE, yyline, yyline);}

<YYINITIAL> {ident} 		{ return new Symbol(sym.TEXT,yyline);}
<YYINITIAL> {date}  		{ return new Symbol(sym.DATE, yyline, yyline);}

<YYINITIAL> "<storage>"		{ return new Symbol(sym.ASTORAGE, yyline, yyline);}
<YYINITIAL> "</storage>"	{ return new Symbol(sym.CSTORAGE, yyline, yyline);}
<YYINITIAL>	"<register>"	{ return new Symbol(sym.AREGISTER, yyline, yyline);}
<YYINITIAL>	"</register>"	{ return new Symbol(sym.CREGISTER, yyline, yyline);}
<YYINITIAL>	"<name>"		{ return new Symbol(sym.ANAME, yyline, yyline);}
<YYINITIAL> {name}			{ return new Symbol(sym.NAME, yyline, yyline);}
<YYINITIAL> "</name>"		{ return new Symbol(sym.CNAME, yyline, yyline);}
<YYINITIAL> "<use>"			{ return new Symbol(sym.AUSE, yyline, yyline);}
<YYINITIAL> {use}			{ return new Symbol(sym.USE, yyline, yyline);}
<YYINITIAL> "</use>"		{ return new Symbol(sym.CUSE, yyline, yyline);}
<YYINITIAL> "<bitSize>"		{ return new Symbol(sym.ABITSIZE, yyline, yyline);}
<YYINITIAL> [1-9][0-9]*		{ return new Symbol(sym.BITSIZE, yyline, yyline);}
<YYINITIAL> "</bitSize>"	{ return new Symbol(sym.CBITSIZE, yyline, yyline);}
<YYINITIAL> "<regbitcode>"	{ return new Symbol(sym.AREGBITCODE, yyline, yyline);}
<YYINITIAL> {regbitcode}	{ return new Symbol(sym.REGBITCODE, yyline, yyline);}
<YYINITIAL>	"</regbitcode>"	{ return new Symbol(sym.CREGBITCODE, yyline, yyline);}
<YYINITIAL> "<group>"		{ return new Symbol(sym.AGROUP, yyline, yyline);}
<YYINITIAL> "<registers>"	{ return new Symbol(sym.AREGISTERS, yyline, yyline);}
<YYINITIAL> "</registers>"	{ return new Symbol(sym.CREGISTERS, yyline, yyline);}
<YYINITIAL> "</group>"		{ return new Symbol(sym.CGROUP, yyline, yyline);}
<YYINITIAL> "<upd>"			{ return new Symbol(sym.AUPD, yyline, yyline);}
<YYINITIAL> "</upd>"		{ return new Symbol(sym.CUPD, yyline, yyline);}
<YYINITIAL> ","				{ return new Symbol(sym.COMA, yyline, yyline);}




{white}         { /*ignorar*/ }
.               { printError("caracter "+yytext()+" no reconocido"); }