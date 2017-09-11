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
 * Copyright (C) 2010-2017 University of Waikato, Hamilton, New Zealand
 */

package adams.parser.basedatetime;

import adams.core.DateFormat;
import adams.core.base.BaseDateTime;
import adams.parser.TimeAmount;
import java_cup.runtime.SymbolFactory;

import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

/**
 * A scanner for date/time expressions.
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
     0,  0,  0,  0,  0,  0,  0,  0,  0, 38, 38,  0, 38, 38,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
    33,  0,  0,  0,  0,  6,  0,  0, 35, 36,  3,  2, 37,  1, 31,  4, 
    30, 32, 32, 32, 32, 32, 32, 32, 32, 32, 34,  0,  0,  0,  0,  0, 
     0,  7,  8, 23, 24, 16, 21, 15, 27, 19,  0, 29, 13, 25, 20, 14, 
    18, 10, 11,  9, 12, 26,  0, 22, 17, 28,  0,  0,  0,  0,  5,  0, 
     0,  7,  8, 23, 24, 16, 21, 15, 27, 19,  0, 29, 13, 25, 20, 14, 
    18, 10, 11,  9, 12, 26,  0, 22, 17, 28,  0,  0,  0,  0,  0,  0
  };

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7"+
    "\17\1\1\10\1\1\1\11\1\12\1\13\1\14\26\0"+
    "\1\10\1\0\1\10\2\0\1\15\5\0\1\16\1\17"+
    "\1\20\1\21\1\22\3\0\1\23\5\0\2\10\1\24"+
    "\1\25\1\0\1\26\2\0\1\27\2\0\1\30\1\31"+
    "\2\0\1\32\1\33\1\10\1\0\1\34\1\0\1\21"+
    "\1\35\1\36\2\0\1\10\1\0\1\37\1\40\11\0"+
    "\1\41\10\0\1\42";

  private static int [] zzUnpackAction() {
    int [] result = new int[125];
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
    "\0\0\0\47\0\116\0\165\0\47\0\47\0\47\0\47"+
    "\0\234\0\303\0\352\0\u0111\0\u0138\0\u015f\0\u0186\0\u01ad"+
    "\0\u01d4\0\u01fb\0\u0222\0\u0249\0\u0270\0\u0297\0\u02be\0\u02e5"+
    "\0\u030c\0\47\0\47\0\47\0\47\0\u0333\0\u035a\0\u0381"+
    "\0\u03a8\0\u03cf\0\u03f6\0\u041d\0\u0444\0\u046b\0\u0492\0\u04b9"+
    "\0\u04e0\0\u0507\0\u052e\0\u0555\0\u057c\0\u05a3\0\u05ca\0\u05f1"+
    "\0\u0618\0\u063f\0\u0666\0\u068d\0\u030c\0\u06b4\0\u06db\0\u0702"+
    "\0\47\0\u0729\0\u0750\0\u0777\0\u079e\0\u07c5\0\47\0\47"+
    "\0\47\0\u07ec\0\47\0\u0813\0\u083a\0\u0861\0\47\0\u0888"+
    "\0\u08af\0\u08d6\0\u08fd\0\u0924\0\u094b\0\u0972\0\47\0\47"+
    "\0\u0999\0\47\0\u09c0\0\u09e7\0\47\0\u0a0e\0\u0a35\0\47"+
    "\0\47\0\u0a5c\0\u0a83\0\47\0\47\0\u0aaa\0\u0ad1\0\47"+
    "\0\u0af8\0\47\0\47\0\47\0\u0b1f\0\u0b46\0\u0b6d\0\u0b94"+
    "\0\47\0\47\0\u0bbb\0\u0be2\0\u0c09\0\u0c30\0\u0c57\0\u0c7e"+
    "\0\u0ca5\0\u0ccc\0\u0cf3\0\47\0\u0d1a\0\u0d41\0\u0d68\0\u0d8f"+
    "\0\u0db6\0\u0ddd\0\u0e04\0\u0e2b\0\47";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[125];
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
    "\1\25\1\2\1\26\1\27\1\2\1\30\1\31\1\30"+
    "\1\32\1\2\1\33\1\34\1\35\1\32\72\0\1\36"+
    "\46\0\1\37\33\0\1\40\70\0\1\41\26\0\1\42"+
    "\1\0\1\43\3\0\1\44\51\0\1\45\41\0\1\46"+
    "\51\0\1\47\2\0\1\50\40\0\1\51\46\0\1\52"+
    "\45\0\1\53\51\0\1\54\46\0\1\55\35\0\1\56"+
    "\55\0\1\57\4\0\1\60\41\0\1\61\50\0\1\62"+
    "\46\0\1\63\15\0\1\64\1\65\1\64\44\0\1\66"+
    "\1\0\1\66\32\0\1\67\46\0\1\70\33\0\1\71"+
    "\46\0\1\72\50\0\1\73\42\0\1\74\66\0\1\75"+
    "\43\0\1\76\41\0\1\77\51\0\1\100\54\0\1\101"+
    "\44\0\1\102\46\0\1\103\36\0\1\104\50\0\1\105"+
    "\51\0\1\106\57\0\1\107\36\0\1\110\46\0\1\111"+
    "\54\0\1\112\23\0\1\113\40\0\1\114\36\0\1\115"+
    "\26\0\1\63\15\0\1\116\1\65\1\116\26\0\1\63"+
    "\15\0\1\66\1\0\1\66\33\0\1\117\46\0\1\120"+
    "\44\0\1\121\37\0\1\122\45\0\1\123\51\0\1\124"+
    "\44\0\1\125\52\0\1\126\44\0\1\127\65\0\1\130"+
    "\26\0\1\131\45\0\1\132\64\0\1\133\27\0\1\134"+
    "\46\0\1\135\73\0\1\115\44\0\1\115\1\0\1\115"+
    "\26\0\1\63\15\0\1\136\1\65\1\136\32\0\1\137"+
    "\36\0\1\140\56\0\1\141\35\0\1\142\46\0\1\143"+
    "\66\0\1\144\27\0\1\145\33\0\1\146\16\0\1\63"+
    "\15\0\1\147\1\65\1\147\26\0\1\150\56\0\1\151"+
    "\36\0\1\152\64\0\1\153\1\0\1\153\26\0\1\63"+
    "\15\0\1\147\1\65\1\147\17\0\1\154\73\0\1\155"+
    "\1\0\1\155\17\0\1\156\36\0\1\157\75\0\1\160"+
    "\54\0\1\161\1\0\1\161\15\0\1\162\75\0\1\163"+
    "\1\0\1\163\42\0\1\164\53\0\1\165\43\0\1\166"+
    "\1\0\1\166\44\0\1\167\1\0\1\167\50\0\1\170"+
    "\42\0\1\171\1\0\1\171\44\0\1\172\1\0\1\172"+
    "\50\0\1\173\42\0\1\174\1\0\1\174\44\0\1\175"+
    "\1\0\1\175\6\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[3666];
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
    "\1\0\1\11\2\1\4\11\21\1\4\11\26\0\1\1"+
    "\1\0\1\1\2\0\1\11\5\0\3\11\1\1\1\11"+
    "\3\0\1\11\5\0\2\1\2\11\1\0\1\11\2\0"+
    "\1\11\2\0\2\11\2\0\2\11\1\1\1\0\1\11"+
    "\1\0\3\11\2\0\1\1\1\0\2\11\11\0\1\11"+
    "\10\0\1\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[125];
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
    m_Format = new DateFormat(BaseDateTime.FORMAT);
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
        case 30: 
          { return sf.newSymbol("Month",  sym.TIMEAMOUNT, new TimeAmount(Calendar.MONTH,     1));
          }
        case 35: break;
        case 14: 
          { return sf.newSymbol("Log", sym.LOG);
          }
        case 36: break;
        case 10: 
          { return sf.newSymbol("Left Bracket", sym.LPAREN);
          }
        case 37: break;
        case 22: 
          { return sf.newSymbol("Sqrt", sym.SQRT);
          }
        case 38: break;
        case 25: 
          { return sf.newSymbol("Ceil", sym.CEIL);
          }
        case 39: break;
        case 20: 
          { return sf.newSymbol("-INF", sym.DATE_ACTUAL, m_Format.parse(BaseDateTime.INF_PAST_DATE));
          }
        case 40: break;
        case 11: 
          { return sf.newSymbol("Right Bracket", sym.RPAREN);
          }
        case 41: break;
        case 8: 
          { return sf.newSymbol("Number", sym.NUMBER, new Double(yytext()));
          }
        case 42: break;
        case 15: 
          { return sf.newSymbol("Exp", sym.EXP);
          }
        case 43: break;
        case 34: 
          { return sf.newSymbol("Date", sym.DATE_ACTUAL, m_Format.parse(yytext()));
          }
        case 44: break;
        case 7: 
          { return sf.newSymbol("Modulo", sym.MODULO);
          }
        case 45: break;
        case 3: 
          { return sf.newSymbol("Plus", sym.PLUS);
          }
        case 46: break;
        case 18: 
          { return sf.newSymbol("Now",  sym.DATE_ACTUAL, new Date());
          }
        case 47: break;
        case 13: 
          { return sf.newSymbol("Abs", sym.ABS);
          }
        case 48: break;
        case 26: 
          { return sf.newSymbol("Hour",   sym.TIMEAMOUNT, new TimeAmount(Calendar.HOUR,      1));
          }
        case 49: break;
        case 33: 
          { return sf.newSymbol("BusinessDay" ,   sym.TIMEAMOUNT, new TimeAmount(Calendar.HOUR, 24, TimeAmount.Note.BUSINESS_DAYS));
          }
        case 50: break;
        case 1: 
          { System.err.println("Illegal character: " + yytext());
          }
        case 51: break;
        case 12: 
          { return sf.newSymbol("Comma", sym.COMMA);
          }
        case 52: break;
        case 32: 
          { return sf.newSymbol("Minute", sym.TIMEAMOUNT, new TimeAmount(Calendar.MINUTE,    1));
          }
        case 53: break;
        case 23: 
          { return sf.newSymbol("Rint", sym.RINT);
          }
        case 54: break;
        case 16: 
          { return sf.newSymbol("End",   sym.DATE_END,    m_Format.parse(BaseDateTime.INF_FUTURE_DATE));
          }
        case 55: break;
        case 28: 
          { return sf.newSymbol("Start", sym.DATE_START,  m_Format.parse(BaseDateTime.INF_PAST_DATE));
          }
        case 56: break;
        case 21: 
          { return sf.newSymbol("+INF", sym.DATE_ACTUAL, m_Format.parse(BaseDateTime.INF_FUTURE_DATE));
          }
        case 57: break;
        case 27: 
          { return sf.newSymbol("Year",   sym.TIMEAMOUNT, new TimeAmount(Calendar.YEAR,      1));
          }
        case 58: break;
        case 5: 
          { return sf.newSymbol("Division", sym.DIVISION);
          }
        case 59: break;
        case 19: 
          { return sf.newSymbol("Day" ,   sym.TIMEAMOUNT, new TimeAmount(Calendar.HOUR,     24));
          }
        case 60: break;
        case 6: 
          { return sf.newSymbol("Power", sym.EXPONENT);
          }
        case 61: break;
        case 4: 
          { return sf.newSymbol("Times", sym.TIMES);
          }
        case 62: break;
        case 29: 
          { return sf.newSymbol("Floor", sym.FLOOR);
          }
        case 63: break;
        case 24: 
          { return sf.newSymbol("Week",   sym.TIMEAMOUNT, new TimeAmount(Calendar.HOUR,   24*7));
          }
        case 64: break;
        case 9: 
          { /* ignore white space. */
          }
        case 65: break;
        case 17: 
          { return sf.newSymbol("Pow", sym.POW);
          }
        case 66: break;
        case 31: 
          { return sf.newSymbol("Second", sym.TIMEAMOUNT, new TimeAmount(Calendar.SECOND,    1));
          }
        case 67: break;
        case 2: 
          { return sf.newSymbol("Minus", sym.MINUS);
          }
        case 68: break;
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
