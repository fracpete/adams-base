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
 * OverlayLayer.java
 * Copyright (C) 2020 University of Waikato, Hamilton, NZ
 */

package adams.gui.visualization.segmentation.layer;

import adams.core.ClassLister;
import adams.core.Utils;
import adams.core.base.BaseFloat;
import adams.data.image.BufferedImageHelper;
import adams.gui.core.BaseColorTextField;
import adams.gui.core.BaseFlatButton;
import adams.gui.core.BaseFlatButtonWithDropDownMenu;
import adams.gui.core.BaseObjectTextField;
import adams.gui.core.ColorHelper;
import adams.gui.core.ConsolePanel;
import adams.gui.core.Fonts;
import adams.gui.core.GUIHelper;
import adams.gui.visualization.segmentation.layer.overlaylayeraction.AbstractOverlayLayerAction;

import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

/**
 * Class for overlay layers.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class OverlayLayer
  extends AbstractImageLayer {

  private static final long serialVersionUID = 7829707838665930430L;

  /**
   * For storing the state of an overlay layer.
   */
  public static class OverlayLayerState
    extends AbstractImageLayerState {

    private static final long serialVersionUID = -5652014216527524598L;

    /** the color. */
    public Color color;

    /** the alpha value. */
    public float alpha;

    /** whether active. */
    public boolean active;
  }

  /** The alpha value in use. */
  protected BaseObjectTextField<BaseFloat> m_TextAlpha;

  /** The color to use. */
  protected BaseColorTextField m_TextColor;

  /** the button for applying the values. */
  protected BaseFlatButton m_ButtonApply;

  /** the button for the action drop. */
  protected BaseFlatButtonWithDropDownMenu m_ButtonActions;

  /** the button for removing the layer. */
  protected BaseFlatButton m_ButtonRemove;

  /** the button for activating, showing the name. */
  protected BaseFlatButton m_ButtonActivate;

  /** whether the layer is active. */
  protected boolean m_Active;

  /**
   * Initializes the members.
   */
  @Override
  protected void initialize() {
    super.initialize();

    m_Active = false;
  }

  /**
   * Initializes the widgets.
   */
  @Override
  protected void initGUI() {
    JPanel	panelRow;
    JMenuItem	menuitem;

    super.initGUI();

    setLayout(new GridLayout(0, 1));

    panelRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
    add(panelRow);
    panelRow.add(m_CheckboxEnabled);
    m_ButtonActivate = new BaseFlatButton("");
    m_ButtonActivate.setToolTipText("Activate layer");
    m_ButtonActivate.addActionListener((ActionEvent e) -> getManager().activate(this));
    panelRow.add(m_ButtonActivate);
    m_ButtonRemove = new BaseFlatButton(GUIHelper.getIcon("delete.gif"));
    m_ButtonRemove.setVisible(false);
    m_ButtonRemove.setToolTipText("Remove layer");
    m_ButtonRemove.addActionListener((ActionEvent e) -> {
      getManager().removeOverlay(getName());
      update();
    });
    panelRow.add(m_ButtonRemove);
    m_ButtonActions = new BaseFlatButtonWithDropDownMenu();
    m_ButtonActions.setVisible(false);
    for (Class cls: ClassLister.getSingleton().getClasses(AbstractOverlayLayerAction.class)) {
      try {
        final AbstractOverlayLayerAction action = (AbstractOverlayLayerAction) cls.newInstance();
        menuitem = new JMenuItem(action.getName());
        menuitem.setIcon(action.getIcon());
        menuitem.addActionListener((ActionEvent e) -> action.performAction(OverlayLayer.this));
	Fonts.usePlain(menuitem);
        m_ButtonActions.addToMenu(menuitem);
      }
      catch (Exception e) {
        ConsolePanel.getSingleton().append("Failed to instantiate overlay layer action: " + Utils.classToString(cls), e);
      }
    }
    m_ButtonActions.setToolTipText("Click to select action");
    panelRow.add(m_ButtonActions);

    panelRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
    add(panelRow);
    m_TextAlpha = new BaseObjectTextField<>(new BaseFloat(0.5f));
    m_TextAlpha.setColumns(4);
    m_TextAlpha.setToolTipText("fully transparent=0.0, fully opaque=1.0");
    panelRow.add(m_TextAlpha);
    m_TextColor = new BaseColorTextField(Color.RED);
    m_TextColor.setColumns(7);
    m_TextColor.setToolTipText("The color to use for the overlay");
    panelRow.add(m_TextColor);
    m_ButtonApply = new BaseFlatButton(GUIHelper.getIcon("validate.png"));
    m_ButtonApply.setToolTipText("Apply current values");
    m_ButtonApply.addActionListener((ActionEvent e) -> {
      updateImage();
      update();
    });
    panelRow.add(m_ButtonApply);
  }

  /**
   * Sets the image to display.
   *
   * @param value	the image, null to clear
   */
  @Override
  public void setImage(BufferedImage value) {
    super.setImage(value);
    updateImage();
  }

  /**
   * Sets the name of the layer.
   *
   * @param value	the name
   */
  public void setName(String value) {
    m_ButtonActivate.setText(value);
  }

  /**
   * Returns the name of the layer.
   *
   * @return		the name
   */
  @Override
  public String getName() {
    return m_ButtonActivate.getText();
  }

  /**
   * Sets the alpha value to use for transparency.
   *
   * @param value	the alpha value
   */
  public void setAlpha(float value) {
    m_TextAlpha.setText("" + value);
  }

  /**
   * Returns the alpha value for transparency.
   *
   * @return		the alpha value
   */
  public float getAlpha() {
    return m_TextAlpha.getObject().floatValue();
  }

  /**
   * Sets the color value to use for the layer.
   *
   * @param value	the color value
   */
  public void setColor(Color value) {
    m_TextColor.setText(ColorHelper.toHex(value));
    updateImage();
  }

  /**
   * Returns the color value for the layer.
   *
   * @return		the color value
   */
  public Color getColor() {
    return m_TextColor.getObject().toColorValue();
  }

  /**
   * Sets the active state of the layer.
   *
   * @param value	true if active
   */
  public void setActive(boolean value) {
    m_Active = value;
    if (m_Active)
      setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    else
      setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
  }

  /**
   * Returns the active state of the layer.
   *
   * @return		true if active
   */
  public boolean isActive() {
    return m_Active;
  }

  /**
   * Returns whether the layer can be removed.
   *
   * @return		true if can be removed
   */
  @Override
  public boolean isRemovable() {
    return m_ButtonRemove.isVisible();
  }

  /**
   * Sets whether the layer can be removed.
   *
   * @param value	true if removable
   */
  public void setRemovable(boolean value) {
    m_ButtonRemove.setVisible(value);
  }

  /**
   * Returns whether actions are available.
   *
   * @return		true if available
   */
  @Override
  public boolean hasActionsAvailable() {
    return m_ButtonActions.isVisible();
  }

  /**
   * Sets whether the layer actions are available.
   *
   * @param value	true if available
   */
  public void setActionsAvailable(boolean value) {
    m_ButtonActions.setVisible(value);
  }

  /**
   * Updates the image using the current color.
   */
  protected void updateImage() {
    int		black;
    int		newColor;
    int[]	pixels;
    int		i;

    if (m_Image == null)
      return;

    black    = Color.BLACK.getRGB();
    newColor = getColor().getRGB();
    pixels   = m_Image.getRGB(0, 0, m_Image.getWidth(), m_Image.getHeight(), null, 0, m_Image.getWidth());
    for (i = 0; i < pixels.length; i++) {
      if (pixels[i] != black)
	pixels[i] = newColor;
    }
    m_Image.setRGB(0, 0, m_Image.getWidth(), m_Image.getHeight(), pixels, 0, m_Image.getWidth());
  }

  /**
   * Returns the image as image with indexed palette.
   *
   * @return		the converted image
   */
  public BufferedImage getIndexedImage() {
    return BufferedImageHelper.convert(getImage(), BufferedImage.TYPE_BYTE_INDEXED);
  }

  /**
   * Performs the drawing.
   *
   * @param g2d		the graphics context
   */
  @Override
  protected void doDraw(Graphics2D g2d) {
    Composite 	original;

    original = g2d.getComposite();
    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, getAlpha()));
    g2d.drawImage(m_Image, null, 0, 0);
    g2d.setComposite(original);
  }

  /**
   * Returns the current state.
   *
   * @return		the state
   */
  @Override
  public AbstractLayerState getState() {
    OverlayLayerState	result;

    result        = new OverlayLayerState();
    result.name   = getName();
    result.image  = BufferedImageHelper.deepCopy(getImage());
    result.color  = getColor();
    result.alpha  = getAlpha();
    result.active = isActive();

    return result;
  }

  /**
   * Restores the state of the layer.
   *
   * @param state	the state
   */
  @Override
  public void setState(AbstractLayerState state) {
    setName(state.name);

    if (state instanceof AbstractImageLayerState) {
      setImage(((AbstractImageLayerState) state).image);
    }

    if (state instanceof OverlayLayerState) {
      setColor(((OverlayLayerState) state).color);
      setAlpha(((OverlayLayerState) state).alpha);
      setActive(((OverlayLayerState) state).active);
    }
  }

  /**
   * Creates a new and empty image.
   *
   * @param width	the width to use
   * @param height	the height to use
   * @return		the image
   */
  public static BufferedImage newImage(int width, int height) {
    BufferedImage 	result;
    Graphics2D		g2d;

    result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    g2d   = result.createGraphics();
    g2d.setColor(Color.BLACK);
    g2d.fillRect(0, 0, width, height);
    g2d.dispose();

    return result;
  }
}