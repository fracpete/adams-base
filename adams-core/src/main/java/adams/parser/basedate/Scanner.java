/* The following code was generated by JFlex 1.4.3 on 11/09/17 12:55 PM */

/*
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


/*
 * Scanner.java
 * Copyright (C) 2009-2017 University of Waikato, Hamilton, New Zealand
 */

package adams.parser.basedate;

import adams.core.DateFormat;
import adams.core.base.BaseDate;
import adams.parser.TimeAmount;
import java_cup.runtime.SymbolFactory;

import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

/**
 * A scanner for date expressions.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 * @version $Revision$
 */

public class Scanner implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = {
     0,  0,  0,  0,  0,  0,  0,  0,  0, 36, 36,  0, 36, 36,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
    36,  0,  0,  0,  0,  6,  0,  0, 33, 34,  3,  2, 35,  1, 31,  4, 
    30, 32, 32, 32, 32, 32, 32, 32, 32, 32,  0,  0,  0,  0,  0,  0, 
     0,  7,  8, 23, 24, 16, 21, 15, 29, 19,  0, 27, 13, 28, 20, 14, 
    18, 10, 11,  9, 12, 26,  0, 22, 17, 25,  0,  0,  0,  0,  5,  0, 
     0,  7,  8, 23, 24, 16, 21, 15, 29, 19,  0, 27, 13, 28, 20, 14, 
    18, 10, 11,  9, 12, 26,  0, 22, 17, 25,  0,  0,  0,  0,  0,  0
  };

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7"+
    "\16\1\1\10\1\1\1\11\1\12\1\13\1\14\23\0"+
    "\1\10\1\0\1\10\2\0\1\15\4\0\1\16\1\17"+
    "\1\20\1\21\1\22\3\0\1\23\3\0\2\10\1\24"+
    "\1\25\1\0\1\26\1\0\1\27\2\0\1\30\1\31"+
    "\1\32\1\0\1\10\1\0\1\33\1\21\1\34\1\35"+
    "\1\0\1\10\11\0\1\36\1\37";

  private static int [] zzUnpackAction() {
    int [] result = new int[102];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\45\0\112\0\157\0\45\0\45\0\45\0\45"+
    "\0\224\0\271\0\336\0\u0103\0\u0128\0\u014d\0\u0172\0\u0197"+
    "\0\u01bc\0\u01e1\0\u0206\0\u022b\0\u0250\0\u0275\0\u029a\0\u02bf"+
    "\0\45\0\45\0\45\0\45\0\u02e4\0\u0309\0\u032e\0\u0353"+
    "\0\u0378\0\u039d\0\u03c2\0\u03e7\0\u040c\0\u0431\0\u0456\0\u047b"+
    "\0\u04a0\0\u04c5\0\u04ea\0\u050f\0\u0534\0\u0559\0\u057e\0\u05a3"+
    "\0\u02bf\0\u05c8\0\u05ed\0\u0612\0\45\0\u0637\0\u065c\0\u0681"+
    "\0\u06a6\0\45\0\45\0\45\0\u06cb\0\45\0\u06f0\0\u0715"+
    "\0\u073a\0\45\0\u075f\0\u0784\0\u07a9\0\u07ce\0\u07f3\0\45"+
    "\0\45\0\u0818\0\45\0\u083d\0\45\0\u0862\0\u0887\0\45"+
    "\0\45\0\45\0\u08ac\0\u08d1\0\u08f6\0\45\0\45\0\45"+
    "\0\45\0\u091b\0\u0940\0\u0965\0\u098a\0\u09af\0\u09d4\0\u09f9"+
    "\0\u0a1e\0\u0a43\0\u0a68\0\u0a8d\0\45\0\45";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[102];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11"+
    "\1\12\1\13\1\2\1\14\1\2\1\15\2\2\1\16"+
    "\1\2\1\17\1\2\1\20\1\21\1\22\1\23\1\24"+
    "\1\25\2\2\1\26\1\2\1\27\1\30\1\27\1\31"+
    "\1\32\1\33\1\34\70\0\1\35\44\0\1\36\31\0"+
    "\1\37\66\0\1\40\24\0\1\41\1\0\1\42\53\0"+
    "\1\43\37\0\1\44\47\0\1\45\2\0\1\46\36\0"+
    "\1\47\44\0\1\50\43\0\1\51\47\0\1\52\44\0"+
    "\1\53\33\0\1\54\55\0\1\55\42\0\1\56\46\0"+
    "\1\57\15\0\1\60\1\61\1\60\42\0\1\62\1\0"+
    "\1\62\30\0\1\63\44\0\1\64\31\0\1\65\44\0"+
    "\1\66\46\0\1\67\40\0\1\70\61\0\1\71\37\0"+
    "\1\72\47\0\1\73\52\0\1\74\42\0\1\75\44\0"+
    "\1\76\34\0\1\77\46\0\1\100\47\0\1\101\52\0"+
    "\1\102\22\0\1\103\61\0\1\104\21\0\1\105\36\0"+
    "\1\106\24\0\1\57\15\0\1\107\1\61\1\107\24\0"+
    "\1\57\15\0\1\62\1\0\1\62\31\0\1\110\44\0"+
    "\1\111\42\0\1\112\35\0\1\113\43\0\1\114\45\0"+
    "\1\115\50\0\1\116\42\0\1\117\61\0\1\120\26\0"+
    "\1\121\42\0\1\122\45\0\1\123\70\0\1\106\42\0"+
    "\1\106\1\0\1\106\24\0\1\57\15\0\1\124\1\61"+
    "\1\124\30\0\1\125\34\0\1\126\43\0\1\127\44\0"+
    "\1\130\66\0\1\131\10\0\1\132\16\0\1\57\15\0"+
    "\1\133\1\61\1\133\24\0\1\134\62\0\1\135\1\0"+
    "\1\135\24\0\1\57\15\0\1\133\1\61\1\133\15\0"+
    "\1\136\71\0\1\137\1\0\1\137\15\0\1\140\34\0"+
    "\1\141\73\0\1\142\52\0\1\143\1\0\1\143\13\0"+
    "\1\144\73\0\1\145\1\0\1\145\35\0\1\146\13\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2738];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\2\1\4\11\20\1\4\11\23\0\1\1"+
    "\1\0\1\1\2\0\1\11\4\0\3\11\1\1\1\11"+
    "\3\0\1\11\3\0\2\1\2\11\1\0\1\11\1\0"+
    "\1\11\2\0\3\11\1\0\1\1\1\0\4\11\1\0"+
    "\1\1\11\0\2\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[102];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
  // Author: FracPete (fracpete at waikato dot ac dot nz)
  // Version: $Revision$
  protected SymbolFactory sf;

  protected static DateFormat m_Format;
  static {
    m_Format = new DateFormat(BaseDate.FORMAT);
  }

  public Scanner(InputStream r, SymbolFactory sf){
    this(r);
    this.sf = sf;
  }


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Scanner(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public Scanner(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 29: 
          { return sf.newSymbol("Month",  sym.TIMEAMOUNT, new TimeAmount(Calendar.MONTH,     1));
          }
        case 32: break;
        case 14: 
          { return sf.newSymbol("Log", sym.LOG);
          }
        case 33: break;
        case 9: 
          { return sf.newSymbol("Left Bracket", sym.LPAREN);
          }
        case 34: break;
        case 22: 
          { return sf.newSymbol("Sqrt", sym.SQRT);
          }
        case 35: break;
        case 25: 
          { return sf.newSymbol("Ceil", sym.CEIL);
          }
        case 36: break;
        case 10: 
          { return sf.newSymbol("Right Bracket", sym.RPAREN);
          }
        case 37: break;
        case 8: 
          { return sf.newSymbol("Number", sym.NUMBER, new Double(yytext()));
          }
        case 38: break;
        case 15: 
          { return sf.newSymbol("Exp", sym.EXP);
          }
        case 39: break;
        case 30: 
          { return sf.newSymbol("Date", sym.DATE_ACTUAL, m_Format.parse(yytext()));
          }
        case 40: break;
        case 7: 
          { return sf.newSymbol("Modulo", sym.MODULO);
          }
        case 41: break;
        case 3: 
          { return sf.newSymbol("Plus", sym.PLUS);
          }
        case 42: break;
        case 13: 
          { return sf.newSymbol("Abs", sym.ABS);
          }
        case 43: break;
        case 31: 
          { return sf.newSymbol("BusinessDay" ,   sym.TIMEAMOUNT, new TimeAmount(Calendar.HOUR, 24, TimeAmount.Note.BUSINESS_DAYS));
          }
        case 44: break;
        case 1: 
          { System.err.println("Illegal character: " + yytext());
          }
        case 45: break;
        case 11: 
          { return sf.newSymbol("Comma", sym.COMMA);
          }
        case 46: break;
        case 18: 
          { return sf.newSymbol("Now",   sym.DATE_ACTUAL, new Date());
          }
        case 47: break;
        case 27: 
          { return sf.newSymbol("Start", sym.DATE_START,  m_Format.parse(BaseDate.INF_PAST_DATE));
          }
        case 48: break;
        case 23: 
          { return sf.newSymbol("Rint", sym.RINT);
          }
        case 49: break;
        case 26: 
          { return sf.newSymbol("Year",   sym.TIMEAMOUNT, new TimeAmount(Calendar.YEAR,      1));
          }
        case 50: break;
        case 5: 
          { return sf.newSymbol("Division", sym.DIVISION);
          }
        case 51: break;
        case 20: 
          { return sf.newSymbol("-INF",  sym.DATE_ACTUAL, m_Format.parse(BaseDate.INF_PAST_DATE));
          }
        case 52: break;
        case 19: 
          { return sf.newSymbol("Day" ,   sym.TIMEAMOUNT, new TimeAmount(Calendar.HOUR,     24));
          }
        case 53: break;
        case 6: 
          { return sf.newSymbol("Power", sym.EXPONENT);
          }
        case 54: break;
        case 4: 
          { return sf.newSymbol("Times", sym.TIMES);
          }
        case 55: break;
        case 28: 
          { return sf.newSymbol("Floor", sym.FLOOR);
          }
        case 56: break;
        case 24: 
          { return sf.newSymbol("Week",   sym.TIMEAMOUNT, new TimeAmount(Calendar.HOUR,   24*7));
          }
        case 57: break;
        case 12: 
          { /* ignore white space. */
          }
        case 58: break;
        case 17: 
          { return sf.newSymbol("Pow", sym.POW);
          }
        case 59: break;
        case 21: 
          { return sf.newSymbol("+INF",  sym.DATE_ACTUAL, m_Format.parse(BaseDate.INF_FUTURE_DATE));
          }
        case 60: break;
        case 2: 
          { return sf.newSymbol("Minus", sym.MINUS);
          }
        case 61: break;
        case 16: 
          { return sf.newSymbol("End",   sym.DATE_END,    m_Format.parse(BaseDate.INF_FUTURE_DATE));
          }
        case 62: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
              {     return sf.newSymbol("EOF",sym.EOF);
 }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
