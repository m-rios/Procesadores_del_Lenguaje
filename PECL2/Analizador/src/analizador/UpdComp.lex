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

marca = (ident|date|name|use|bitSize|insBitCode)
aEtiqueta = "<"{marca}">"
cEtiqueta = "</"{marca}">"

%unicode
%line
%state comment
%class scanner

%cup

%%

<YYINITIAL> "<comment>" { yybegin(comment);}
<comment> . {/*ignorar*/}
<comment> "</comment>" {  yybegin(YYINITIAL);}

<YYINITIAL> "<descr>" { return new Symbol(sym.ADESCR);}
<YYINITIAL> "</descr>" { return new Symbol(sym.CDESCR);}
<YYINITIAL> "<ident>" { return new Symbol(sym.AIDENT);}
<YYINITIAL> "</ident>" { return new Symbol(sym.CIDENT);}
<YYINITIAL> "<date>" { return new Symbol(sym.ADATE);}
<YYINITIAL> "</date>" { return new Symbol(sym.CDATE);}

<YYINITIAL> {ident} { return new Symbol(sym.IDENT);}
<YYINITIAL> {date}  { return new Symbol(sym.DATE);}

{white}         { /*ignorar*/ }
.               { printError("caracter "+yytext()+" no reconocido"); }