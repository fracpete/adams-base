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
 * CompareObjectLocations.java
 * Copyright (C) 2019 University of Waikato, Hamilton, NZ
 */

package adams.flow.transformer;

import adams.core.QuickInfoHelper;
import adams.core.io.PlaceholderFile;
import adams.data.image.AbstractImageContainer;
import adams.data.io.input.AbstractReportReader;
import adams.data.io.input.DefaultSimpleReportReader;
import adams.data.report.Report;
import adams.flow.core.Token;
import adams.flow.transformer.compareobjectlocations.AbstractComparison;
import adams.flow.transformer.compareobjectlocations.AbstractComparisonPanel;
import adams.flow.transformer.compareobjectlocations.SideBySide;
import adams.flow.transformer.locateobjects.LocatedObject;
import adams.flow.transformer.locateobjects.LocatedObjects;
import adams.gui.core.BaseButton;
import adams.gui.core.BaseDialog;
import adams.gui.core.BasePanel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

/**
 <!-- globalinfo-start -->
 * Visualizes object locations (annotations and predicted) for the incoming image side-by-side.<br>
 * Only forwards the image container when accepted.
 * <br><br>
 <!-- globalinfo-end -->
 *
 <!-- flow-summary-start -->
 * Input&#47;output:<br>
 * - accepts:<br>
 * &nbsp;&nbsp;&nbsp;adams.data.image.AbstractImageContainer<br>
 * - generates:<br>
 * &nbsp;&nbsp;&nbsp;adams.data.image.AbstractImageContainer<br>
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
 * &nbsp;&nbsp;&nbsp;default: CompareObjectLocations
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
 * &nbsp;&nbsp;&nbsp;If set to true, the flow execution at this level gets stopped in case this
 * &nbsp;&nbsp;&nbsp;actor encounters an error; the error gets propagated; useful for critical
 * &nbsp;&nbsp;&nbsp;actors.
 * &nbsp;&nbsp;&nbsp;default: false
 * </pre>
 *
 * <pre>-silent &lt;boolean&gt; (property: silent)
 * &nbsp;&nbsp;&nbsp;If enabled, then no errors are output in the console; Note: the enclosing
 * &nbsp;&nbsp;&nbsp;actor handler must have this enabled as well.
 * &nbsp;&nbsp;&nbsp;default: false
 * </pre>
 *
 * <pre>-short-title &lt;boolean&gt; (property: shortTitle)
 * &nbsp;&nbsp;&nbsp;If enabled uses just the name for the title instead of the actor's full
 * &nbsp;&nbsp;&nbsp;name.
 * &nbsp;&nbsp;&nbsp;default: false
 * </pre>
 *
 * <pre>-width &lt;int&gt; (property: width)
 * &nbsp;&nbsp;&nbsp;The width of the dialog.
 * &nbsp;&nbsp;&nbsp;default: 800
 * &nbsp;&nbsp;&nbsp;minimum: 1
 * </pre>
 *
 * <pre>-height &lt;int&gt; (property: height)
 * &nbsp;&nbsp;&nbsp;The height of the dialog.
 * &nbsp;&nbsp;&nbsp;default: 600
 * &nbsp;&nbsp;&nbsp;minimum: 1
 * </pre>
 *
 * <pre>-x &lt;int&gt; (property: x)
 * &nbsp;&nbsp;&nbsp;The X position of the dialog (&gt;=0: absolute, -1: left, -2: center, -3: right
 * &nbsp;&nbsp;&nbsp;).
 * &nbsp;&nbsp;&nbsp;default: -1
 * &nbsp;&nbsp;&nbsp;minimum: -3
 * </pre>
 *
 * <pre>-y &lt;int&gt; (property: y)
 * &nbsp;&nbsp;&nbsp;The Y position of the dialog (&gt;=0: absolute, -1: top, -2: center, -3: bottom
 * &nbsp;&nbsp;&nbsp;).
 * &nbsp;&nbsp;&nbsp;default: -1
 * &nbsp;&nbsp;&nbsp;minimum: -3
 * </pre>
 *
 * <pre>-stop-if-canceled &lt;boolean&gt; (property: stopFlowIfCanceled)
 * &nbsp;&nbsp;&nbsp;If enabled, the flow gets stopped in case the user cancels the dialog.
 * &nbsp;&nbsp;&nbsp;default: false
 * </pre>
 *
 * <pre>-custom-stop-message &lt;java.lang.String&gt; (property: customStopMessage)
 * &nbsp;&nbsp;&nbsp;The custom stop message to use in case a user cancelation stops the flow
 * &nbsp;&nbsp;&nbsp;(default is the full name of the actor)
 * &nbsp;&nbsp;&nbsp;default:
 * </pre>
 *
 * <pre>-stop-mode &lt;GLOBAL|STOP_RESTRICTOR&gt; (property: stopMode)
 * &nbsp;&nbsp;&nbsp;The stop mode to use.
 * &nbsp;&nbsp;&nbsp;default: GLOBAL
 * </pre>
 *
 * <pre>-annotations-reader &lt;adams.data.io.input.AbstractReportReader&gt; (property: annotationsReader)
 * &nbsp;&nbsp;&nbsp;The reader to use for loading the annotations (ground truth).
 * &nbsp;&nbsp;&nbsp;default: adams.data.io.input.DefaultSimpleReportReader
 * </pre>
 *
 * <pre>-annotations-file &lt;adams.core.io.PlaceholderFile&gt; (property: annotationsFile)
 * &nbsp;&nbsp;&nbsp;The file containing the annotations.
 * &nbsp;&nbsp;&nbsp;default: ${CWD}
 * </pre>
 *
 * <pre>-annotations-prefix &lt;java.lang.String&gt; (property: annotationsPrefix)
 * &nbsp;&nbsp;&nbsp;The object prefix that the annotations use.
 * &nbsp;&nbsp;&nbsp;default: Object.
 * </pre>
 *
 * <pre>-annotations-label-suffix &lt;java.lang.String&gt; (property: annotationsLabelSuffix)
 * &nbsp;&nbsp;&nbsp;The report suffix that the annotations use for storing the label.
 * &nbsp;&nbsp;&nbsp;default: type
 * </pre>
 *
 * <pre>-predictions-reader &lt;adams.data.io.input.AbstractReportReader&gt; (property: predictionsReader)
 * &nbsp;&nbsp;&nbsp;The reader to use for loading the predictions.
 * &nbsp;&nbsp;&nbsp;default: adams.data.io.input.DefaultSimpleReportReader
 * </pre>
 *
 * <pre>-predictions-file &lt;adams.core.io.PlaceholderFile&gt; (property: predictionsFile)
 * &nbsp;&nbsp;&nbsp;The file containing the predictions.
 * &nbsp;&nbsp;&nbsp;default: ${CWD}
 * </pre>
 *
 * <pre>-predictions-prefix &lt;java.lang.String&gt; (property: predictionsPrefix)
 * &nbsp;&nbsp;&nbsp;The object prefix that the predictions use.
 * &nbsp;&nbsp;&nbsp;default: Object.
 * </pre>
 *
 * <pre>-predictions-label-suffix &lt;java.lang.String&gt; (property: predictionsLabelSuffix)
 * &nbsp;&nbsp;&nbsp;The report suffix that the predictions use for storing the label.
 * &nbsp;&nbsp;&nbsp;default: type
 * </pre>
 *
 * <pre>-comparison &lt;adams.flow.transformer.compareobjectlocations.AbstractComparison&gt; (property: comparison)
 * &nbsp;&nbsp;&nbsp;The comparison view to use.
 * &nbsp;&nbsp;&nbsp;default: adams.flow.transformer.compareobjectlocations.SideBySide -annotations-overlay \"adams.gui.visualization.image.ObjectLocationsOverlayFromReport -type-color-provider adams.gui.visualization.core.DefaultColorProvider\" -predictions-overlay \"adams.gui.visualization.image.ObjectLocationsOverlayFromReport -type-color-provider adams.gui.visualization.core.DefaultColorProvider\"
 * </pre>
 *
 <!-- options-end -->
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class CompareObjectLocations
  extends AbstractInteractiveTransformerDialog {

  private static final long serialVersionUID = 2191236912048968711L;

  /** the report reader for the annotations. */
  protected AbstractReportReader m_AnnotationsReader;

  /** the annotations file to read. */
  protected PlaceholderFile m_AnnotationsFile;

  /** the annotations object prefix. */
  protected String m_AnnotationsPrefix;

  /** the annotations label suffix. */
  protected String m_AnnotationsLabelSuffix;

  /** the report reader for the predictions. */
  protected AbstractReportReader m_PredictionsReader;

  /** the predictions file to read. */
  protected PlaceholderFile m_PredictionsFile;

  /** the predictions object prefix. */
  protected String m_PredictionsPrefix;
  
  /** the predictions label suffix. */
  protected String m_PredictionsLabelSuffix;

  /** the comparison view to use. */
  protected AbstractComparison m_Comparison;

  /** the generated panel. */
  protected AbstractComparisonPanel m_ComparisonPanel;

  /** whether the dialog got accepted. */
  protected boolean m_Accepted;

  /**
   * Returns a string describing the object.
   *
   * @return 			a description suitable for displaying in the gui
   */
  @Override
  public String globalInfo() {
    return "Visualizes object locations (annotations and predicted) for the incoming image side-by-side.\n"
      + "Only forwards the image container when accepted.";
  }

  /**
   * Adds options to the internal list of options.
   */
  @Override
  public void defineOptions() {
    super.defineOptions();

    m_OptionManager.add(
      "annotations-reader", "annotationsReader",
      new DefaultSimpleReportReader());

    m_OptionManager.add(
      "annotations-file", "annotationsFile",
      new PlaceholderFile());

    m_OptionManager.add(
      "annotations-prefix", "annotationsPrefix",
      "Object.");

    m_OptionManager.add(
      "annotations-label-suffix", "annotationsLabelSuffix",
      "type");

    m_OptionManager.add(
      "predictions-reader", "predictionsReader",
      new DefaultSimpleReportReader());

    m_OptionManager.add(
      "predictions-file", "predictionsFile",
      new PlaceholderFile());

    m_OptionManager.add(
      "predictions-prefix", "predictionsPrefix",
      "Object.");

    m_OptionManager.add(
      "predictions-label-suffix", "predictionsLabelSuffix",
      "type");

    m_OptionManager.add(
      "comparison", "comparison",
      new SideBySide());
  }

  /**
   * Returns a quick info about the actor, which will be displayed in the GUI.
   *
   * @return		null if no info available, otherwise short string
   */
  @Override
  public String getQuickInfo() {
    String	result;

    result  = super.getQuickInfo();
    result += QuickInfoHelper.toString(this, "annotationsReader", m_AnnotationsReader, ", ann/reader: ");
    result += QuickInfoHelper.toString(this, "annotationsFile", m_AnnotationsFile, ", ann/file: ");
    result += QuickInfoHelper.toString(this, "predictionsReader", m_PredictionsReader, ", pred/reader: ");
    result += QuickInfoHelper.toString(this, "predictionsFile", m_PredictionsFile, ", pred/file: ");
    result += QuickInfoHelper.toString(this, "comparison", m_Comparison, ", comparison: ");

    return result;
  }

  /**
   * Sets the reader to use for the annotations.
   *
   * @param value 	the reader
   */
  public void setAnnotationsReader(AbstractReportReader value) {
    m_AnnotationsReader = value;
    reset();
  }

  /**
   * Returns the reader to use for the annotations.
   *
   * @return 		the reader
   */
  public AbstractReportReader getAnnotationsReader() {
    return m_AnnotationsReader;
  }

  /**
   * Returns the tip text for this property.
   *
   * @return 		tip text for this property suitable for
   * 			displaying in the GUI or for listing the options.
   */
  public String annotationsReaderTipText() {
    return "The reader to use for loading the annotations (ground truth).";
  }

  /**
   * Sets the annotations file to read.
   *
   * @param value 	the file
   */
  public void setAnnotationsFile(PlaceholderFile value) {
    m_AnnotationsFile = value;
    reset();
  }

  /**
   * Returns the annotations file to read.
   *
   * @return 		the file
   */
  public PlaceholderFile getAnnotationsFile() {
    return m_AnnotationsFile;
  }

  /**
   * Returns the tip text for this property.
   *
   * @return 		tip text for this property suitable for
   * 			displaying in the GUI or for listing the options.
   */
  public String annotationsFileTipText() {
    return "The file containing the annotations.";
  }

  /**
   * Sets the object prefix to use for the annotations.
   *
   * @param value 	the object prefix
   */
  public void setAnnotationsPrefix(String value) {
    m_AnnotationsPrefix = value;
    reset();
  }

  /**
   * Returns the object prefix to use for the annotations.
   *
   * @return 		the object prefix
   */
  public String getAnnotationsPrefix() {
    return m_AnnotationsPrefix;
  }

  /**
   * Returns the tip text for this property.
   *
   * @return 		tip text for this property suitable for
   * 			displaying in the GUI or for listing the options.
   */
  public String annotationsPrefixTipText() {
    return "The object prefix that the annotations use.";
  }

  /**
   * Sets the report suffix that the annotations use for storing the label.
   *
   * @param value 	the suffix
   */
  public void setAnnotationsLabelSuffix(String value) {
    m_AnnotationsLabelSuffix = value;
    reset();
  }

  /**
   * Returns the report suffix that the annotations use for storing the label.
   *
   * @return 		the suffix
   */
  public String getAnnotationsLabelSuffix() {
    return m_AnnotationsLabelSuffix;
  }

  /**
   * Returns the tip text for this property.
   *
   * @return 		tip text for this property suitable for
   * 			displaying in the GUI or for listing the options.
   */
  public String annotationsLabelSuffixTipText() {
    return "The report suffix that the annotations use for storing the label.";
  }

  /**
   * Sets the reader to use for the predictions.
   *
   * @param value 	the reader
   */
  public void setPredictionsReader(AbstractReportReader value) {
    m_PredictionsReader = value;
    reset();
  }

  /**
   * Returns the reader to use for the predictions.
   *
   * @return 		the reader
   */
  public AbstractReportReader getPredictionsReader() {
    return m_PredictionsReader;
  }

  /**
   * Returns the tip text for this property.
   *
   * @return 		tip text for this property suitable for
   * 			displaying in the GUI or for listing the options.
   */
  public String predictionsReaderTipText() {
    return "The reader to use for loading the predictions.";
  }

  /**
   * Sets the predictions file to read.
   *
   * @param value 	the file
   */
  public void setPredictionsFile(PlaceholderFile value) {
    m_PredictionsFile = value;
    reset();
  }

  /**
   * Returns the predictions file to read.
   *
   * @return 		the file
   */
  public PlaceholderFile getPredictionsFile() {
    return m_PredictionsFile;
  }

  /**
   * Returns the tip text for this property.
   *
   * @return 		tip text for this property suitable for
   * 			displaying in the GUI or for listing the options.
   */
  public String predictionsFileTipText() {
    return "The file containing the predictions.";
  }

  /**
   * Sets the object prefix to use for the predictions.
   *
   * @param value 	the object prefix
   */
  public void setPredictionsPrefix(String value) {
    m_PredictionsPrefix = value;
    reset();
  }

  /**
   * Returns the object prefix to use for the predictions.
   *
   * @return 		the object prefix
   */
  public String getPredictionsPrefix() {
    return m_PredictionsPrefix;
  }

  /**
   * Returns the tip text for this property.
   *
   * @return 		tip text for this property suitable for
   * 			displaying in the GUI or for listing the options.
   */
  public String predictionsPrefixTipText() {
    return "The object prefix that the predictions use.";
  }

  /**
   * Sets the report suffix that the predictions use for storing the label.
   *
   * @param value 	the suffix
   */
  public void setPredictionsLabelSuffix(String value) {
    m_PredictionsLabelSuffix = value;
    reset();
  }

  /**
   * Returns the report suffix that the predictions use for storing the label.
   *
   * @return 		the suffix
   */
  public String getPredictionsLabelSuffix() {
    return m_PredictionsLabelSuffix;
  }

  /**
   * Returns the tip text for this property.
   *
   * @return 		tip text for this property suitable for
   * 			displaying in the GUI or for listing the options.
   */
  public String predictionsLabelSuffixTipText() {
    return "The report suffix that the predictions use for storing the label.";
  }

  /**
   * Sets the comparison view to use.
   *
   * @param value 	the comparison
   */
  public void setComparison(AbstractComparison value) {
    m_Comparison = value;
    reset();
  }

  /**
   * Returns the comparison view to use.
   *
   * @return 		the comparison
   */
  public AbstractComparison getComparison() {
    return m_Comparison;
  }

  /**
   * Returns the tip text for this property.
   *
   * @return 		tip text for this property suitable for
   * 			displaying in the GUI or for listing the options.
   */
  public String comparisonTipText() {
    return "The comparison view to use.";
  }

  /**
   * Returns the class that the consumer accepts.
   *
   * @return		the Class of objects that can be processed
   */
  @Override
  public Class[] accepts() {
    return new Class[]{AbstractImageContainer.class};
  }

  /**
   * Returns the class of objects that it generates.
   *
   * @return		the Class of the generated tokens
   */
  @Override
  public Class[] generates() {
    return new Class[]{AbstractImageContainer.class};
  }

  /**
   * Clears the content of the panel.
   */
  @Override
  public void clearPanel() {
    if (m_ComparisonPanel != null)
      m_ComparisonPanel.clearPanel();
  }

  /**
   * Creates the panel to display in the dialog.
   *
   * @return		the panel
   */
  @Override
  protected BasePanel newPanel() {
    m_ComparisonPanel = m_Comparison.generate(this);
    return m_ComparisonPanel;
  }

  /**
   * Hook method after the dialog got created.
   *
   * @param dialog	the dialog that got just created
   * @param panel	the panel displayed in the frame
   */
  protected void postCreateDialog(final BaseDialog dialog, BasePanel panel) {
    BaseButton buttonOK;
    BaseButton	buttonCancel;
    JPanel	panelButtons;

    panelButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    dialog.getContentPane().add(panelButtons, BorderLayout.SOUTH);

    buttonOK = new BaseButton("OK");
    buttonOK.addActionListener((ActionEvent e) -> {
      m_Accepted = true;
      dialog.setVisible(false);
    });
    panelButtons.add(buttonOK);

    buttonCancel = new BaseButton("Cancel");
    buttonCancel.addActionListener((ActionEvent e) -> {
      m_Accepted = false;
      dialog.setVisible(false);
    });
    panelButtons.add(buttonCancel);
  }

  /**
   * Displays the token (the panel and dialog have already been created at
   * this stage).
   *
   * @param token	the token to display
   */
  protected void display(Token token) {
    Report 			annRep;
    LocatedObjects 		annObj;
    Report 			predRep;
    LocatedObjects 		predObj;
    List<Report> 		reports;
    Set<String>			labels;
    List<String>		labelsSorted;

    // read annotations
    try {
      m_AnnotationsReader.setInput(m_AnnotationsFile);
      reports = m_AnnotationsReader.read();
      if (reports.size() != 1)
        getLogger().warning("Expected to find one annotations report, but found: " + reports.size());
      if (reports.size() > 0)
	annRep = reports.get(0);
      else
        annRep = new Report();
    }
    catch (Exception e) {
      annRep = new Report();
      getLogger().log(Level.SEVERE, "Failed to read annotations report '" + m_AnnotationsFile + "'!", e);
    }
    annObj = LocatedObjects.fromReport(annRep, m_AnnotationsPrefix);

    // read predictions
    try {
      m_PredictionsReader.setInput(m_PredictionsFile);
      reports = m_PredictionsReader.read();
      if (reports.size() != 1)
        getLogger().warning("Expected to find one predictions report, but found: " + reports.size());
      if (reports.size() > 0)
	predRep = reports.get(0);
      else
        predRep = new Report();
    }
    catch (Exception e) {
      predRep = new Report();
      getLogger().log(Level.SEVERE, "Failed to read predictions report '" + m_PredictionsFile + "'!", e);
    }
    predObj = LocatedObjects.fromReport(predRep, m_PredictionsPrefix);

    // determine labels
    labels = new HashSet<>();
    for (LocatedObject obj: annObj) {
      if (obj.getMetaData().containsKey(m_AnnotationsLabelSuffix))
	labels.add("" + obj.getMetaData().get(m_AnnotationsLabelSuffix));
    }
    for (LocatedObject obj: predObj) {
      if (obj.getMetaData().containsKey(m_PredictionsLabelSuffix))
	labels.add("" + obj.getMetaData().get(m_PredictionsLabelSuffix));
    }
    labelsSorted = new ArrayList<>(labels);
    Collections.sort(labelsSorted);

    // update GUI
    m_ComparisonPanel.display(token.getPayload(AbstractImageContainer.class), labelsSorted, annRep, annObj, predRep, predObj);
  }

  /**
   * Performs the interaction with the user.
   * <br><br>
   * Default implementation simply displays the dialog and returns always true.
   *
   * @return		true if successfully interacted
   */
  @Override
  public boolean doInteract() {
    m_Accepted = false;

    registerWindow(m_Dialog, m_Dialog.getTitle());
    display(m_InputToken);
    m_Dialog.setVisible(true);
    deregisterWindow(m_Dialog);

    if (m_Accepted)
      m_OutputToken = new Token(m_InputToken.getPayload());

    return m_Accepted;
  }
}