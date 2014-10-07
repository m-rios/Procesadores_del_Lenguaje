import java.io.*;

%%
%{

String mensaje="";

public static void main (String argv[])
throws java.io.IOException {
		FileInputStream fis=new FileInputStream("ficheroPrueba.txt");
    Yylex yy = new Yylex(fis);
    while (yy.yylex() != -1) ;
   } 
%}

email=[a-zA-Z]+(\.[a-zA-Z]+)*@[a-zA-Z]+(\.[a-zA-Z]+)+

%state estadoAgua, estadoError, estadoOK

%integer
%%

[ \t]	{/* Nada */}

<YYINITIAL> "AGUA"	{mensaje="--> Sirve un agua";
					yybegin(estadoAgua);}

<estadoAgua> {email} {mensaje=mensaje + " a " + yytext(); yybegin(estadoOK);}

<estadoAgua> \r\n {System.out.println ("--> Error: se esperaba un email"); yybegin(YYINITIAL);}

<estadoAgua> . {System.out.println ("--> Error: se esperaba un email"); yybegin(estadoError);}

<estadoOK> "OK" {System.out.println (mensaje); yybegin(estadoError);}

<estadoOK> \r\n {System.out.println ("--> Error: se esperaba un OK"); yybegin(YYINITIAL);}

<estadoOK> . {System.out.println ("--> Error: se esperaba un OK"); yybegin(estadoError);}

<estadoError> \r\n {yybegin(YYINITIAL);}

<estadoError> . {/*Mientras estemos en error nos saltamos todo lo que leamos*/}

[\r\n]	{ /* Nada */}

.       { /* Nada */}