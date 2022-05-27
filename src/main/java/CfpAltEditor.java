

import java.awt.Dialog;
import java.awt.Frame;

import hec.gui.SelectorPanelEditor;

import hec2.plugin.model.ModelAlternative;


@SuppressWarnings("serial")
public class CfpAltEditor  extends SelectorPanelEditor
{
	private CfpAltPanel  _cfpAltPanel;

	public CfpAltEditor(Dialog parent, boolean modal)
	{
		super(parent, modal);
		createControls();
		pack();
		setSize(550,250);
		setLocationRelativeTo(getParent());
	}

	public CfpAltEditor(Frame parent, boolean modal)
	{
		super(parent, modal);
		createControls();
		pack();
		setSize(550,550);
		setLocationRelativeTo(getParent());
	}

	private void createControls()
	{
		setTitle(CfpI18n.getI18n(
				CfpMessages.PLUGIN_EDITOR_TITLE).getText());
		_cfpAltPanel = new CfpAltPanel();
		addPanel(_cfpAltPanel);
	}

	/**
	 * @param modelAlt
	 */
	public void setModelAlternative(ModelAlternative modelAlt)
	{
		_cfpAltPanel.setModelAlternative(modelAlt);
	}
}
