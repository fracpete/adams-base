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
 * ConvertFeatures.java
 * Copyright (C) 2014 University of Waikato, Hamilton, New Zealand
 */

package adams.flow.transformer;

import java.util.ArrayList;
import java.util.List;

import adams.core.QuickInfoHelper;
import adams.data.featureconverter.AbstractFeatureConverter;
import adams.data.featureconverter.HeaderDefinition;
import adams.data.featureconverter.Text;
import adams.flow.container.FeatureConverterContainer;
import adams.flow.core.Token;

/**
 <!-- globalinfo-start -->
 * Turns the raw features generated by a feature converter (stored in a adams.flow.container.FeatureConverterContainer) into a specific format using the user-defined feature converter.<br>
 * If an array of containers is received, the get merged first, i.e., placed side-by-side.
 * <br><br>
 <!-- globalinfo-end -->
 *
 <!-- flow-summary-start -->
 * Input&#47;output:<br>
 * - accepts:<br>
 * &nbsp;&nbsp;&nbsp;adams.flow.container.FeatureConverterContainer<br>
 * &nbsp;&nbsp;&nbsp;adams.flow.container.FeatureConverterContainer[]<br>
 * - generates:<br>
 * &nbsp;&nbsp;&nbsp;java.lang.String<br>
 * <br><br>
 * Container information:<br>
 * - adams.flow.container.FeatureConverterContainer: Header, Row
 * <br><br>
 <!-- flow-summary-end -->
 *
 <!-- options-start -->
 * <pre>-logging-level &lt;OFF|SEVERE|WARNING|INFO|CONFIG|FINE|FINER|FINEST&gt; (property: loggingLevel)
 * &nbsp;&nbsp;&nbsp;The logging level for outputting errors and debugging output.
 * &nbsp;&nbsp;&nbsp;default: WARNING
 * </pre>
 * 
 * <pre>-name &lt;java.lang.String&gt; (property: name)
 * &nbsp;&nbsp;&nbsp;The name of the actor.
 * &nbsp;&nbsp;&nbsp;default: ConvertFeatures
 * </pre>
 * 
 * <pre>-annotation &lt;adams.core.base.BaseAnnotation&gt; (property: annotations)
 * &nbsp;&nbsp;&nbsp;The annotations to attach to this actor.
 * &nbsp;&nbsp;&nbsp;default: 
 * </pre>
 * 
 * <pre>-skip &lt;boolean&gt; (property: skip)
 * &nbsp;&nbsp;&nbsp;If set to true, transformation is skipped and the input token is just forwarded 
 * &nbsp;&nbsp;&nbsp;as it is.
 * &nbsp;&nbsp;&nbsp;default: false
 * </pre>
 * 
 * <pre>-stop-flow-on-error &lt;boolean&gt; (property: stopFlowOnError)
 * &nbsp;&nbsp;&nbsp;If set to true, the flow gets stopped in case this actor encounters an error;
 * &nbsp;&nbsp;&nbsp; useful for critical actors.
 * &nbsp;&nbsp;&nbsp;default: false
 * </pre>
 * 
 * <pre>-silent &lt;boolean&gt; (property: silent)
 * &nbsp;&nbsp;&nbsp;If enabled, then no errors are output in the console.
 * &nbsp;&nbsp;&nbsp;default: false
 * </pre>
 * 
 * <pre>-converter &lt;adams.data.featureconverter.AbstractFeatureConverter&gt; (property: converter)
 * &nbsp;&nbsp;&nbsp;The converter to use for turning the raw features into a specific format.
 * &nbsp;&nbsp;&nbsp;default: adams.data.featureconverter.Text
 * </pre>
 * 
 * <pre>-merge-format &lt;java.lang.String&gt; (property: mergeFormat)
 * &nbsp;&nbsp;&nbsp;The format to use for merging multiple feature containers before converting 
 * &nbsp;&nbsp;&nbsp;them; use '&#64;' for the dataset name, '#' for the 1-based array index and '
 * &nbsp;&nbsp;&nbsp;$' for the feature name.
 * &nbsp;&nbsp;&nbsp;default: &#64;-$
 * </pre>
 * 
 * <pre>-output-type &lt;HEADER|ROW|BOTH&gt; (property: outputType)
 * &nbsp;&nbsp;&nbsp;What data to generate; in case of BOTH an array with HEADER and ROW is output.
 * &nbsp;&nbsp;&nbsp;default: HEADER
 * </pre>
 * 
 <!-- options-end -->
 *
 * @author  fracpete (fracpete at waikato dot ac dot nz)
 * @version $Revision: 7330 $
 */
public class ConvertFeatures
  extends AbstractTransformer {

  /** for serialization. */
  private static final long serialVersionUID = 7491100983182267771L;

  /**
   * Defines what to output.
   *
   * @author  fracpete (fracpete at waikato dot ac dot nz)
   * @version $Revision: 7330 $
   */
  public enum OutputType {
    HEADER,
    ROW,
    BOTH
  }
  
  /** the feature converter to use. */
  protected AbstractFeatureConverter m_Converter;
  
  /** the format to use for merging multiple feature sets. */
  protected String m_MergeFormat;

  /** whether to output the header of the row. */
  protected OutputType m_OutputType;
  
  /**
   * Returns a string describing the object.
   *
   * @return 			a description suitable for displaying in the gui
   */
  @Override
  public String globalInfo() {
    return 
	"Turns the raw features generated by a feature converter (stored "
	+ "in a " + FeatureConverterContainer.class.getName() + ") "
	+ "into a specific format using the user-defined feature converter.\n"
	+ "If an array of containers is received, the get merged first, i.e., "
	+ "placed side-by-side.";
  }

  /**
   * Adds options to the internal list of options.
   */
  @Override
  public void defineOptions() {
    super.defineOptions();

    m_OptionManager.add(
	    "converter", "converter",
	    new Text());

    m_OptionManager.add(
	    "merge-format", "mergeFormat",
	    "@-$");

    m_OptionManager.add(
	    "output-type", "outputType",
	    OutputType.HEADER);
  }

  /**
   * Sets the feature converter to use.
   *
   * @param value	the converter
   */
  public void setConverter(AbstractFeatureConverter value) {
    m_Converter = value;
    reset();
  }

  /**
   * Returns the feature converter in use.
   *
   * @return		the converter
   */
  public AbstractFeatureConverter getConverter() {
    return m_Converter;
  }

  /**
   * Returns the tip text for this property.
   *
   * @return 		tip text for this property suitable for
   * 			displaying in the GUI or for listing the options.
   */
  public String converterTipText() {
    return "The converter to use for turning the raw features into a specific format.";
  }

  /**
   * Sets the format to use for merging multiple feature containers.
   *
   * @param value	the format
   */
  public void setMergeFormat(String value) {
    if ((value != null) && (value.trim().length() > 0)) {
      m_MergeFormat = value;
      reset();
    }
    else {
      getLogger().warning("Merge format cannot be null or empty!");
    }
  }

  /**
   * Returns the format in use for merging multiple feature containers.
   *
   * @return		the format
   */
  public String getMergeFormat() {
    return m_MergeFormat;
  }

  /**
   * Returns the tip text for this property.
   *
   * @return 		tip text for this property suitable for
   * 			displaying in the GUI or for listing the options.
   */
  public String mergeFormatTipText() {
    return 
	"The format to use for merging multiple feature containers before "
	+ "converting them; use '@' for the dataset name, '#' for the 1-based "
	+ "array index and '$' for the feature name.";
  }

  /**
   * Sets the type of output to generate.
   *
   * @param value	the output type
   */
  public void setOutputType(OutputType value) {
    m_OutputType = value;
    reset();
  }

  /**
   * Returns the type of output to generate.
   *
   * @return		the output type
   */
  public OutputType getOutputType() {
    return m_OutputType;
  }

  /**
   * Returns the tip text for this property.
   *
   * @return 		tip text for this property suitable for
   * 			displaying in the GUI or for listing the options.
   */
  public String outputTypeTipText() {
    return 
	"What data to generate; in case of " + OutputType.BOTH + " an array with " 
	+ OutputType.HEADER + " and " + OutputType.ROW + " is output.";
  }

  /**
   * Returns a quick info about the object, which can be displayed in the GUI.
   *
   * @return		null if no info available, otherwise short string
   */
  @Override
  public String getQuickInfo() {
    String	result;
    
    result  = QuickInfoHelper.toString(this, "converter", m_Converter, "converter: ");
    result += QuickInfoHelper.toString(this, "mergeFormat", m_MergeFormat, ", format: ");
    result += QuickInfoHelper.toString(this, "outputType", m_OutputType, ", output: ");
    
    return result;
  }

  /**
   * Returns the class that the consumer accepts.
   *
   * @return		<!-- flow-accepts-start -->adams.flow.container.FeatureConverterContainer.class, adams.flow.container.FeatureConverterContainer[].class<!-- flow-accepts-end -->
   */
  @Override
  public Class[] accepts() {
    return new Class[]{FeatureConverterContainer.class, FeatureConverterContainer[].class};
  }

  /**
   * Returns the class of objects that it generates.
   *
   * @return		<!-- flow-generates-start -->java.lang.String.class<!-- flow-generates-end -->
   */
  @Override
  public Class[] generates() {
    switch (m_OutputType) {
      case HEADER:
	return new Class[]{m_Converter.getDatasetFormat()};
      case ROW:
	return new Class[]{m_Converter.getRowFormat()};
      case BOTH:
	return new Class[]{Object[].class};
      default:
	throw new IllegalStateException("Unhandled output type: " + m_OutputType);
    }
  }

  /**
   * Executes the flow item.
   *
   * @return		null if everything is fine, otherwise error message
   */
  @Override
  protected String doExecute() {
    String			result;
    FeatureConverterContainer	cont;
    FeatureConverterContainer[]	conts;
    HeaderDefinition		header;
    List			row;
    HeaderDefinition		headerNew;
    List			rowNew;
    int				i;
    int				n;
    String			name;

    result = null;

    headerNew = null;
    rowNew    = null;
    
    if (m_InputToken.getPayload() instanceof FeatureConverterContainer) {
      cont      = (FeatureConverterContainer) m_InputToken.getPayload();
      headerNew = (HeaderDefinition) cont.getValue(FeatureConverterContainer.VALUE_HEADER);
      rowNew    = (List) cont.getValue(FeatureConverterContainer.VALUE_ROW);
    }
    else {
      conts     = (FeatureConverterContainer[]) m_InputToken.getPayload();
      headerNew = new HeaderDefinition();
      rowNew    = new ArrayList();
      for (i = 0; i < conts.length; i++) {
	header = (HeaderDefinition) conts[i].getValue(FeatureConverterContainer.VALUE_HEADER);
	row    = (List) conts[i].getValue(FeatureConverterContainer.VALUE_ROW);
	// header
	for (n = 0; n < header.size(); n++) {
	  name = m_MergeFormat;
	  name = name.replace("@", header.getDataset());
	  name = name.replace("#", "" + (i+1));
	  name = name.replace("$", header.getName(n));
	  headerNew.add(name, header.getType(n));
	}
	// row
	if (rowNew != null) {
	  // can only merge if *all* rows are non-null
	  if (row == null)
	    rowNew = null;
	  else
	    rowNew.addAll(row);
	}
      }
    }

    switch (m_OutputType) {
      case HEADER:
	m_OutputToken = new Token(m_Converter.generateHeader(headerNew));
	break;
      case ROW:
	if (rowNew != null) {
	  if (m_Converter.getHeaderDefinition() == null)
	    m_Converter.generateHeader(headerNew);
	  m_OutputToken = new Token(m_Converter.generateRow(rowNew));
	}
	break;
      case BOTH:
	m_OutputToken = new Token(
	    new Object[]{
		m_Converter.generateHeader(headerNew),
		(rowNew == null) ? null : m_Converter.generateRow(rowNew)
	    });
	break;
      default:
	throw new IllegalStateException("Unhandled output type: " + m_OutputType);
    }

    return result;
  }
}
