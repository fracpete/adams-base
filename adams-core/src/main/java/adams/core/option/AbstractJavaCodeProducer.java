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

/**
 * AbstractJavaCodeProducer.java
 * Copyright (C) 2011-2015 University of Waikato, Hamilton, New Zealand
 */
package adams.core.option;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import adams.core.Utils;
import adams.env.Environment;
import adams.flow.core.AbstractActor;
import adams.flow.core.ActorHandler;

/**
 * Ancestor for producers that generate Java code.
 *
 * @author  fracpete (fracpete at waikato dot ac dot nz)
 * @version $Revision$
 */
public abstract class AbstractJavaCodeProducer
  extends AbstractRecursiveOptionProducerWithOptionHandling<String,String> {

  /** for serialization. */
  private static final long serialVersionUID = 6441039650955464738L;

  /** the buffer for assembling the help. */
  protected StringBuilder m_OutputBuffer;

  /** the copyright string. */
  protected String m_Copyright;

  /** the package of the class. */
  protected String m_Package;

  /** the simple name of the class. */
  protected String m_SimpleName;

  /** whether to output default values as well. */
  protected boolean m_OutputDefaultValues;

  /** the counter for temporary variables. */
  protected int m_TmpCounter;

  /** the indentation level. */
  protected int m_Indentation;

  /**
   * Initializes the output data structure.
   *
   * @return		the created data structure
   */
  @Override
  protected String initOutput() {
    return "";
  }

  /**
   * Initializes the visitor.
   */
  @Override
  protected void initialize() {
    super.initialize();

    m_OutputBuffer = new StringBuilder();
    m_TmpCounter   = 0;
    m_Indentation  = 0;
  }

  /**
   * Adds options to the internal list of options. Derived classes must
   * override this method to add additional options.
   */
  @Override
  public void defineOptions() {
    super.defineOptions();

    m_OptionManager.add(
	"copyright", "copyright",
	"University of Waikato, Hamilton, New Zealand");

    m_OptionManager.add(
      "output-default-values", "outputDefaultValues",
      false);
  }

  /**
   * Sets the copyright entity.
   *
   * @param value	the entity owning the copyright
   */
  public void setCopyright(String value) {
    m_Copyright = value;
    reset();
  }

  /**
   * Returns the copyright entity.
   *
   * @return		the entity owning the copyright
   */
  public String getCopyright() {
    return m_Copyright;
  }

  /**
   * Returns the tip text for this property.
   *
   * @return 		tip text for this property suitable for
   * 			displaying in the GUI or for listing the options.
   */
  public String copyrightTipText() {
    return "The copyright string to add as a comment at the start of the class.";
  }

  /**
   * Sets whether to output default values as well.
   *
   * @param value	if true then default values are output as well
   */
  public void setOutputDefaultValues(boolean value) {
    m_OutputDefaultValues = value;
    reset();
  }

  /**
   * Returns whyether default values are output as well.
   *
   * @return		true if default values are output as well
   */
  public boolean getOutputDefaultValues() {
    return m_OutputDefaultValues;
  }

  /**
   * Returns the tip text for this property.
   *
   * @return 		tip text for this property suitable for
   * 			displaying in the GUI or for listing the options.
   */
  public String outputDefaultValuesTipText() {
    return "Whether to output default values as well or to suppress them.";
  }

  /**
   * Returns the output generated from the visit.
   *
   * @return		the output
   */
  @Override
  public String getOutput() {
    if (m_Output == null)
      m_Output = m_OutputBuffer.toString();

    return m_Output;
  }

  /**
   * Sets the simple name (without package) of the class to generate.
   *
   * @param value	the simple name
   */
  protected void setSimpleName(String value) {
    m_SimpleName = value;
    reset();
  }

  /**
   * Returns the simple name to use.
   *
   * @return		the simple name
   */
  protected String getSimpleName() {
    return m_SimpleName;
  }

  /**
   * Returns the tip text for this property.
   *
   * @return 		tip text for this property suitable for
   * 			displaying in the GUI or for listing the options.
   */
  public String simpleNameTipText() {
    return "The simple name of the class (without package prefix).";
  }

  /**
   * Sets the package name of the class to generate.
   *
   * @param value	the package name
   */
  protected void setPackage(String value) {
    m_Package = value;
    reset();
  }

  /**
   * Returns the package name to use.
   *
   * @return		the package name
   */
  protected String getPackage() {
    return m_Package;
  }

  /**
   * Returns the tip text for this property.
   *
   * @return 		tip text for this property suitable for
   * 			displaying in the GUI or for listing the options.
   */
  public String packageTipText() {
    return "The name of the package.";
  }

  /**
   * The outer most variable name.
   *
   * @return		the variable name
   */
  protected abstract String getOuterVariableName();

  /**
   * Returns the current variable name.
   *
   * @return 		the variable name
   * @see		#getOuterVariableName()
   */
  protected String getCurrentVariable() {
    if (m_Nesting.size() == 0)
      return getOuterVariableName();
    else
      return m_Nesting.peek();
  }

  /**
   * Returns the indentation for code inside the try-catch-block.
   *
   * @return		the indentation string
   */
  protected abstract String getIndentation();

  /**
   * Creates the class name.
   *
   * @param cls		the class to create the class name string for
   * @return		the class name string
   */
  protected String getClassname(Class cls) {
    String	result;
    String	name;

    name = cls.getName().replace("$", ".");

    // primitive?
    if (name.indexOf(".") == -1) {
      if (name.equals("byte"))
	result = "byte";
      else if (name.equals("short"))
	result = "short";
      else if (name.equals("int"))
	result = "Integer";
      else if (name.equals("long"))
	result = "Long";
      else if (name.equals("float"))
	result = "Float";
      else if (name.equals("double"))
	result = "Double";
      else if (name.equals("char"))
	result = "Character";
      else
	throw new IllegalStateException("Unhandled primitive: " + name);
    }
    else {
      result = name;
    }

    return result;
  }

  /**
   * Creates the class name.
   *
   * @param option	the option to create the class name string for
   * @return		the class name string
   */
  protected String getClassname(AbstractArgumentOption option) {
    return getClassname(option.getBaseClass());
  }

  /**
   * Creates the class name.
   *
   * @param obj		the object to create the class name string for
   * @return		the class name string
   */
  protected String getClassname(Object obj) {
    return getClassname(obj.getClass());
  }

  /**
   * Creates the casting string.
   *
   * @param option	the option to create the casting string for
   * @return		the casting string
   */
  protected String getCast(AbstractArgumentOption option) {
    String	result;

    result = getClassname(option);

    if (option.isMultiple())
      result = "(" + result + "[]) ";
    else
      result = "(" + result + ") ";

    return result;
  }

  /**
   * Generates the next name for a temporary variable.
   *
   * @param option	the option to generate the variable for
   * @return		the name of the temp variable
   */
  protected String getNextTmpVariable(AbstractOption option) {
    m_TmpCounter++;
    return option.getProperty().toLowerCase() + m_TmpCounter;
  }

  /**
   * Generates the next name for a temporary variable.
   *
   * @param cls		the class this temp variable is for
   * @return		the name of the temp variable
   */
  protected String getNextTmpVariable(Class cls) {
    m_TmpCounter++;
    return cls.getSimpleName().toLowerCase() + m_TmpCounter;
  }

  /**
   * Visits a boolean option.
   *
   * @param option	the boolean option
   * @return		the last internal data structure that was generated
   */
  @Override
  public String processOption(BooleanOption option) {
    Object		currValue;

    currValue = getCurrentValue(option);
    if (m_OutputDefaultValues || !((Boolean) currValue).equals(option.getDefaultValue())) {
      m_OutputBuffer.append(getIndentation());
      m_OutputBuffer.append(getCurrentVariable());
      m_OutputBuffer.append(".");
      m_OutputBuffer.append(option.getWriteMethod().getName());
      m_OutputBuffer.append("(");
      m_OutputBuffer.append("" + currValue);
      m_OutputBuffer.append(");\n");
      m_OutputBuffer.append("\n");
    }

    return null;
  }

  /**
   * Checks whether a string represents a classname. Simply tries to instantiate
   * a class with that name.
   *
   * @param s		the string to check
   * @return		true if string resembles a class name
   */
  protected boolean isClassName(String s) {
    boolean	result;

    try {
      Class.forName(s);
      result = true;
    }
    catch (Exception e) {
      result = false;
    }

    return result;
  }

  /**
   * Hook method that gets called before indentation is increased.
   * <br>
   * Default implementation does nothing.
   *
   * @param value	the current object
   */
  protected void preIndent(Object value) {
  }

  /**
   * Hook method that gets called just after the indentation got decreased.
   * <br>
   * Default implementation does nothing.
   *
   * @param value	the current object
   */
  protected void postIndent(Object value) {
  }

  /**
   * Processes the value of a class option.
   *
   * @param variable	the name of the tmp variable to use
   * @param value	the actual value
   */
  protected void processClassOption(String variable, Object value) {
    String			options;
    AbstractCommandLineHandler	handler;
    String[]			array;
    String[]			newArray;
    boolean			inc;

    if (value instanceof AbstractActor) {
      m_OutputBuffer.append("\n");
      m_OutputBuffer.append(getIndentation());
      m_OutputBuffer.append("// " + ((AbstractActor) value).getFullName() + "\n");
    }

    m_OutputBuffer.append(getIndentation());
    m_OutputBuffer.append(getClassname(value));
    m_OutputBuffer.append(" ");
    m_OutputBuffer.append(variable);
    m_OutputBuffer.append(" = new " + getClassname(value) + "();\n");

    if (value instanceof OptionHandler) {
      m_Nesting.push(variable);
      if (value instanceof ActorHandler) {
        preIndent(value);
	m_Indentation++;
      }
      doProduce(((OptionHandler) value).getOptionManager());
      if (value instanceof ActorHandler) {
	m_Indentation--;
        postIndent(value);
      }
      m_Nesting.pop();
    }
    else {
      handler = AbstractCommandLineHandler.getHandler(value);
      array   = handler.toArray(value);
      // remove classname
      if ((array.length > 0) && (isClassName(array[0]))) {
	newArray = new String[array.length - 1];
	System.arraycopy(array, 1, newArray, 0, newArray.length);
      }
      else {
	newArray = array;
      }
      options = OptionUtils.joinOptions(newArray).trim();
      if (options.length() > 0) {
	m_OutputBuffer.append(getIndentation());
	m_OutputBuffer.append(variable);
	m_OutputBuffer.append(".setOptions(OptionUtils.splitOptions(\"");
	m_OutputBuffer.append(Utils.backQuoteChars(options));
	m_OutputBuffer.append("\"));\n");
      }
    }
  }

  /**
   * Visits a class option.
   *
   * @param option	the class option
   * @return		the last internal data structure that was generated
   */
  @Override
  public String processOption(ClassOption option) {
    Object		currValue;
    int			i;
    String		tmp;
    String		tmp2;
    Object		value;

    currValue = getCurrentValue(option);
    if (m_OutputDefaultValues || !isDefaultValue(option, currValue) || option.isVariableAttached()) {
      if (option.isVariableAttached()) {
	m_OutputBuffer.append(getIndentation());
	m_OutputBuffer.append("argOption = (ClassOption) " + getCurrentVariable() + ".getOptionManager().findByProperty(\"" + option.getProperty() + "\");\n");
        m_OutputBuffer.append(getIndentation());
	m_OutputBuffer.append("argOption.setVariable(\"" + option.getVariable() + "\");\n");
      }
      else {
	tmp = getNextTmpVariable(option);
	if (option.isMultiple()) {
	  m_OutputBuffer.append(getIndentation());
	  m_OutputBuffer.append(getClassname(option) + "[] " + tmp + " = new " + getClassname(option) + "[" + Array.getLength(currValue) + "];\n");
	  for (i = 0; i < Array.getLength(currValue); i++) {
	    value = Array.get(currValue, i);
	    tmp2  = getNextTmpVariable(value.getClass());

	    processClassOption(tmp2, value);

	    m_OutputBuffer.append(getIndentation());
	    m_OutputBuffer.append(tmp + "[" + i + "] = ");
	    m_OutputBuffer.append(tmp2);
	    m_OutputBuffer.append(";\n");
	  }

	  m_OutputBuffer.append(getIndentation());
	  m_OutputBuffer.append(getCurrentVariable());
	  m_OutputBuffer.append(".");
	  m_OutputBuffer.append(option.getWriteMethod().getName());
	  m_OutputBuffer.append("(" + tmp + ");\n");
	}
	else {
	  value = currValue;
	  tmp2  = getNextTmpVariable(value.getClass());

	  processClassOption(tmp2, value);

	  m_OutputBuffer.append(getIndentation());
	  m_OutputBuffer.append(getCurrentVariable());
	  m_OutputBuffer.append(".");
	  m_OutputBuffer.append(option.getWriteMethod().getName());
	  m_OutputBuffer.append("(");
	  m_OutputBuffer.append(tmp2);
	  m_OutputBuffer.append(");\n");
	}
      }
      m_OutputBuffer.append("\n");
    }

    return null;
  }

  /**
   * Visits an argument option.
   *
   * @param option	the argument option
   * @return		the last internal data structure that was generated
   */
  @Override
  public String processOption(AbstractArgumentOption option) {
    Object		currValue;
    Object		value;
    int			i;
    String		tmp;

    currValue = getCurrentValue(option);
    if (m_OutputDefaultValues || !isDefaultValue(option, currValue) || option.isVariableAttached()) {
      m_OutputBuffer.append(getIndentation());
      m_OutputBuffer.append("argOption = (AbstractArgumentOption) " + getCurrentVariable() + ".getOptionManager().findByProperty(\"" + option.getProperty() + "\");\n");

      if (option.isVariableAttached()) {
	m_OutputBuffer.append(getIndentation());
	m_OutputBuffer.append("argOption.setVariable(\"" + option.getVariable() + "\");\n");
      }
      else {
	tmp = getNextTmpVariable(option);
	if (option.isMultiple()) {
	  m_OutputBuffer.append(getIndentation());
	  m_OutputBuffer.append(getClassname(option) + "[] " + tmp + " = new " + getClassname(option) + "[" + Array.getLength(currValue) + "];\n");
	  for (i = 0; i < Array.getLength(currValue); i++) {
	    value = Array.get(currValue, i);
	    m_OutputBuffer.append(getIndentation());
	    m_OutputBuffer.append(tmp + "[" + i + "] = ");
	    m_OutputBuffer.append("(" + getClassname(option) + ")");
	    m_OutputBuffer.append(" ");
	    m_OutputBuffer.append("argOption.valueOf(\"" + option.toString(value).replace("\"", "\\\"") + "\");\n");
	  }
	  m_OutputBuffer.append(getIndentation());
	  m_OutputBuffer.append(getCurrentVariable());
	  m_OutputBuffer.append(".");
	  m_OutputBuffer.append(option.getWriteMethod().getName());
	  m_OutputBuffer.append("(" + tmp + ");\n");
	}
	else {
	  m_OutputBuffer.append(getIndentation());
	  m_OutputBuffer.append(getCurrentVariable());
	  m_OutputBuffer.append(".");
	  m_OutputBuffer.append(option.getWriteMethod().getName());
	  m_OutputBuffer.append("(");
	  m_OutputBuffer.append(getCast(option));
	  m_OutputBuffer.append("argOption.valueOf(\"" + option.toString(currValue).replace("\"", "\\\"") + "\"));\n");
	}
      }
    }

    return null;
  }

  /**
   * Hook method that gets called just before an option gets produced.
   * <br><br>
   * Default implementation does nothing
   *
   * @param manager	the option manager
   * @param index	the index of the option
   */
  @Override
  protected void preProduce(OptionManager manager, int index) {
    super.preProduce(manager, index);

    m_Output = null;
  }

  /**
   * Returns other necessary imports.
   *
   * @return		the class names
   */
  protected List<String> getRequiredImports() {
    List<String>	result;

    result = new ArrayList<String>();

    result.add("adams.env.Environment");
    result.add("adams.core.option.AbstractArgumentOption");
    result.add("adams.core.option.ClassOption");
    result.add("adams.core.option.OptionUtils");

    return result;
  }

  /**
   * Adds the license preamble.
   */
  protected void addLicensePreamble() {
    m_OutputBuffer.append("/*" + "\n");
    m_OutputBuffer.append(" *   This program is free software: you can redistribute it and/or modify" + "\n");
    m_OutputBuffer.append(" *   it under the terms of the GNU General Public License as published by" + "\n");
    m_OutputBuffer.append(" *   the Free Software Foundation, either version 3 of the License, or" + "\n");
    m_OutputBuffer.append(" *   (at your option) any later version." + "\n");
    m_OutputBuffer.append(" *" + "\n");
    m_OutputBuffer.append(" *   This program is distributed in the hope that it will be useful," + "\n");
    m_OutputBuffer.append(" *   but WITHOUT ANY WARRANTY; without even the implied warranty of" + "\n");
    m_OutputBuffer.append(" *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the" + "\n");
    m_OutputBuffer.append(" *   GNU General Public License for more details." + "\n");
    m_OutputBuffer.append(" *" + "\n");
    m_OutputBuffer.append(" *   You should have received a copy of the GNU General Public License" + "\n");
    m_OutputBuffer.append(" *   along with this program.  If not, see <http://www.gnu.org/licenses/>." + "\n");
    m_OutputBuffer.append(" */" + "\n");
    m_OutputBuffer.append("\n");
  }

  /**
   * Adds the copyright notice.
   */
  protected void addCopyright() {
    SimpleDateFormat	yearFormatter;

    m_OutputBuffer.append("/*" + "\n");
    m_OutputBuffer.append(" * " + m_SimpleName + ".java" + "\n");
    if (m_Copyright.length() > 0) {
      yearFormatter = new SimpleDateFormat("yyyy");
      m_OutputBuffer.append(" * Copyright (C) " + yearFormatter.format(new Date()) + " " + m_Copyright + "\n");
    }
    m_OutputBuffer.append(" */" + "\n");
    m_OutputBuffer.append("\n");
  }

  /**
   * Adds the package for the generated code.
   */
  protected void addPackage() {
    m_OutputBuffer.append("package " + m_Package + ";" + "\n");
    m_OutputBuffer.append("\n");
  }

  /**
   * Adds the imports.
   */
  protected void addImports() {
    List<String>	imports;

    imports = getRequiredImports();
    for (String imp: imports)
      m_OutputBuffer.append("import " + imp + ";" + "\n");
    m_OutputBuffer.append("\n");
  }

  /**
   * Adds the Javadoc for the class.
   */
  protected void addClassJavadoc() {
    m_OutputBuffer.append("/**\n");
    m_OutputBuffer.append(" * Code generated for " + m_SimpleName + "." + "\n");
    m_OutputBuffer.append(" *\n");
    m_OutputBuffer.append(" * @author " + System.getProperty("user.name") + "\n");
    m_OutputBuffer.append(" * @author " + getClass().getName() + " (code generator)" + "\n");
    m_OutputBuffer.append(" * @version $Revision$\n");
    m_OutputBuffer.append(" */\n");
  }

  /**
   * Adds the start of the enclosing "class" statement.
   * <br><br>
   * Default implementation does not derive the class from another superclass.
   */
  protected void addClassStart() {
    m_OutputBuffer.append("public class " + m_SimpleName + " {\n");
    m_OutputBuffer.append("\n");
  }

  /**
   * Adds the constructor.
   * <br><br>
   * Default implementation merely adds a default constructor.
   */
  protected void addConstructor() {
    m_OutputBuffer.append("  /**\n");
    m_OutputBuffer.append("   * Initializes the object.\n");
    m_OutputBuffer.append("   */\n");
    m_OutputBuffer.append("  public " + m_SimpleName + "() {\n");
    m_OutputBuffer.append("    super();\n");
    m_OutputBuffer.append("  }\n");
    m_OutputBuffer.append("\n");
  }

  /**
   * Hook-method for adding additional methods necessary to make the class
   * compile.
   * <br><br>
   * Default implementation adds nothgin.
   */
  protected void addAdditionalMethods() {
  }

  /**
   * Adds the method that encloses the generated code.
   */
  protected abstract void addMethodStart();

  /**
   * Hook-method before starting visiting options.
   */
  @Override
  protected void preProduce() {
    super.preProduce();

    m_TmpCounter   = 0;
    m_OutputBuffer = new StringBuilder();

    addLicensePreamble();
    addCopyright();
    addPackage();
    addImports();
    addClassJavadoc();
    addClassStart();
    addConstructor();
    addAdditionalMethods();
    addMethodStart();
  }

  /**
   * Closes the method with the generated code.
   */
  protected abstract void addMethodEnd();

  /**
   * Hook method for adding a main method.
   * <br><br>
   * Default implementation merely instantiates a new instance of the class.
   */
  protected void addMainMethod() {
    m_OutputBuffer.append("  /**\n");
    m_OutputBuffer.append("   * Only for testing.\n");
    m_OutputBuffer.append("   *\n");
    m_OutputBuffer.append("   * @param args	ignored\n");
    m_OutputBuffer.append("   */\n");
    m_OutputBuffer.append("  public static void main(String[] args) {\n");
    m_OutputBuffer.append("    Environment.setEnvironmentClass(" + Environment.getEnvironmentClass().getName() + ".class);\n");
    m_OutputBuffer.append("    new " + m_SimpleName + "();\n");
    m_OutputBuffer.append("  }\n");
  }

  /**
   * Adds the end of the enclosing "class" statement.
   */
  protected void addClassEnd() {
    m_OutputBuffer.append("}\n");
  }

  /**
   * Hook-method after visiting options.
   */
  @Override
  protected void postProduce() {
    addMethodEnd();
    addMainMethod();
    addClassEnd();
  }

  /**
   * Returns the output generated from the visit.
   *
   * @return		the output, null in case of an error
   */
  @Override
  public String toString() {
    return getOutput();
  }
}
