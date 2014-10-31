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
ident = {letra}[^\n\r\t \"<']*
date = {dig}{dig}"/"{dig}{dig}"/"{dig}{dig}{dig}{dig}
name = "'"{letra}{letra}?"'"
bitMask = [0-1]+(x+(yz*)*)*
use = \"(AllPurpose|Accumulator|ProgramPC|Index|FlagVector|StackPointer)\"
regbitcode = [0-1]+b
marca = (ident|date|name|use|bitSize|insBitCode)
aEtiqueta = "<"{marca}">"
cEtiqueta = "</"{marca}">"

%unicode
%line
%state comment
%class scanner

%cup

%%

<YYINITIAL> "<comment>" 	{ yybegin(comment);}
<comment> . {/*ignorar*/}
<comment> "</comment>" 		{ yybegin(YYINITIAL);}

<YYINITIAL> "<descr>" 		{ return new Symbol(sym.ADESCR);}
<YYINITIAL> "</descr>" 		{ return new Symbol(sym.CDESCR);}
<YYINITIAL> "<ident>" 		{ return new Symbol(sym.AIDENT);}
<YYINITIAL> "</ident>" 		{ return new Symbol(sym.CIDENT);}
<YYINITIAL> "<date>" 		{ return new Symbol(sym.ADATE);}
<YYINITIAL> "</date>" 		{ return new Symbol(sym.CDATE);}

<YYINITIAL> {ident} 		{ return new Symbol(sym.IDENT);}
<YYINITIAL> {date}  		{ return new Symbol(sym.DATE);}

<YYINITIAL> "<storage>"		{ return new Symbol(sym.ASTORAGE);}
<YYINITIAL> "</storage>"	{ return new Symbol(sym.CSTORAGE);}
<YYINITIAL>	"<register>"	{ return new Symbol(sym.AREGISTER);}
<YYINITIAL>	"</register>"	{ return new Symbol(sym.CREGISTER);}
<YYINITIAL>	"<name>"		{ return new Symbol(sym.ANAME);}
<YYINITIAL> {name}			{ return new Symbol(sym.NAME);}
<YYINITIAL> "</name>"		{ return new Symbol(sym.CNAME);}
<YYINITIAL> "<use>"			{ return new Symbol(sym.AUSE);}
<YYINITIAL> {use}			{ return new Symbol(sym.USE);}
<YYINITIAL> "</use>"		{ return new Symbol(sym.CUSE);}
<YYINITIAL> "<bitSize>"		{ return new Symbol(sym.ABITSIZE);}
<YYINITIAL> [1-9][0-9]*		{ return new Symbol(sym.BITSIZE);}
<YYINITIAL> "</bitSize>"	{ return new Symbol(sym.CBITSIZE);}
<YYINITIAL> "<regbitcode>"	{ return new Symbol(sym.AREGBITCODE);}
<YYINITIAL> {regbitcode}	{ return new Symbol(sym.REGBITCODE);}
<YYINITIAL>	"</regbitcode>"	{ return new Symbol(sym.CREGBITCODE);}
<YYINITIAL> "<group>"		{ return new Symbol(sym.AGROUP);}
<YYINITIAL> "<registers>"	{ return new Symbol(sym.AREGISTERS);}
<YYINITIAL> "</registers>"	{ return new Symbol(sym.CREGISTERS);}
<YYINITIAL> "</group>"		{ return new Symbol(sym.CGROUP);}
<YYINITIAL> "<upd>"			{ return new Symbol(sym.AUPD);}
<YYINITIAL> "</upd>"		{ return new Symbol(sym.CUPD);}
<YYINITIAL> ","				{ return new Symbol(sym.COMA);}

{white}         { /*ignorar*/ }
.               { printError("caracter "+yytext()+" no reconocido"); }