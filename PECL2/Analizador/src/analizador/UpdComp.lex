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
%char
%state comment
%class scanner

%cup

%%

<YYINITIAL> "<comment>" 	{ yybegin(comment);}
<comment> . {/*ignorar*/}
<comment> "</comment>" 		{ yybegin(YYINITIAL);}

<YYINITIAL> "<descr>" 		{ return new Symbol(sym.ADESCR, yyline, yychar);}
<YYINITIAL> "</descr>" 		{ return new Symbol(sym.CDESCR, yyline, yychar);}
<YYINITIAL> "<ident>" 		{ return new Symbol(sym.AIDENT, yyline, yychar);}
<YYINITIAL> "</ident>" 		{ return new Symbol(sym.CIDENT, yyline, yychar);}
<YYINITIAL> "<date>" 		{ return new Symbol(sym.ADATE, yyline, yychar);}
<YYINITIAL> "</date>" 		{ return new Symbol(sym.CDATE, yyline, yychar);}

<YYINITIAL> {ident} 		{ return new Symbol(sym.TEXT,yyline, yychar);}
<YYINITIAL> {date}  		{ return new Symbol(sym.DATE, yyline, yychar);}

<YYINITIAL> "<storage>"		{ return new Symbol(sym.ASTORAGE, yyline, yychar);}
<YYINITIAL> "</storage>"	{ return new Symbol(sym.CSTORAGE, yyline, yychar);}
<YYINITIAL>	"<register>"	{ return new Symbol(sym.AREGISTER, yyline, yychar);}
<YYINITIAL>	"</register>"	{ return new Symbol(sym.CREGISTER, yyline, yychar);}
<YYINITIAL>	"<name>"		{ return new Symbol(sym.ANAME, yyline, yychar);}
<YYINITIAL> {name}			{ return new Symbol(sym.NAME, yyline, yychar);}
<YYINITIAL> "</name>"		{ return new Symbol(sym.CNAME, yyline, yychar);}
<YYINITIAL> "<use>"			{ return new Symbol(sym.AUSE, yyline, yychar);}
<YYINITIAL> {use}			{ return new Symbol(sym.USE, yyline, yychar);}
<YYINITIAL> "</use>"		{ return new Symbol(sym.CUSE, yyline, yychar);}
<YYINITIAL> "<bitSize>"		{ return new Symbol(sym.ABITSIZE, yyline, yychar);}
<YYINITIAL> [1-9][0-9]*		{ return new Symbol(sym.BITSIZE, yyline, yychar);}
<YYINITIAL> "</bitSize>"	{ return new Symbol(sym.CBITSIZE, yyline, yychar);}
<YYINITIAL> "<regbitcode>"	{ return new Symbol(sym.AREGBITCODE, yyline, yychar);}
<YYINITIAL> {regbitcode}	{ return new Symbol(sym.REGBITCODE, yyline, yychar);}
<YYINITIAL>	"</regbitcode>"	{ return new Symbol(sym.CREGBITCODE, yyline, yychar);}
<YYINITIAL> "<group>"		{ return new Symbol(sym.AGROUP, yyline, yychar);}
<YYINITIAL> "<registers>"	{ return new Symbol(sym.AREGISTERS, yyline, yychar);}
<YYINITIAL> "</registers>"	{ return new Symbol(sym.CREGISTERS, yyline, yychar);}
<YYINITIAL> "</group>"		{ return new Symbol(sym.CGROUP, yyline, yychar);}
<YYINITIAL> "<upd>"			{ return new Symbol(sym.AUPD, yyline, yychar);}
<YYINITIAL> "</upd>"		{ return new Symbol(sym.CUPD, yyline, yychar);}
<YYINITIAL> ","				{ return new Symbol(sym.COMA, yyline, yychar);}




{white}         { /*ignorar*/ }
.               { printError("caracter "+yytext()+" no reconocido"); }