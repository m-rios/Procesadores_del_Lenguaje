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
    Ident = s; }
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
	private final int insBitCode = 6;
	private final int bitSize = 5;
	private final int use = 4;
	private final int name = 3;
	private final int date = 2;
	private final int YYINITIAL = 0;
	private final int ident = 1;
	private final int comment = 7;
	private final int yy_state_dtrans[] = {
		0,
		58,
		80,
		150,
		195,
		199,
		167,
		179
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
		/* 22 */ YY_NOT_ACCEPT,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NOT_ACCEPT,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NOT_ACCEPT,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NOT_ACCEPT,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NOT_ACCEPT,
		/* 34 */ YY_NOT_ACCEPT,
		/* 35 */ YY_NOT_ACCEPT,
		/* 36 */ YY_NOT_ACCEPT,
		/* 37 */ YY_NOT_ACCEPT,
		/* 38 */ YY_NOT_ACCEPT,
		/* 39 */ YY_NOT_ACCEPT,
		/* 40 */ YY_NOT_ACCEPT,
		/* 41 */ YY_NOT_ACCEPT,
		/* 42 */ YY_NOT_ACCEPT,
		/* 43 */ YY_NOT_ACCEPT,
		/* 44 */ YY_NOT_ACCEPT,
		/* 45 */ YY_NOT_ACCEPT,
		/* 46 */ YY_NOT_ACCEPT,
		/* 47 */ YY_NOT_ACCEPT,
		/* 48 */ YY_NOT_ACCEPT,
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
		/* 188 */ YY_NO_ANCHOR,
		/* 189 */ YY_NOT_ACCEPT,
		/* 190 */ YY_NOT_ACCEPT,
		/* 191 */ YY_NOT_ACCEPT,
		/* 192 */ YY_NOT_ACCEPT,
		/* 193 */ YY_NOT_ACCEPT,
		/* 194 */ YY_NOT_ACCEPT,
		/* 195 */ YY_NOT_ACCEPT,
		/* 196 */ YY_NO_ANCHOR,
		/* 197 */ YY_NOT_ACCEPT,
		/* 198 */ YY_NOT_ACCEPT,
		/* 199 */ YY_NOT_ACCEPT,
		/* 200 */ YY_NO_ANCHOR,
		/* 201 */ YY_NOT_ACCEPT,
		/* 202 */ YY_NOT_ACCEPT,
		/* 203 */ YY_NOT_ACCEPT
	};
	private int yy_cmap[] = unpackFromString(1,65538,
"9:9,27,28,9:2,28,9:18,27,9:14,10,24:2,9:10,1,9,8,9:3,22,23,9:15,20,9:13,15," +
"19,2,12,5,9,16,9,11,9:2,13,4,6,3,9:2,14,18,7,17,9:2,25,26,21,9:65413,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,204,
"0,1,2,1:8,3,1:6,4,1,5,1,6,1,7,1,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22," +
"23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47," +
"48,49,50,51,52,53,54,55,56,57,58,59,48,60,61,62,63,64,65,66,67,68,69,70,71," +
"72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96," +
"97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,11" +
"6,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131,132,133,134,1" +
"35,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,153," +
"154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,169,170,171,172" +
",173,174,175,176,177,178,179,180,181,182,183,184")[0];

	private int yy_nxt[][] = unpackFromString(185,29,
"1,2,23:25,3:2,-1:31,22,-1:3,26,-1:4,29,186,-1:4,31,-1,33,-1:10,70,-1:51,18," +
"24,-1:13,180,-1:21,34,-1:50,24,28,-1:17,189,-1:23,59,-1:2,60,-1:36,28,-1:3," +
"24,28,-1:8,187,-1:5,35,-1:26,81,-1,82,-1:34,190,-1:20,151,-1:29,191,-1:21,1" +
"97,-1:29,38,-1:30,39,-1:43,43,-1:12,44,-1:27,45,-1:31,4,-1:40,193,-1:16,5,-" +
"1:31,194,-1:24,47,-1:29,6,-1:26,49,-1:30,7,-1:41,51,-1:14,52,-1:44,53,-1:10" +
",54,-1:31,8,-1:23,55,-1:33,9,-1:32,56,-1:21,57,-1:31,10,-1:20,1,27,23:26,-1" +
":12,61,-1:22,62,-1:35,63,-1:23,64,-1:26,65,-1:37,66,-1:20,67,-1:37,68,-1:20" +
",69,-1:29,11,-1:28,12,-1:32,71,72,-1:26,73,-1:22,74,-1:39,75,-1:19,76,-1:32" +
",77,-1:31,78,-1:21,79,-1:24,68,-1:25,1,30,23:26,-1:13,83,-1:27,84,-1:32,85," +
"-1:29,86,-1:19,87,-1:32,88,-1:22,89,-1:30,90,-1:29,13,-1:23,91,-1:33,92,-1:" +
"21,93,-1:39,94,-1:27,95,-1:33,96,-1:23,97,-1:24,98,-1:24,99,-1:33,100,-1:30" +
",101,-1:19,102,-1:39,103,-1:27,104,-1:33,105,-1:23,106,-1:24,107,-1:24,108," +
"-1:33,109,-1:21,110,-1:39,111,-1:27,112,-1:33,113,-1:23,114,-1:24,115,-1:24" +
",116,-1:33,117,-1:30,118,-1:19,119,-1:39,120,-1:27,121,-1:33,122,-1:23,123," +
"-1:24,124,-1:24,125,-1:33,126,-1:21,127,-1:39,128,-1:27,129,-1:33,130,-1:23" +
",131,-1:24,132,-1:24,133,-1:33,134,-1:21,135,-1:39,136,-1:27,137,-1:33,138," +
"-1:23,139,-1:24,140,-1:24,141,-1:33,142,-1:21,143,-1:39,144,-1:27,145,-1:33" +
",146,-1:23,147,-1:24,148,-1:24,149,-1:33,14,-1:20,1,32,23:26,-1:7,152,-1:37" +
",201,-1:18,154,-1:31,15,-1:37,156,-1:29,157,-1:15,158,-1:31,16,-1:39,160,-1" +
":20,161,-1:24,162,-1:41,163,-1:19,164,-1:38,165,-1:12,166,-1:31,17,-1:20,1," +
"200,23:22,18,23:3,-1:12,169,-1:23,170,-1:40,171,-1:32,172,-1:17,173,-1:24,1" +
"74,-1:44,175,-1:8,176,-1:37,177,-1:21,178,-1:31,19,-1:20,1,20,25:26,-1:3,18" +
"1,-1:29,202,-1:30,183,-1:29,184,-1:29,185,-1:29,21,-1:35,36,-1:31,37,-1:20," +
"155,-1:22,198,-1:29,40,-1:30,41,-1:26,46,-1:34,48,-1:24,50,-1:21,1,188,23:2" +
"6,-1:11,159,-1:22,192,-1:29,42,-1:23,1,196,23:26,-1:11,168,-1:22,153,-1:28," +
"203,-1:28,182,-1:24");

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
						{System.out.println("Error l??xico en l??nea "+yyline);}
					case -3:
						break;
					case 3:
						{System.out.println("nada");}
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
						{yybegin(insBitCode);}
					case -11:
						break;
					case 11:
						{System.out.println("identificador");}
					case -12:
						break;
					case 12:
						{yybegin(YYINITIAL);}
					case -13:
						break;
					case 13:
						{yybegin(YYINITIAL);}
					case -14:
						break;
					case 14:
						{/*add to class*/}
					case -15:
						break;
					case 15:
						{yybegin(YYINITIAL);}
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
						{System.out.println("bitmask");}
					case -19:
						break;
					case 19:
						{yybegin(YYINITIAL);}
					case -20:
						break;
					case 20:
						{/*mientras comentario saltamos todo*/}
					case -21:
						break;
					case 21:
						{yybegin(YYINITIAL);}
					case -22:
						break;
					case 23:
						{System.out.println("Error l??xico en l??nea "+yyline);}
					case -23:
						break;
					case 24:
						{System.out.println("bitmask");}
					case -24:
						break;
					case 25:
						{/*mientras comentario saltamos todo*/}
					case -25:
						break;
					case 27:
						{System.out.println("Error l??xico en l??nea "+yyline);}
					case -26:
						break;
					case 28:
						{System.out.println("bitmask");}
					case -27:
						break;
					case 30:
						{System.out.println("Error l??xico en l??nea "+yyline);}
					case -28:
						break;
					case 32:
						{System.out.println("Error l??xico en l??nea "+yyline);}
					case -29:
						break;
					case 188:
						{System.out.println("Error l??xico en l??nea "+yyline);}
					case -30:
						break;
					case 196:
						{System.out.println("Error l??xico en l??nea "+yyline);}
					case -31:
						break;
					case 200:
						{System.out.println("Error l??xico en l??nea "+yyline);}
					case -32:
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
