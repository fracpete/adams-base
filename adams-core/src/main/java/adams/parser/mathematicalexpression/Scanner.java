/* The following code was generated by JFlex 1.4.3 on 21/08/20, 11:08 AM */

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
 * Copyright (C) 2008-2020 University of Waikato, Hamilton, New Zealand
 */

package adams.parser.mathematicalexpression;

import java_cup.runtime.SymbolFactory;

import java.io.InputStream;

/**
 * A scanner for mathematical expressions.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */

public class Scanner implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int STRING = 2;
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1, 1
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\60\1\57\1\0\1\60\1\67\22\0\1\60\1\12\1\65"+
    "\1\56\1\0\1\6\1\16\1\55\1\63\1\64\1\3\1\2\1\61"+
    "\1\1\1\52\1\4\1\35\1\34\1\42\7\51\1\0\1\62\1\7"+
    "\1\10\1\11\2\0\1\17\1\30\1\32\1\20\1\24\1\25\1\33"+
    "\1\41\1\40\1\50\1\46\1\26\1\44\1\13\1\14\1\37\1\31"+
    "\1\22\1\27\1\15\1\23\1\50\1\45\1\36\1\43\1\50\1\53"+
    "\1\66\1\54\1\5\1\47\1\0\1\17\1\30\1\32\1\20\1\24"+
    "\1\25\1\33\1\41\1\40\1\50\1\46\1\26\1\44\1\13\1\14"+
    "\1\37\1\31\1\22\1\27\1\15\1\23\1\50\1\45\1\36\1\43"+
    "\1\50\1\0\1\21\uff83\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7"+
    "\1\10\1\11\1\12\1\13\3\14\1\15\2\14\1\16"+
    "\2\14\1\17\5\14\1\20\6\14\3\1\1\21\1\22"+
    "\1\23\1\24\1\25\1\26\1\27\1\30\1\31\1\32"+
    "\1\0\1\33\1\34\1\35\2\14\1\16\32\14\2\0"+
    "\1\14\1\36\2\14\1\37\11\14\1\20\2\0\1\40"+
    "\1\41\1\42\1\43\1\0\1\13\1\44\1\45\1\14"+
    "\1\46\2\14\1\15\1\14\1\47\1\50\5\14\1\51"+
    "\1\52\3\14\1\53\1\54\1\14\1\55\1\14\1\56"+
    "\3\14\1\57\2\14\1\60\2\14\1\0\1\20\1\61"+
    "\1\62\5\14\1\63\4\14\1\64\1\65\1\66\1\14"+
    "\1\67\1\70\1\0\1\14\1\71\1\72\1\73\1\74"+
    "\1\75\1\76\1\14\1\77\4\14\1\100\3\14\1\101"+
    "\2\14\1\102\1\103\3\14\1\104\1\105\1\106\5\14"+
    "\1\107\1\14\1\110\4\14\1\0\1\111\1\112\2\14"+
    "\1\113\1\114\1\115\1\116\1\117\1\120\6\14\1\61"+
    "\3\14\1\121\1\122\1\123\4\14\1\0\4\14\1\55"+
    "\1\124\1\14\1\125\1\126\3\14\1\37\2\14\1\127"+
    "\2\14\1\0\1\130\1\131\7\14\1\132\1\133\1\134"+
    "\1\0\5\14\1\135\1\14\1\136\1\14\1\137\2\14"+
    "\1\114\1\120\2\14\1\140\1\141\1\142\1\143\1\14"+
    "\1\144";

  private static int [] zzUnpackAction() {
    int [] result = new int[285];
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
    "\0\0\0\70\0\160\0\250\0\160\0\160\0\160\0\160"+
    "\0\160\0\340\0\160\0\u0118\0\u0150\0\u0188\0\u01c0\0\u01f8"+
    "\0\160\0\u0230\0\u0268\0\160\0\u02a0\0\u02d8\0\u0310\0\u0348"+
    "\0\u0380\0\u03b8\0\u03f0\0\u0428\0\u0460\0\u0498\0\u04d0\0\u0508"+
    "\0\u0540\0\u0578\0\u05b0\0\u05e8\0\u0620\0\u0658\0\u0690\0\160"+
    "\0\160\0\160\0\160\0\160\0\160\0\u06c8\0\160\0\u0700"+
    "\0\u0738\0\160\0\160\0\160\0\u0770\0\u07a8\0\u03f0\0\u07e0"+
    "\0\u0818\0\u0850\0\u0888\0\u08c0\0\u08f8\0\u0930\0\u0968\0\u09a0"+
    "\0\u09d8\0\u0a10\0\u0a48\0\u0a80\0\u0ab8\0\u0af0\0\u0b28\0\u0b60"+
    "\0\u0b98\0\u0bd0\0\u0c08\0\u0c40\0\u0c78\0\u0cb0\0\u0ce8\0\u0d20"+
    "\0\u0d58\0\u0d90\0\u05e8\0\u0dc8\0\u03f0\0\u0e00\0\u0e38\0\u0e70"+
    "\0\u0ea8\0\u0ee0\0\u0f18\0\u0f50\0\u0f88\0\u0fc0\0\u0ff8\0\u1030"+
    "\0\u1068\0\u10a0\0\u10d8\0\u1110\0\160\0\160\0\160\0\160"+
    "\0\u1148\0\u03f0\0\u03f0\0\u03f0\0\u1180\0\u11b8\0\u11f0\0\u1228"+
    "\0\u03f0\0\u1260\0\u03f0\0\u03f0\0\u1298\0\u12d0\0\u1308\0\u1340"+
    "\0\u1378\0\u03f0\0\u03f0\0\u13b0\0\u13e8\0\u1420\0\u0b28\0\u1458"+
    "\0\u1490\0\u14c8\0\u1500\0\u03f0\0\u1538\0\u1570\0\u15a8\0\u15e0"+
    "\0\u1618\0\u1650\0\u1688\0\u16c0\0\u16f8\0\u1730\0\u1768\0\u17a0"+
    "\0\u0e00\0\u17d8\0\u1810\0\u1848\0\u1880\0\u18b8\0\u03f0\0\u18f0"+
    "\0\u1928\0\u1960\0\u1998\0\u03f0\0\u19d0\0\u03f0\0\u1a08\0\160"+
    "\0\160\0\u1a40\0\u1a78\0\u03f0\0\u03f0\0\u03f0\0\u1ab0\0\u1ae8"+
    "\0\u03f0\0\u1b20\0\u03f0\0\u1b58\0\u1b90\0\u1bc8\0\u1c00\0\u03f0"+
    "\0\u1c38\0\u1c70\0\u1ca8\0\u03f0\0\u1ce0\0\u1d18\0\u03f0\0\u03f0"+
    "\0\u1d50\0\u1d88\0\u1dc0\0\u03f0\0\u03f0\0\u03f0\0\u1df8\0\u1e30"+
    "\0\u1e68\0\u1ea0\0\u1ed8\0\u03f0\0\u1f10\0\u03f0\0\u1f48\0\u1f80"+
    "\0\u1fb8\0\u1ff0\0\u2028\0\u03f0\0\u03f0\0\u2060\0\u2098\0\u03f0"+
    "\0\u20d0\0\u03f0\0\u03f0\0\u03f0\0\u2108\0\u2140\0\u2178\0\u21b0"+
    "\0\u21e8\0\u2220\0\u2258\0\u03f0\0\u2290\0\u22c8\0\u2300\0\u03f0"+
    "\0\u03f0\0\u03f0\0\u2338\0\u2370\0\u23a8\0\u23e0\0\u2418\0\u2450"+
    "\0\u2488\0\u24c0\0\u24f8\0\u03f0\0\u03f0\0\u2530\0\u03f0\0\u03f0"+
    "\0\u2568\0\u25a0\0\u25d8\0\u03f0\0\u2610\0\u2648\0\u03f0\0\u2680"+
    "\0\u26b8\0\u26f0\0\u03f0\0\u2728\0\u2760\0\u2798\0\u27d0\0\u2808"+
    "\0\u2840\0\u2878\0\u28b0\0\u03f0\0\u03f0\0\u03f0\0\u28e8\0\u2920"+
    "\0\u2958\0\u2990\0\u29c8\0\u2a00\0\u03f0\0\u2a38\0\u03f0\0\u2a70"+
    "\0\160\0\u2aa8\0\u2ae0\0\u03f0\0\u03f0\0\u2b18\0\u2b50\0\u03f0"+
    "\0\u03f0\0\u03f0\0\u03f0\0\u2b88\0\u03f0";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[285];
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
    "\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12"+
    "\1\13\1\14\1\15\1\16\1\17\1\20\1\21\1\22"+
    "\1\23\1\24\1\25\1\26\1\27\1\30\1\31\1\32"+
    "\2\33\1\34\1\33\2\35\1\33\1\36\1\37\1\40"+
    "\1\35\1\41\1\42\1\43\1\33\1\3\1\33\1\35"+
    "\1\44\1\45\1\3\1\46\1\47\2\50\1\51\1\52"+
    "\1\53\1\54\1\55\1\3\1\50\57\56\1\0\5\56"+
    "\1\57\1\60\1\3\130\0\1\61\37\0\1\62\1\63"+
    "\66\0\1\64\67\0\1\63\72\0\1\33\1\65\1\33"+
    "\1\0\1\66\1\33\1\0\31\33\30\0\3\33\1\0"+
    "\2\33\1\0\1\67\30\33\30\0\1\33\1\70\1\33"+
    "\1\0\1\71\1\33\1\0\1\72\30\33\30\0\1\73"+
    "\1\33\1\74\1\0\2\33\1\0\6\33\1\75\22\33"+
    "\30\0\3\33\1\0\1\76\1\33\1\0\31\33\30\0"+
    "\3\33\1\0\1\77\1\33\1\0\2\33\1\100\13\33"+
    "\1\101\12\33\30\0\3\33\1\0\2\33\1\0\15\33"+
    "\1\102\13\33\30\0\3\33\1\0\2\33\1\0\14\33"+
    "\1\103\14\33\30\0\3\33\1\0\1\104\1\33\1\0"+
    "\4\33\1\105\11\33\1\106\6\33\1\107\3\33\30\0"+
    "\1\33\1\110\1\33\1\0\2\33\1\0\2\33\1\111"+
    "\26\33\30\0\2\33\1\112\1\0\2\33\1\0\1\33"+
    "\1\113\1\114\4\33\1\115\6\33\1\116\12\33\30\0"+
    "\3\33\1\0\2\33\1\0\31\33\30\0\1\33\1\117"+
    "\1\33\1\0\2\33\1\0\2\33\1\120\3\33\1\121"+
    "\22\33\41\0\1\122\7\0\2\35\4\0\1\35\6\0"+
    "\1\35\1\123\30\0\1\33\1\124\1\33\1\0\2\33"+
    "\1\0\16\33\1\125\6\33\1\126\3\33\30\0\1\127"+
    "\2\33\1\0\2\33\1\0\3\33\1\130\1\33\1\131"+
    "\23\33\30\0\1\33\1\132\1\33\1\0\1\133\1\33"+
    "\1\0\21\33\1\134\7\33\30\0\3\33\1\0\2\33"+
    "\1\0\2\33\1\135\26\33\30\0\1\33\1\136\1\33"+
    "\1\0\1\137\1\33\1\0\16\33\1\140\12\33\30\0"+
    "\3\33\1\0\2\33\1\0\2\33\1\141\26\33\51\0"+
    "\2\142\4\0\1\142\6\0\1\142\16\0\54\143\1\0"+
    "\13\143\55\144\1\0\12\144\57\47\1\0\10\47\57\56"+
    "\1\0\5\56\16\0\1\145\1\0\1\146\4\0\1\147"+
    "\42\0\1\150\15\0\1\151\67\0\2\33\1\152\1\0"+
    "\2\33\1\0\23\33\1\153\5\33\30\0\1\154\2\33"+
    "\1\0\2\33\1\0\31\33\30\0\3\33\1\0\1\33"+
    "\1\155\1\0\31\33\30\0\1\156\2\33\1\0\2\33"+
    "\1\0\31\33\30\0\3\33\1\0\2\33\1\0\1\33"+
    "\1\157\14\33\1\160\12\33\30\0\3\33\1\0\1\33"+
    "\1\161\1\0\31\33\30\0\3\33\1\0\1\162\1\33"+
    "\1\0\31\33\30\0\3\33\1\0\2\33\1\0\5\33"+
    "\1\163\23\33\30\0\3\33\1\0\2\33\1\0\21\33"+
    "\1\164\7\33\30\0\1\165\2\33\1\0\2\33\1\0"+
    "\31\33\30\0\3\33\1\0\2\33\1\0\15\33\1\166"+
    "\13\33\30\0\1\167\2\33\1\0\2\33\1\0\11\33"+
    "\1\170\17\33\30\0\3\33\1\0\2\33\1\0\15\33"+
    "\1\171\13\33\30\0\2\33\1\172\1\0\2\33\1\0"+
    "\15\33\1\173\13\33\30\0\3\33\1\0\2\33\1\0"+
    "\4\33\1\174\24\33\30\0\1\33\1\175\1\33\1\0"+
    "\2\33\1\0\31\33\30\0\1\176\2\33\1\0\2\33"+
    "\1\0\31\33\30\0\3\177\1\0\2\177\1\0\30\177"+
    "\1\33\30\0\3\33\1\0\2\33\1\0\11\33\1\200"+
    "\11\33\1\201\5\33\30\0\1\202\2\33\1\0\2\33"+
    "\1\0\3\33\1\203\25\33\30\0\3\33\1\0\2\33"+
    "\1\0\1\204\30\33\30\0\3\33\1\0\2\33\1\0"+
    "\6\33\1\205\22\33\30\0\3\33\1\0\2\33\1\0"+
    "\10\33\1\206\20\33\30\0\3\33\1\0\2\33\1\0"+
    "\1\207\30\33\30\0\1\210\2\33\1\0\2\33\1\0"+
    "\11\33\1\211\17\33\30\0\1\212\2\33\1\0\2\33"+
    "\1\0\5\33\1\213\23\33\30\0\3\33\1\0\2\33"+
    "\1\0\16\33\1\214\12\33\30\0\3\33\1\0\2\33"+
    "\1\0\1\215\30\33\16\0\1\216\32\0\1\217\5\0"+
    "\1\217\6\0\1\217\31\0\3\33\1\0\2\33\1\0"+
    "\23\33\1\220\5\33\30\0\3\221\1\0\2\221\1\0"+
    "\30\221\1\33\30\0\3\33\1\0\2\33\1\0\3\33"+
    "\1\222\25\33\30\0\3\33\1\0\2\33\1\0\2\33"+
    "\1\223\17\33\1\224\6\33\30\0\1\225\2\33\1\0"+
    "\2\33\1\0\31\33\30\0\3\33\1\0\2\33\1\0"+
    "\1\33\1\226\27\33\30\0\3\33\1\0\2\33\1\0"+
    "\5\33\1\227\23\33\30\0\3\33\1\0\2\33\1\0"+
    "\15\33\1\230\13\33\30\0\3\33\1\0\1\231\1\33"+
    "\1\0\31\33\30\0\1\232\2\33\1\0\2\33\1\0"+
    "\31\33\30\0\2\33\1\233\1\0\2\33\1\0\14\33"+
    "\1\234\14\33\30\0\1\235\2\33\1\0\1\33\1\236"+
    "\1\0\31\33\30\0\3\33\1\0\2\33\1\0\2\33"+
    "\1\237\26\33\41\0\1\122\7\0\2\142\4\0\1\142"+
    "\6\0\1\142\16\0\54\143\1\240\13\143\55\144\1\241"+
    "\12\144\25\0\1\242\55\0\3\33\1\0\1\243\1\33"+
    "\1\0\31\33\30\0\3\33\1\0\2\33\1\0\17\33"+
    "\1\244\11\33\30\0\3\33\1\0\2\33\1\0\2\33"+
    "\1\245\26\33\30\0\3\33\1\0\2\33\1\0\22\33"+
    "\1\246\6\33\30\0\1\247\2\33\1\0\2\33\1\0"+
    "\31\33\30\0\3\33\1\0\1\33\1\250\1\0\31\33"+
    "\30\0\2\33\1\251\1\0\2\33\1\0\4\33\1\252"+
    "\24\33\30\0\2\33\1\253\1\0\2\33\1\0\31\33"+
    "\30\0\3\33\1\0\2\33\1\0\17\33\1\254\11\33"+
    "\30\0\3\33\1\0\2\33\1\0\2\33\1\255\26\33"+
    "\30\0\3\33\1\0\2\33\1\0\5\33\1\256\23\33"+
    "\30\0\1\33\1\257\1\33\1\0\2\33\1\0\31\33"+
    "\30\0\3\33\1\0\1\33\1\260\1\0\31\33\30\0"+
    "\3\33\1\0\2\33\1\0\12\33\1\261\16\33\30\0"+
    "\3\33\1\0\2\33\1\0\2\33\1\262\26\33\30\0"+
    "\3\33\1\0\2\33\1\0\11\33\1\263\17\33\30\0"+
    "\2\33\1\264\1\0\2\33\1\0\31\33\30\0\3\33"+
    "\1\0\2\33\1\0\5\33\1\265\23\33\30\0\1\33"+
    "\1\266\1\33\1\0\2\33\1\0\31\33\30\0\2\33"+
    "\1\267\1\0\2\33\1\0\31\33\30\0\3\33\1\0"+
    "\2\33\1\0\17\33\1\270\11\33\30\0\1\271\2\33"+
    "\1\0\2\33\1\0\31\33\30\0\2\33\1\272\1\0"+
    "\2\33\1\0\10\33\1\273\20\33\30\0\3\33\1\0"+
    "\2\33\1\0\17\33\1\274\11\33\30\0\3\33\1\0"+
    "\2\33\1\0\4\33\1\275\24\33\30\0\2\33\1\276"+
    "\1\0\2\33\1\0\31\33\51\0\1\217\5\0\1\217"+
    "\6\0\1\217\52\0\2\217\4\0\1\217\6\0\1\217"+
    "\31\0\3\33\1\0\2\33\1\0\2\33\1\277\26\33"+
    "\30\0\3\33\1\0\2\33\1\0\16\33\1\300\12\33"+
    "\30\0\3\33\1\0\2\33\1\0\4\33\1\301\24\33"+
    "\30\0\3\33\1\0\2\33\1\0\16\33\1\302\12\33"+
    "\30\0\3\33\1\0\1\303\1\33\1\0\31\33\30\0"+
    "\3\33\1\0\2\33\1\0\1\304\30\33\30\0\1\33"+
    "\1\305\1\33\1\0\2\33\1\0\31\33\30\0\3\33"+
    "\1\0\2\33\1\0\1\306\30\33\30\0\2\33\1\307"+
    "\1\0\2\33\1\0\31\33\30\0\3\33\1\0\2\33"+
    "\1\0\10\33\1\310\20\33\30\0\3\33\1\0\2\33"+
    "\1\0\1\33\1\311\27\33\30\0\3\33\1\0\2\33"+
    "\1\0\24\33\1\312\4\33\55\0\1\313\42\0\3\33"+
    "\1\0\2\33\1\0\21\33\1\314\7\33\30\0\3\33"+
    "\1\0\2\33\1\0\20\33\1\315\10\33\30\0\3\33"+
    "\1\0\2\33\1\0\16\33\1\316\12\33\30\0\3\33"+
    "\1\0\1\317\1\33\1\0\31\33\30\0\2\33\1\320"+
    "\1\0\2\33\1\0\31\33\30\0\3\33\1\0\2\33"+
    "\1\0\1\321\30\33\30\0\3\33\1\0\2\33\1\0"+
    "\2\33\1\322\26\33\30\0\3\33\1\0\2\33\1\0"+
    "\1\323\30\33\30\0\3\33\1\0\2\33\1\0\13\33"+
    "\1\324\15\33\30\0\3\33\1\0\2\33\1\0\1\325"+
    "\30\33\30\0\2\33\1\326\1\0\2\33\1\0\31\33"+
    "\30\0\2\33\1\327\1\0\2\33\1\0\31\33\30\0"+
    "\1\330\2\33\1\0\2\33\1\0\31\33\30\0\3\33"+
    "\1\0\2\33\1\0\1\33\1\331\27\33\30\0\3\33"+
    "\1\0\1\332\1\33\1\0\31\33\30\0\3\33\1\0"+
    "\1\333\1\33\1\0\31\33\30\0\3\33\1\0\2\33"+
    "\1\0\1\334\30\33\30\0\1\335\2\33\1\0\2\33"+
    "\1\0\31\33\30\0\3\33\1\0\2\33\1\0\5\33"+
    "\1\336\23\33\30\0\3\33\1\0\2\33\1\0\5\33"+
    "\1\337\23\33\30\0\1\340\2\33\1\0\2\33\1\0"+
    "\31\33\30\0\2\33\1\341\1\0\2\33\1\0\31\33"+
    "\30\0\3\33\1\0\2\33\1\0\17\33\1\342\11\33"+
    "\30\0\3\33\1\0\2\33\1\0\17\33\1\343\11\33"+
    "\30\0\2\33\1\344\1\0\2\33\1\0\31\33\30\0"+
    "\1\345\2\33\1\0\1\33\1\346\1\0\31\33\30\0"+
    "\1\347\67\0\1\350\2\33\1\0\2\33\1\0\31\33"+
    "\30\0\3\33\1\0\2\33\1\0\10\33\1\351\20\33"+
    "\30\0\3\33\1\0\2\33\1\0\10\33\1\352\20\33"+
    "\30\0\3\33\1\0\2\33\1\0\10\33\1\353\20\33"+
    "\30\0\3\33\1\0\2\33\1\0\17\33\1\354\11\33"+
    "\30\0\3\33\1\0\2\33\1\0\1\355\15\33\1\356"+
    "\12\33\30\0\3\33\1\0\1\33\1\357\1\0\31\33"+
    "\30\0\3\33\1\0\2\33\1\0\22\33\1\360\6\33"+
    "\30\0\3\33\1\0\2\33\1\0\16\33\1\361\12\33"+
    "\30\0\2\33\1\362\1\0\2\33\1\0\31\33\30\0"+
    "\3\33\1\0\2\33\1\0\16\33\1\363\12\33\30\0"+
    "\3\33\1\0\2\33\1\0\2\33\1\364\26\33\30\0"+
    "\3\33\1\0\2\33\1\0\5\33\1\365\23\33\30\0"+
    "\3\33\1\0\2\33\1\0\2\33\1\366\26\33\30\0"+
    "\3\33\1\0\2\33\1\0\2\33\1\367\26\33\30\0"+
    "\3\33\1\0\2\33\1\0\1\33\1\370\27\33\30\0"+
    "\3\33\1\0\1\371\1\33\1\0\31\33\55\0\1\372"+
    "\42\0\2\33\1\373\1\0\2\33\1\0\31\33\30\0"+
    "\3\33\1\0\2\33\1\0\2\33\1\374\26\33\30\0"+
    "\3\33\1\0\1\375\1\33\1\0\31\33\30\0\3\33"+
    "\1\0\1\376\1\33\1\0\31\33\30\0\2\33\1\377"+
    "\1\0\2\33\1\0\31\33\30\0\1\u0100\2\33\1\0"+
    "\2\33\1\0\31\33\30\0\3\33\1\0\2\33\1\0"+
    "\2\33\1\u0101\26\33\30\0\2\33\1\u0102\1\0\2\33"+
    "\1\0\31\33\30\0\3\33\1\0\2\33\1\0\16\33"+
    "\1\u0103\12\33\30\0\3\33\1\0\2\33\1\0\5\33"+
    "\1\u0104\23\33\30\0\3\33\1\0\2\33\1\0\22\33"+
    "\1\u0105\6\33\30\0\3\33\1\0\2\33\1\0\21\33"+
    "\1\u0106\7\33\32\0\1\u0107\65\0\3\33\1\0\1\u0108"+
    "\1\33\1\0\2\33\1\u0109\26\33\30\0\3\33\1\0"+
    "\2\33\1\0\5\33\1\u010a\23\33\30\0\3\33\1\0"+
    "\2\33\1\0\5\33\1\u010b\23\33\30\0\3\33\1\0"+
    "\2\33\1\0\1\33\1\u010c\27\33\30\0\3\33\1\0"+
    "\2\33\1\0\5\33\1\u010d\23\33\30\0\1\u010e\2\33"+
    "\1\0\2\33\1\0\31\33\30\0\3\33\1\0\2\33"+
    "\1\0\21\33\1\u010f\7\33\30\0\1\u0110\2\33\1\0"+
    "\2\33\1\0\31\33\60\0\1\u0111\37\0\3\33\1\0"+
    "\2\33\1\0\4\33\1\u0112\24\33\30\0\3\33\1\0"+
    "\2\33\1\0\14\33\1\u0113\14\33\30\0\3\33\1\0"+
    "\2\33\1\0\2\33\1\u0114\26\33\30\0\3\33\1\0"+
    "\2\33\1\0\2\33\1\u0115\26\33\30\0\2\33\1\u0116"+
    "\1\0\2\33\1\0\31\33\30\0\3\33\1\0\1\u0117"+
    "\1\33\1\0\31\33\30\0\3\33\1\0\2\33\1\0"+
    "\11\33\1\u0118\17\33\30\0\3\33\1\0\2\33\1\0"+
    "\4\33\1\u0119\24\33\30\0\2\33\1\u011a\1\0\2\33"+
    "\1\0\31\33\30\0\3\33\1\0\2\33\1\0\2\33"+
    "\1\u011b\26\33\30\0\2\33\1\u011c\1\0\2\33\1\0"+
    "\31\33\30\0\3\33\1\0\2\33\1\0\2\33\1\u011d"+
    "\26\33\15\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[11200];
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
    "\2\0\1\11\1\1\5\11\1\1\1\11\5\1\1\11"+
    "\2\1\1\11\23\1\6\11\1\1\1\11\1\1\1\0"+
    "\3\11\35\1\2\0\17\1\2\0\4\11\1\0\44\1"+
    "\1\0\21\1\2\11\1\0\50\1\1\0\33\1\1\0"+
    "\22\1\1\0\14\1\1\0\11\1\1\11\14\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[285];
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

  public Scanner(InputStream r, SymbolFactory sf){
    this(r);
    this.sf = sf;
  }
  StringBuilder string = new StringBuilder();


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
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 188) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
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
        case 66: 
          { return sf.newSymbol("Sqrt", sym.SQRT);
          }
        case 101: break;
        case 17: 
          { /* ignore line comments. */
          }
        case 102: break;
        case 37: 
          { return sf.newSymbol("Number", sym.NUMBER, new Double(Double.NaN));
          }
        case 103: break;
        case 12: 
          { return sf.newSymbol("Variable", sym.VARIABLE, new String(yytext()));
          }
        case 104: break;
        case 92: 
          { return sf.newSymbol("WeekDay", sym.WEEKDAY);
          }
        case 105: break;
        case 83: 
          { return sf.newSymbol("Month", sym.MONTH);
          }
        case 106: break;
        case 87: 
          { return sf.newSymbol("Minute", sym.MINUTE);
          }
        case 107: break;
        case 27: 
          { return sf.newSymbol("Less or equal than", sym.LE);
          }
        case 108: break;
        case 29: 
          { return sf.newSymbol("Greater or equal than", sym.GE);
          }
        case 109: break;
        case 10: 
          { return sf.newSymbol("Greater than", sym.GT);
          }
        case 110: break;
        case 36: 
          { return sf.newSymbol("Now", sym.NOW);
          }
        case 111: break;
        case 42: 
          { return sf.newSymbol("Exp", sym.EXP);
          }
        case 112: break;
        case 7: 
          { return sf.newSymbol("Modulo", sym.MODULO);
          }
        case 113: break;
        case 82: 
          { return sf.newSymbol("Hypot", sym.HYPOT);
          }
        case 114: break;
        case 62: 
          { return sf.newSymbol("repeat", sym.REPEAT);
          }
        case 115: break;
        case 69: 
          { return sf.newSymbol("Ceil", sym.CEIL);
          }
        case 116: break;
        case 85: 
          { return sf.newSymbol("Second", sym.SECOND);
          }
        case 117: break;
        case 56: 
          { return sf.newSymbol("Variable", sym.VARIABLE, new String(yytext().replace("'", "").replace("'", "")));
          }
        case 118: break;
        case 77: 
          { return sf.newSymbol("False", sym.FALSE);
          }
        case 119: break;
        case 47: 
          { return sf.newSymbol("Sin", sym.SIN);
          }
        case 120: break;
        case 32: 
          { string.append('\n');
          }
        case 121: break;
        case 52: 
          { return sf.newSymbol("Max", sym.MAX);
          }
        case 122: break;
        case 35: 
          { string.append('\"');
          }
        case 123: break;
        case 18: 
          { /* ignore white space. */
          }
        case 124: break;
        case 6: 
          { return sf.newSymbol("Power", sym.EXPONENT);
          }
        case 125: break;
        case 53: 
          { return sf.newSymbol("Min", sym.MIN);
          }
        case 126: break;
        case 93: 
          { return sf.newSymbol("Contains", sym.CONTAINS);
          }
        case 127: break;
        case 74: 
          { return sf.newSymbol("Atan2", sym.ATAN2);
          }
        case 128: break;
        case 59: 
          { return sf.newSymbol("Trim", sym.TRIM);
          }
        case 129: break;
        case 81: 
          { return sf.newSymbol("IsNaN", sym.ISNAN);
          }
        case 130: break;
        case 70: 
          { return sf.newSymbol("Cbrt", sym.CBRT);
          }
        case 131: break;
        case 39: 
          { return sf.newSymbol("Abs", sym.ABS);
          }
        case 132: break;
        case 73: 
          { return sf.newSymbol("Today", sym.TODAY);
          }
        case 133: break;
        case 25: 
          { yybegin(YYINITIAL);
                  return sf.newSymbol("String", sym.STRING, string.toString());
          }
        case 134: break;
        case 55: 
          { return sf.newSymbol("Variable", sym.VARIABLE, new String(yytext().replace("[", "").replace("]", "")));
          }
        case 135: break;
        case 79: 
          { return sf.newSymbol("Log10", sym.LOG10);
          }
        case 136: break;
        case 34: 
          { string.append('\r');
          }
        case 137: break;
        case 44: 
          { return sf.newSymbol("Log", sym.LOG);
          }
        case 138: break;
        case 23: 
          { string.setLength(0); yybegin(STRING);
          }
        case 139: break;
        case 97: 
          { return sf.newSymbol("ReplaceAll", sym.REPLACEALL);
          }
        case 140: break;
        case 90: 
          { return sf.newSymbol("Matches", sym.MATCHES);
          }
        case 141: break;
        case 46: 
          { return sf.newSymbol("str", sym.STR);
          }
        case 142: break;
        case 54: 
          { return sf.newSymbol("Mid", sym.MID);
          }
        case 143: break;
        case 78: 
          { return sf.newSymbol("Floor", sym.FLOOR);
          }
        case 144: break;
        case 68: 
          { return sf.newSymbol("CosH", sym.COSH);
          }
        case 145: break;
        case 2: 
          { return sf.newSymbol("Minus", sym.MINUS);
          }
        case 146: break;
        case 33: 
          { string.append('\t');
          }
        case 147: break;
        case 86: 
          { return sf.newSymbol("Signum", sym.SIGNUM);
          }
        case 148: break;
        case 19: 
          { return sf.newSymbol("Comma", sym.COMMA);
          }
        case 149: break;
        case 5: 
          { return sf.newSymbol("Division", sym.DIVISION);
          }
        case 150: break;
        case 58: 
          { return sf.newSymbol("True", sym.TRUE);
          }
        case 151: break;
        case 91: 
          { return sf.newSymbol("WeekNum", sym.WEEKNUM);
          }
        case 152: break;
        case 72: 
          { return sf.newSymbol("Year", sym.YEAR);
          }
        case 153: break;
        case 98: 
          { return sf.newSymbol("replaceext", sym.REPLACEEXT);
          }
        case 154: break;
        case 95: 
          { return sf.newSymbol("Number", sym.NUMBER, new Double(Double.NEGATIVE_INFINITY));
          }
        case 155: break;
        case 94: 
          { return sf.newSymbol("Number", sym.NUMBER, new Double(Double.POSITIVE_INFINITY));
          }
        case 156: break;
        case 28: 
          { return sf.newSymbol("Not qquals", sym.NOT_EQ);
          }
        case 157: break;
        case 20: 
          { return sf.newSymbol("Semicolor", sym.SEMICOLON);
          }
        case 158: break;
        case 30: 
          { return sf.newSymbol("pi", sym.PI);
          }
        case 159: break;
        case 24: 
          { string.append(yytext());
          }
        case 160: break;
        case 100: 
          { return sf.newSymbol("Concantenate", sym.CONCATENATE);
          }
        case 161: break;
        case 50: 
          { return sf.newSymbol("Procedure", sym.PROCEDURE, new String(yytext().substring(2)));
          }
        case 162: break;
        case 1: 
          { System.err.println("Illegal character: "+yytext());
          }
        case 163: break;
        case 45: 
          { return sf.newSymbol("Length", sym.LENGTH);
          }
        case 164: break;
        case 3: 
          { return sf.newSymbol("Plus", sym.PLUS);
          }
        case 165: break;
        case 8: 
          { return sf.newSymbol("Less than", sym.LT);
          }
        case 166: break;
        case 99: 
          { return sf.newSymbol("Substitute", sym.SUBSTITUTE);
          }
        case 167: break;
        case 16: 
          { return sf.newSymbol("Number", sym.NUMBER, new Double(yytext()));
          }
        case 168: break;
        case 21: 
          { return sf.newSymbol("Left Bracket", sym.LPAREN);
          }
        case 169: break;
        case 31: 
          { return sf.newSymbol("IfElse", sym.IFELSE);
          }
        case 170: break;
        case 84: 
          { return sf.newSymbol("Substr", sym.SUBSTR);
          }
        case 171: break;
        case 63: 
          { return sf.newSymbol("Rint", sym.RINT);
          }
        case 172: break;
        case 38: 
          { return sf.newSymbol("Tan", sym.TAN);
          }
        case 173: break;
        case 49: 
          { return sf.newSymbol("Pow", sym.POW);
          }
        case 174: break;
        case 51: 
          { return sf.newSymbol("Has", sym.HAS);
          }
        case 175: break;
        case 14: 
          { return sf.newSymbol("Or", sym.OR);
          }
        case 176: break;
        case 57: 
          { return sf.newSymbol("TanH", sym.TANH);
          }
        case 177: break;
        case 88: 
          { return sf.newSymbol("Randint", sym.RANDINT);
          }
        case 178: break;
        case 26: 
          { string.append('\\');
          }
        case 179: break;
        case 40: 
          { return sf.newSymbol("Day", sym.DAY);
          }
        case 180: break;
        case 71: 
          { return sf.newSymbol("Hour", sym.HOUR);
          }
        case 181: break;
        case 89: 
          { return sf.newSymbol("Replace", sym.REPLACE);
          }
        case 182: break;
        case 13: 
          { return sf.newSymbol("And", sym.AND);
          }
        case 183: break;
        case 67: 
          { return sf.newSymbol("SinH", sym.SINH);
          }
        case 184: break;
        case 15: 
          { return sf.newSymbol("e", sym.E);
          }
        case 185: break;
        case 65: 
          { return sf.newSymbol("Left", sym.LEFT);
          }
        case 186: break;
        case 75: 
          { return sf.newSymbol("Right", sym.RIGHT);
          }
        case 187: break;
        case 76: 
          { return sf.newSymbol("UpperCase", sym.UPPERCASE);
          }
        case 188: break;
        case 96: 
          { return sf.newSymbol("IfMissing", sym.IFMISSING);
          }
        case 189: break;
        case 11: 
          { return sf.newSymbol("Not", sym.NOT);
          }
        case 190: break;
        case 43: 
          { return sf.newSymbol("Function", sym.FUNCTION, new String(yytext().substring(2)));
          }
        case 191: break;
        case 61: 
          { return sf.newSymbol("Rand", sym.RAND);
          }
        case 192: break;
        case 22: 
          { return sf.newSymbol("Right Bracket", sym.RPAREN);
          }
        case 193: break;
        case 4: 
          { return sf.newSymbol("Times", sym.TIMES);
          }
        case 194: break;
        case 41: 
          { return sf.newSymbol("ext", sym.EXT);
          }
        case 195: break;
        case 64: 
          { return sf.newSymbol("Find", sym.FIND);
          }
        case 196: break;
        case 60: 
          { return sf.newSymbol("Atan", sym.ATAN);
          }
        case 197: break;
        case 80: 
          { return sf.newSymbol("LowerCase", sym.LOWERCASE);
          }
        case 198: break;
        case 48: 
          { return sf.newSymbol("Cos", sym.COS);
          }
        case 199: break;
        case 9: 
          { return sf.newSymbol("Equals", sym.EQ);
          }
        case 200: break;
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
