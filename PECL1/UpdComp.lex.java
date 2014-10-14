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


class UpdComp {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 65536;
	private final int YY_EOF = 65537;
	public final int YYEOF = -1;

    private updClass upd = new updClass();
    private String campo;
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
    public void printError(String msg){
        System.out.println("error en linea "+(int)(yyline+1)+": "+msg);
    }
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	UpdComp (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	UpdComp (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private UpdComp () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private void yy_do_eof () {
		if (false == yy_eof_done) {

System.out.println("------------------Report--------------------");
System.out.println(upd.toString());
		}
		yy_eof_done = true;
	}
	private final int use = 4;
	private final int name = 3;
	private final int date = 2;
	private final int insBitCode = 6;
	private final int ident = 1;
	private final int comment = 7;
	private final int bitSize = 5;
	private final int endident = 8;
	private final int error = 9;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0,
		76,
		83,
		93,
		100,
		143,
		152,
		164,
		173,
		171
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NOT_ACCEPT,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NOT_ACCEPT,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NOT_ACCEPT,
		/* 50 */ YY_NOT_ACCEPT,
		/* 51 */ YY_NOT_ACCEPT,
		/* 52 */ YY_NOT_ACCEPT,
		/* 53 */ YY_NOT_ACCEPT,
		/* 54 */ YY_NOT_ACCEPT,
		/* 55 */ YY_NOT_ACCEPT,
		/* 56 */ YY_NOT_ACCEPT,
		/* 57 */ YY_NOT_ACCEPT,
		/* 58 */ YY_NOT_ACCEPT,
		/* 59 */ YY_NOT_ACCEPT,
		/* 60 */ YY_NOT_ACCEPT,
		/* 61 */ YY_NOT_ACCEPT,
		/* 62 */ YY_NOT_ACCEPT,
		/* 63 */ YY_NOT_ACCEPT,
		/* 64 */ YY_NOT_ACCEPT,
		/* 65 */ YY_NOT_ACCEPT,
		/* 66 */ YY_NOT_ACCEPT,
		/* 67 */ YY_NOT_ACCEPT,
		/* 68 */ YY_NOT_ACCEPT,
		/* 69 */ YY_NOT_ACCEPT,
		/* 70 */ YY_NOT_ACCEPT,
		/* 71 */ YY_NOT_ACCEPT,
		/* 72 */ YY_NOT_ACCEPT,
		/* 73 */ YY_NOT_ACCEPT,
		/* 74 */ YY_NOT_ACCEPT,
		/* 75 */ YY_NOT_ACCEPT,
		/* 76 */ YY_NOT_ACCEPT,
		/* 77 */ YY_NOT_ACCEPT,
		/* 78 */ YY_NOT_ACCEPT,
		/* 79 */ YY_NOT_ACCEPT,
		/* 80 */ YY_NOT_ACCEPT,
		/* 81 */ YY_NOT_ACCEPT,
		/* 82 */ YY_NOT_ACCEPT,
		/* 83 */ YY_NOT_ACCEPT,
		/* 84 */ YY_NOT_ACCEPT,
		/* 85 */ YY_NOT_ACCEPT,
		/* 86 */ YY_NOT_ACCEPT,
		/* 87 */ YY_NOT_ACCEPT,
		/* 88 */ YY_NOT_ACCEPT,
		/* 89 */ YY_NOT_ACCEPT,
		/* 90 */ YY_NOT_ACCEPT,
		/* 91 */ YY_NOT_ACCEPT,
		/* 92 */ YY_NOT_ACCEPT,
		/* 93 */ YY_NOT_ACCEPT,
		/* 94 */ YY_NOT_ACCEPT,
		/* 95 */ YY_NOT_ACCEPT,
		/* 96 */ YY_NOT_ACCEPT,
		/* 97 */ YY_NOT_ACCEPT,
		/* 98 */ YY_NOT_ACCEPT,
		/* 99 */ YY_NOT_ACCEPT,
		/* 100 */ YY_NOT_ACCEPT,
		/* 101 */ YY_NOT_ACCEPT,
		/* 102 */ YY_NOT_ACCEPT,
		/* 103 */ YY_NOT_ACCEPT,
		/* 104 */ YY_NOT_ACCEPT,
		/* 105 */ YY_NOT_ACCEPT,
		/* 106 */ YY_NOT_ACCEPT,
		/* 107 */ YY_NOT_ACCEPT,
		/* 108 */ YY_NOT_ACCEPT,
		/* 109 */ YY_NOT_ACCEPT,
		/* 110 */ YY_NOT_ACCEPT,
		/* 111 */ YY_NOT_ACCEPT,
		/* 112 */ YY_NOT_ACCEPT,
		/* 113 */ YY_NOT_ACCEPT,
		/* 114 */ YY_NOT_ACCEPT,
		/* 115 */ YY_NOT_ACCEPT,
		/* 116 */ YY_NOT_ACCEPT,
		/* 117 */ YY_NOT_ACCEPT,
		/* 118 */ YY_NOT_ACCEPT,
		/* 119 */ YY_NOT_ACCEPT,
		/* 120 */ YY_NOT_ACCEPT,
		/* 121 */ YY_NOT_ACCEPT,
		/* 122 */ YY_NOT_ACCEPT,
		/* 123 */ YY_NOT_ACCEPT,
		/* 124 */ YY_NOT_ACCEPT,
		/* 125 */ YY_NOT_ACCEPT,
		/* 126 */ YY_NOT_ACCEPT,
		/* 127 */ YY_NOT_ACCEPT,
		/* 128 */ YY_NOT_ACCEPT,
		/* 129 */ YY_NOT_ACCEPT,
		/* 130 */ YY_NOT_ACCEPT,
		/* 131 */ YY_NOT_ACCEPT,
		/* 132 */ YY_NOT_ACCEPT,
		/* 133 */ YY_NOT_ACCEPT,
		/* 134 */ YY_NOT_ACCEPT,
		/* 135 */ YY_NOT_ACCEPT,
		/* 136 */ YY_NOT_ACCEPT,
		/* 137 */ YY_NOT_ACCEPT,
		/* 138 */ YY_NOT_ACCEPT,
		/* 139 */ YY_NOT_ACCEPT,
		/* 140 */ YY_NOT_ACCEPT,
		/* 141 */ YY_NOT_ACCEPT,
		/* 142 */ YY_NOT_ACCEPT,
		/* 143 */ YY_NOT_ACCEPT,
		/* 144 */ YY_NOT_ACCEPT,
		/* 145 */ YY_NOT_ACCEPT,
		/* 146 */ YY_NOT_ACCEPT,
		/* 147 */ YY_NOT_ACCEPT,
		/* 148 */ YY_NOT_ACCEPT,
		/* 149 */ YY_NOT_ACCEPT,
		/* 150 */ YY_NOT_ACCEPT,
		/* 151 */ YY_NOT_ACCEPT,
		/* 152 */ YY_NOT_ACCEPT,
		/* 153 */ YY_NOT_ACCEPT,
		/* 154 */ YY_NOT_ACCEPT,
		/* 155 */ YY_NOT_ACCEPT,
		/* 156 */ YY_NOT_ACCEPT,
		/* 157 */ YY_NOT_ACCEPT,
		/* 158 */ YY_NOT_ACCEPT,
		/* 159 */ YY_NOT_ACCEPT,
		/* 160 */ YY_NOT_ACCEPT,
		/* 161 */ YY_NOT_ACCEPT,
		/* 162 */ YY_NOT_ACCEPT,
		/* 163 */ YY_NOT_ACCEPT,
		/* 164 */ YY_NOT_ACCEPT,
		/* 165 */ YY_NOT_ACCEPT,
		/* 166 */ YY_NOT_ACCEPT,
		/* 167 */ YY_NOT_ACCEPT,
		/* 168 */ YY_NOT_ACCEPT,
		/* 169 */ YY_NOT_ACCEPT,
		/* 170 */ YY_NOT_ACCEPT,
		/* 171 */ YY_NOT_ACCEPT,
		/* 172 */ YY_NOT_ACCEPT,
		/* 173 */ YY_NOT_ACCEPT,
		/* 174 */ YY_NOT_ACCEPT,
		/* 175 */ YY_NOT_ACCEPT,
		/* 176 */ YY_NOT_ACCEPT,
		/* 177 */ YY_NOT_ACCEPT,
		/* 178 */ YY_NOT_ACCEPT,
		/* 179 */ YY_NOT_ACCEPT,
		/* 180 */ YY_NOT_ACCEPT,
		/* 181 */ YY_NOT_ACCEPT,
		/* 182 */ YY_NOT_ACCEPT,
		/* 183 */ YY_NOT_ACCEPT,
		/* 184 */ YY_NOT_ACCEPT,
		/* 185 */ YY_NOT_ACCEPT,
		/* 186 */ YY_NOT_ACCEPT,
		/* 187 */ YY_NOT_ACCEPT,
		/* 188 */ YY_NOT_ACCEPT,
		/* 189 */ YY_NOT_ACCEPT,
		/* 190 */ YY_NOT_ACCEPT,
		/* 191 */ YY_NOT_ACCEPT,
		/* 192 */ YY_NOT_ACCEPT,
		/* 193 */ YY_NOT_ACCEPT,
		/* 194 */ YY_NOT_ACCEPT,
		/* 195 */ YY_NOT_ACCEPT,
		/* 196 */ YY_NOT_ACCEPT,
		/* 197 */ YY_NOT_ACCEPT,
		/* 198 */ YY_NOT_ACCEPT,
		/* 199 */ YY_NOT_ACCEPT
	};
	private int yy_cmap[] = unpackFromString(1,65538,
"9:9,39,38,9:2,38,9:18,39,9,19,9:4,16,9:7,10,36:2,14:8,9:2,1,9,8,9:2,20,34,3" +
"5,13:2,28,13:2,26,13:6,22,13:2,30,13:2,29,13:4,9:6,15,32,2,12,5,13,25,13,11" +
",13,31,21,4,6,3,24,13,23,18,7,17,13:2,27,37,33,9:65413,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,200,
"0,1,2,1:8,3,4,1,5,1:2,6,1:2,7,1:2,8,9,1,10,11,1,12,1,13,1,14,1:7,15,1:2,16," +
"17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41," +
"42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66," +
"67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91," +
"92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112" +
",113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,130,13" +
"1,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,1" +
"50,151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168," +
"169,170,171")[0];

	private int yy_nxt[][] = unpackFromString(172,40,
"1,2,34:36,3:2,-1:42,33,-1:3,44,-1:4,49,174,-1:4,50,-1:14,51,-1:17,77,-1:31," +
"12:6,-1:3,12:5,-1,12:2,-1,12:18,-1:12,84,-1:39,94,-1:39,101,-1:39,144,-1:43" +
",24,-1:21,24,-1:13,153,-1:56,41,-1:8,27,-1:13,165,-1:39,172,-1:32,52,-1:63," +
"41,-1:9,48,-1:17,176,-1:38,85,-1:21,85,-1:5,95:6,-1:3,95:3,-1,95,-1,95:2,-1" +
",95:16,-1,95,-1:22,102,-1,103,-1:3,104,-1,105,-1,106,-1:36,41,-1:5,48,-1:3," +
"48,-1:8,175,-1:5,53,-1:45,177,-1:32,178,-1:32,189,-1:40,56,-1:41,57,-1:66,6" +
"1,-1:11,62,-1:38,63,-1:42,4,-1:61,180,-1:17,5,-1:42,181,-1:35,65,-1:40,6,-1" +
":37,67,-1:41,7,-1:64,69,-1:13,70,-1:67,71,-1:9,72,-1:42,8,-1:34,73,-1:44,9," +
"-1:43,74,-1:32,75,-1:42,10,-1:31,1,11,12:6,35:3,12:3,35,12,35,12:2,35,12:16" +
",35,12,3,35,-1:11,78,-1:40,79,-1:32,80,-1:40,81,-1:40,82,-1:40,13,-1:31,1,1" +
"4,36:12,45,36:21,45,36,3,36,-1:12,86,-1:37,87,-1:44,88,-1:38,182,-1:21,182," +
"-1:10,89,-1:37,91,-1:44,191,-1:37,15,-1:45,16,-1:21,16,-1:3,1,17,37:14,46,3" +
"7:21,3,37,-1:6,96,-1:35,97:6,-1:3,97:3,-1,97,-1,97:2,-1,97:16,-1,97,-1:17,1" +
"92,-1:40,18,-1:28,99,-1:42,19,-1:31,1,20,38:17,47,38:18,3,38,-1:17,107,-1:2" +
"4,108,-1:18,183,-1:41,109,-1:22,110,-1:54,111,-1:25,184,-1:50,112,-1:23,113" +
",-1:40,115,-1:48,185,-1:42,186,-1:29,117,-1:51,194,-1:44,118,-1:42,119,-1:1" +
"6,122,-1:45,21,-1:48,123,-1:45,198,-1:43,124,-1:41,125,-1:41,126,-1:31,128," +
"-1:35,22,-1:25,129,-1:56,130,-1:38,131,-1:42,188,-1:17,133,-1:40,134,-1:51," +
"133,-1:46,136,-1:24,137,-1:43,138,-1:46,139,-1:23,124,-1:40,140,-1:42,141,-" +
"1:38,124,-1:57,124,-1:23,142,-1:37,140,-1:34,1,23,39:12,24,39:21,24,39,3,39" +
",-1:32,145,-1:18,146,-1:35,147,-1:62,148,-1:20,149,-1:61,150,-1:11,151,-1:4" +
"2,25,-1:31,1,26,40:34,27,40,3,40,-1:11,154,-1:34,155,-1:51,156,-1:55,157,-1" +
":16,158,-1:35,159,-1:67,160,-1:7,161,-1:48,162,-1:32,163,-1:42,28,-1:31,1,2" +
"9,42:36,3,42,-1:2,166,-1:40,196,-1:41,168,-1:40,169,-1:40,170,-1:40,30,-1:3" +
"1,1,31,43:38,-1:2,172:6,32,-1:2,172:3,-1,172,-1,172:2,-1,172:16,-1,172,-1:2" +
",1,34:37,3:2,-1:15,54,-1:42,55,-1:25,190,-1:40,58,-1:41,59,-1:37,64,-1:45,6" +
"6,-1:35,68,-1:46,90,-1:21,90,-1:24,114,-1:33,116,-1:29,120,-1:59,121,-1:31," +
"127,-1:25,135,-1:40,179,-1:40,60,-1:48,199,-1:21,199,-1:7,98,-1:49,92,-1:21" +
",92,-1:7,187,-1:39,132,-1:39,197,-1:39,167,-1:50,195,-1:38,193,-1:21,193,-1" +
":3");

	public int yylex ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {
				yy_do_eof();
				return YYEOF;
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{printError("caracter inesperado");}
					case -3:
						break;
					case 3:
						{}
					case -4:
						break;
					case 4:
						{ yybegin(use); }
					case -5:
						break;
					case 5:
						{  yybegin(name);  }
					case -6:
						break;
					case 6:
						{ yybegin(date);  }
					case -7:
						break;
					case 7:
						{ yybegin(ident);  }
					case -8:
						break;
					case 8:
						{ yybegin(comment);  }
					case -9:
						break;
					case 9:
						{ yybegin(bitSize); }
					case -10:
						break;
					case 10:
						{yybegin(insBitCode);}
					case -11:
						break;
					case 11:
						{ printError("identificador no valido");
            upd.incScanErrors();
            yybegin(error); }
					case -12:
						break;
					case 12:
						{ campo = yytext();  }
					case -13:
						break;
					case 13:
						{  upd.setIdent(campo);
                      campo=null;
                      yybegin(YYINITIAL); }
					case -14:
						break;
					case 14:
						{  printError("fecha no valida");
            upd.incScanErrors();
            yybegin(error); }
					case -15:
						break;
					case 15:
						{ upd.setFecha(campo);
                  campo = null;
                  yybegin(YYINITIAL);}
					case -16:
						break;
					case 16:
						{  campo = yytext(); }
					case -17:
						break;
					case 17:
						{ printError("nombre no reconocido");
            upd.incScanErrors();
            yybegin(error); 
          }
					case -18:
						break;
					case 18:
						{ campo = yytext(); }
					case -19:
						break;
					case 19:
						{  if(!upd.putReg(campo,campo)){
                      System.out.println("WARNING: Registro repetido: "+campo);
                    }else{
                      upd.incRegs();
                    }
                    campo = null;
                    yybegin(YYINITIAL);
                  }
					case -20:
						break;
					case 20:
						{ printError("uso no reconocido");
          upd.incScanErrors();
          yybegin(error); 
        }
					case -21:
						break;
					case 21:
						{ if (campo.equals("\"AllPurpose\"")) {                    
                    upd.incAllPurpose();
                  }else if (campo.equals("\"Accumulator\"")) {
                    upd.incAccumulator();
                  }else if (campo.equals("\"ProgramPc\"")) {
                    upd.incProgramPC();
                  }else if (campo.equals("\"Index\"")) {
                    upd.incIndex();
                  }else if (campo.equals("\"FlagVector\"")) {
                    upd.incFlagVector();
                  }else if (campo.equals("\"StackPointer\"")) {
                    upd.incStackPointer();
                  }else{
                    yybegin(error);
                  }
                  yybegin(YYINITIAL);                   
                }
					case -22:
						break;
					case 22:
						{ campo = yytext(); }
					case -23:
						break;
					case 23:
						{ printError("tama??o de bits no reconocido");
                upd.incScanErrors();
                yybegin(error);}
					case -24:
						break;
					case 24:
						{  campo=yytext(); }
					case -25:
						break;
					case 25:
						{ upd.addBits(Integer.parseInt(campo));
                            campo=null;
                            yybegin(YYINITIAL);
                          }
					case -26:
						break;
					case 26:
						{ printError("Mascara de bits no reconocida");
                  upd.incScanErrors();
                  yybegin(error);}
					case -27:
						break;
					case 27:
						{/*??*/}
					case -28:
						break;
					case 28:
						{yybegin(YYINITIAL);}
					case -29:
						break;
					case 29:
						{/*ignorar*/}
					case -30:
						break;
					case 30:
						{ yybegin(YYINITIAL);  }
					case -31:
						break;
					case 31:
						{/*ignorar*/}
					case -32:
						break;
					case 32:
						{  yybegin(YYINITIAL);
                            System.out.println("recuperacion de error en linea: "+(int)(yyline+1));
                            System.out.println("------------------------------");  }
					case -33:
						break;
					case 34:
						{printError("caracter inesperado");}
					case -34:
						break;
					case 35:
						{ printError("identificador no valido");
            upd.incScanErrors();
            yybegin(error); }
					case -35:
						break;
					case 36:
						{  printError("fecha no valida");
            upd.incScanErrors();
            yybegin(error); }
					case -36:
						break;
					case 37:
						{ printError("nombre no reconocido");
            upd.incScanErrors();
            yybegin(error); 
          }
					case -37:
						break;
					case 38:
						{ printError("uso no reconocido");
          upd.incScanErrors();
          yybegin(error); 
        }
					case -38:
						break;
					case 39:
						{ printError("tama??o de bits no reconocido");
                upd.incScanErrors();
                yybegin(error);}
					case -39:
						break;
					case 40:
						{ printError("Mascara de bits no reconocida");
                  upd.incScanErrors();
                  yybegin(error);}
					case -40:
						break;
					case 41:
						{/*??*/}
					case -41:
						break;
					case 42:
						{/*ignorar*/}
					case -42:
						break;
					case 43:
						{/*ignorar*/}
					case -43:
						break;
					case 45:
						{  printError("fecha no valida");
            upd.incScanErrors();
            yybegin(error); }
					case -44:
						break;
					case 46:
						{ printError("nombre no reconocido");
            upd.incScanErrors();
            yybegin(error); 
          }
					case -45:
						break;
					case 47:
						{ printError("uso no reconocido");
          upd.incScanErrors();
          yybegin(error); 
        }
					case -46:
						break;
					case 48:
						{/*??*/}
					case -47:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
