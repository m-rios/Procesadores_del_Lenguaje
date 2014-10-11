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


class Yylex {
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
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
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
	private final int end = 8;
	private final int insBitCode = 6;
	private final int ident = 1;
	private final int comment = 7;
	private final int bitSize = 5;
	private final int error = 9;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0,
		73,
		162,
		182,
		187,
		138,
		190,
		147,
		154,
		192
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
		/* 32 */ YY_NOT_ACCEPT,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NOT_ACCEPT,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NOT_ACCEPT,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NOT_ACCEPT,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NOT_ACCEPT,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NOT_ACCEPT,
		/* 49 */ YY_NO_ANCHOR,
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
		/* 164 */ YY_NO_ANCHOR,
		/* 165 */ YY_NOT_ACCEPT,
		/* 166 */ YY_NOT_ACCEPT,
		/* 167 */ YY_NO_ANCHOR,
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
		/* 183 */ YY_NO_ANCHOR,
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
"9:9,10,11,9:2,11,9:18,10,9,21,9:4,18,9:7,12,38:2,17:8,9:2,1,9,8,9:2,22,37,2" +
"8,15:2,31,15:2,29,15:6,24,15:2,33,15:2,32,15:4,9:6,16,35,2,14,5,15,27,15,13" +
",15,34,23,4,6,3,26,15,25,20,7,19,15:2,30,39,36,9:65413,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,200,
"0,1,2,1:8,3,4,1:11,5,6,1,7,1,8,1,9,10,1,11,1,12,13,1:2,14,15,16,17,18,19,20" +
",21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45" +
",46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70" +
",71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95" +
",96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,11" +
"5,116,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131,132,133,2" +
"3,9,134,135,136,137,138,11,139,140,141,142,143,144,145,146,147,148,149,150," +
"151,11,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168," +
"169")[0];

	private int yy_nxt[][] = unpackFromString(170,40,
"1,2,33:8,3:2,33:28,-1:42,32,-1:3,40,-1:6,42,163,-1:4,44,-1:15,46,-1:16,74,-" +
"1:29,12:8,-1:2,12:28,-1:17,24,-1:20,24,-1:31,25,-1:8,37,-1:12,148,-1:39,155" +
",-1:28,161:7,31,161:2,-1,161:28,-1:3,48,-1:37,160,181:9,-1,181:28,-1:17,24," +
"-1:12,25,-1:7,36,-1:31,25,-1:5,37,-1:2,37,-1:16,166,-1:40,80,-1:20,80,-1:7," +
"165,-1:7,50,-1:37,84,-1:47,168,-1:21,85:6,-1:5,85:4,-1:2,85:2,-1,85:16,-1,8" +
"5,-1:13,169,-1:48,91,-1,92,-1:4,93,-1,94,-1,95,-1:10,184,-1:36,160,181:9,-1" +
",161,181:27,-1:5,53,-1:41,54,-1:69,58,-1:8,59,-1:38,60,-1:42,4,-1:64,171,-1" +
":14,5,-1:44,172,-1:33,62,-1:40,6,-1:37,64,-1:41,7,-1:67,66,-1:10,67,-1:60,6" +
"8,-1:16,69,-1:42,8,-1:34,70,-1:44,9,-1:45,71,-1:30,72,-1:42,10,-1:31,1,11,1" +
"2:6,35:3,3,35,12:4,35:2,12:2,35,12:16,35,12,-1:13,75,-1:40,76,-1:30,77,-1:4" +
"0,78,-1:40,79,-1:40,13,-1:43,81,-1:44,173,-1:20,173,-1:13,186,-1:44,14,-1:2" +
"0,14,-1:7,86,-1:35,87:6,-1:5,87:4,-1:2,87:2,-1,87:16,-1,87,-1:16,188,-1:41," +
"15,-1:26,89,-1:42,16,-1:50,96,-1:22,97,-1:20,174,-1:41,98,-1:20,99,-1:56,10" +
"0,-1:23,175,-1:52,101,-1:21,102,-1:40,104,-1:50,176,-1:41,177,-1:28,106,-1:" +
"53,191,-1:44,107,-1:42,108,-1:14,111,-1:45,17,-1:50,112,-1:45,196,-1:44,113" +
",-1:41,114,-1:41,115,-1:30,117,-1:35,18,-1:23,118,-1:58,119,-1:38,120,-1:42" +
",179,-1:15,122,-1:40,123,-1:52,199,-1:47,125,-1:22,126,-1:45,127,-1:46,128," +
"-1:47,129,-1:14,130,-1:42,131,-1:38,132,-1:55,19,-1:43,133,-1:21,134,-1:53," +
"20,-1:39,21,-1:23,136,-1:55,22,-1:43,137,-1:35,23,-1:18,1,183,33:8,3:2,33:5" +
",24,33:20,36,33,-1:35,140,-1:17,141,-1:33,142,-1:65,143,-1:19,144,-1:62,145" +
",-1:8,146,-1:42,26,-1:31,1,27,38:38,-1:2,149,-1:40,194,-1:41,151,-1:40,152," +
"-1:40,153,-1:40,28,-1:31,1,29,39:9,3,39:28,-1:13,75,156,-1:41,157,-1:30,158" +
",-1:37,159,-1:42,30,-1:31,1,33:9,3:2,33:5,41,33:20,41,33,-1:16,51,-1:35,90," +
"-1:47,52,-1:23,185,-1:40,55,-1:41,56,-1:37,61,-1:47,63,-1:33,65,-1:49,82,-1" +
":20,82,-1:24,103,-1:32,105,-1:28,109,-1:61,110,-1:31,116,-1:23,124,-1:61,13" +
"5,-1:14,1,43,33:8,3:2,33:6,45,33:21,-1:12,139,-1:31,170,-1:40,57,-1:51,198," +
"-1:20,198,-1,1,164,33:8,3:2,33:9,47,33:18,-1:4,88,-1:52,83,-1:20,83,-1,1,33" +
":9,3:2,33:28,-1:4,178,-1:35,1,49,167:8,34,3,167:28,-1:4,121,-1:39,195,-1:39" +
",150,-1:51,193,-1:26,180,-1:53,189,-1:20,189,-1:8,197,-1:32");

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
						{System.out.println("unknown error at line: "+(int)(yyline+1));}
					case -3:
						break;
					case 3:
						{}
					case -4:
						break;
					case 4:
						{yybegin(use);}
					case -5:
						break;
					case 5:
						{yybegin(name);}
					case -6:
						break;
					case 6:
						{yybegin(date);}
					case -7:
						break;
					case 7:
						{yybegin(ident);}
					case -8:
						break;
					case 8:
						{yybegin(comment);}
					case -9:
						break;
					case 9:
						{yybegin(bitSize);}
					case -10:
						break;
					case 10:
						{yybegin(bitSize);}
					case -11:
						break;
					case 11:
						{System.out.println("error in line "+(int)(yyline+1)+": identifier expected");
           yybegin(error);}
					case -12:
						break;
					case 12:
						{upd.setIdent(yytext());yybegin(end);}
					case -13:
						break;
					case 13:
						{yybegin(YYINITIAL);}
					case -14:
						break;
					case 14:
						{upd.setFecha(yytext());yybegin(end);}
					case -15:
						break;
					case 15:
						{if(!upd.putReg(yytext(),"null")){
                  System.out.println("WARNING: Registro repetido: "+yytext());
              }else{
                upd.incRegs();
              }}
					case -16:
						break;
					case 16:
						{yybegin(YYINITIAL);}
					case -17:
						break;
					case 17:
						{yybegin(YYINITIAL);}
					case -18:
						break;
					case 18:
						{upd.incIndex();}
					case -19:
						break;
					case 19:
						{upd.incProgramPC();}
					case -20:
						break;
					case 20:
						{upd.incAllPurpose();}
					case -21:
						break;
					case 21:
						{upd.incFlagVector();}
					case -22:
						break;
					case 22:
						{upd.incAccumulator();}
					case -23:
						break;
					case 23:
						{upd.incStackPointer();}
					case -24:
						break;
					case 24:
						{upd.addBits(Integer.parseInt(yytext()));}
					case -25:
						break;
					case 25:
						{/*??*/}
					case -26:
						break;
					case 26:
						{yybegin(YYINITIAL);}
					case -27:
						break;
					case 27:
						{}
					case -28:
						break;
					case 28:
						{yybegin(YYINITIAL);System.out.println("fin comentario "+(int)(yyline+1));}
					case -29:
						break;
					case 29:
						{System.out.println("End of tag expected at line: "+(int)(yyline+1)); yybegin(error);}
					case -30:
						break;
					case 30:
						{yybegin(YYINITIAL);}
					case -31:
						break;
					case 31:
						{yybegin(YYINITIAL);}
					case -32:
						break;
					case 33:
						{System.out.println("unknown error at line: "+(int)(yyline+1));}
					case -33:
						break;
					case 34:
						{}
					case -34:
						break;
					case 35:
						{System.out.println("error in line "+(int)(yyline+1)+": identifier expected");
           yybegin(error);}
					case -35:
						break;
					case 36:
						{upd.addBits(Integer.parseInt(yytext()));}
					case -36:
						break;
					case 37:
						{/*??*/}
					case -37:
						break;
					case 38:
						{}
					case -38:
						break;
					case 39:
						{System.out.println("End of tag expected at line: "+(int)(yyline+1)); yybegin(error);}
					case -39:
						break;
					case 41:
						{System.out.println("unknown error at line: "+(int)(yyline+1));}
					case -40:
						break;
					case 43:
						{System.out.println("unknown error at line: "+(int)(yyline+1));}
					case -41:
						break;
					case 45:
						{System.out.println("unknown error at line: "+(int)(yyline+1));}
					case -42:
						break;
					case 47:
						{System.out.println("unknown error at line: "+(int)(yyline+1));}
					case -43:
						break;
					case 49:
						{System.out.println("unknown error at line: "+(int)(yyline+1));}
					case -44:
						break;
					case 164:
						{System.out.println("unknown error at line: "+(int)(yyline+1));}
					case -45:
						break;
					case 167:
						{System.out.println("unknown error at line: "+(int)(yyline+1));}
					case -46:
						break;
					case 183:
						{System.out.println("unknown error at line: "+(int)(yyline+1));}
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
