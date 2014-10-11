//codigo de usuario
import java.io.*;
%%
%{

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
%integer
%line
%unicode
%states comment, id, error

letra=[a-zA-Z]
id={letra}[^\r\n\t" "]*

%%
<YYINITIAL> "<comment>" {yybegin(comment);}
<comment> (.|[\r\n])*"</comment>" {yybegin(YYINITIAL);}

<YYINITIAL> "<ident>" {yybegin(id);}
<id> {id}"</ident>" {System.out.println("identificador: "+yytext());yybegin(YYINITIAL);}
<id> . {System.out.println("wrong identificator");yybegin(error);}
<error> "</".*">" {yybegin(YYINITIAL);}
<error> . {}

[" "\r\n\t] {}
. {System.out.println("lexical error at line: "+(int)(yyline+1));}