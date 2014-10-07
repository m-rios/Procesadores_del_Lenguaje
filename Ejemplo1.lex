import java.io.*;
%%
%{

public static void main (String argv[])
throws java.io.IOException {
    Yylex yy = new Yylex(System.in);
    while (yy.yylex() != -1) ;
   } 
%}


%integer
%%

[\r\t \n] 			{/*nada*/ }

[A-Z][a-z]+			{System.out.println ("--> Nombre propio"); }

0*[1-9][0-9][0-9]+	{System.out.println ("--> Numero mayor de 98"); }
99					{System.out.println ("--> Numero mayor de 98"); }

\([^)]+\)				{System.out.println ("--> Bloque de texto entre parentesis"); }

.          				{ System.out.println(yytext()+" -> No conocido!! "); }