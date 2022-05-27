import java.util.ArrayList;
import java.util.List;

import com.rma.client.Browser;
import com.rma.factories.NewObjectFactory;
import com.rma.io.FileManagerImpl;
import com.rma.io.RmaFile;

import hec2.map.GraphicElement;
import hec2.model.DataLocation;
import hec2.model.ProgramOrderItem;
import hec2.plugin.CreatablePlugin;
import hec2.plugin.action.EditAction;
import hec2.plugin.action.OutputElement;
import hec2.plugin.lang.ModelLinkingException;
import hec2.plugin.lang.OutputException;
import hec2.plugin.model.ComputeOptions;
import hec2.plugin.model.ModelAlternative;
import hec2.plugin.selfcontained.AbstractSelfContainedPlugin;
import hec2.rts.plugin.RtsPlugin;
import hec2.rts.plugin.RtsPluginManager;
import hec2.rts.plugin.action.ComputeModelAction;
import hec2.rts.ui.RtsTabType;

public class CfpPlugin extends AbstractSelfContainedPlugin<CfpAlternative> implements RtsPlugin, CreatablePlugin {
    public static final String PluginName = "FIRO_CFP";
    private static final String _pluginVersion = "1.0.0";
    private static final String _pluginSubDirectory = "cfp";
    private static final String _pluginExtension = ".cfp";

    public static void main(String[] args) {
        CfpPlugin plugin = new CfpPlugin();
    }

    public CfpPlugin() {
        super();
        setName(PluginName);
        setProgramOrderItem(new ProgramOrderItem(PluginName,
                "Blank Description.",
                false,
                1,
                "CFP",
                "blank/path"));
        RtsPluginManager.register(this);
    }


    @Override
    public void editAlternative(CfpAlternative cfp_alternative) {
    	if (cfp_alternative == null )
		{
			displayApplication();
			return;
		}
		CfpAltEditor editor = new CfpAltEditor(
				Browser.getBrowserFrame(), true);
		editor.setSelectionList(_altList);
		editor.fillForm(cfp_alternative);
		editor.setVisible(true);
    }

    @Override
    protected CfpAlternative newAlternative(String s) {
        return new CfpAlternative(s);
    }

    @Override
    protected String getAltFileExtension() {
        return _pluginExtension;
    }

    @Override
    public String getPluginDirectory() {
        return _pluginSubDirectory;
    }

    @Override
    protected NewObjectFactory getAltObjectFactory() {
        return new CfpAlternativeFactory(this);
    }

    @Override
    public boolean copyModelFiles(ModelAlternative modelAlternative, String s, boolean b) {
        return false;
    }

    @Override
    public List<EditAction> getGlobalEditActions(RtsTabType rtsTabType) {
        return null;
    }

    @Override
    public boolean closeForecast(String s) {
        return false;
    }

    @Override
    public boolean compute(ModelAlternative modelAlternative) {
        CfpAlternative alt = getSimulationAlt(modelAlternative);
        if (alt != null) {
            alt.setComputeOptions(modelAlternative.getComputeOptions());
            if (_computeListeners != null && !_computeListeners.isEmpty()) {
                for (int i = 0; i < _computeListeners.size(); i++) {
                    alt.addComputeListener(_computeListeners.get(i));
                }
            }
            return alt.compute();
        } else {
            addComputeErrorMessage("Failed to find Alternative for " + modelAlternative);
            return false;
        }
    }

    @Override
    public List<DataLocation> getDataLocations(ModelAlternative modelAlternative, int i) {
        CfpAlternative alt = getAlt(modelAlternative);
        if (alt == null) return null;
        if (DataLocation.INPUT_LOCATIONS == i) {
            return alt.getInputDataLocations();
        }
        else {
            return alt.getOutputDataLocations();
        }
    }

    @Override
    public boolean setDataLocations(ModelAlternative modelAlternative, List<DataLocation> list) throws ModelLinkingException {
        CfpAlternative alt = getAlt(modelAlternative);
        if (alt != null) {
            return alt.setDataLocations(list);
        }
        return true;
    }

    @Override
    public List<GraphicElement> getGraphicElements(ModelAlternative modelAlternative) {
        return null;
    }

    @Override
    public List<OutputElement> getOutputReports(ModelAlternative modelAlternative) {
        return null;
    }

    @Override
    public boolean displayEditor(GraphicElement graphicElement) {
        return false;
    }

    @Override
    public boolean displayOutput(OutputElement outputElement, List<ModelAlternative> list) throws OutputException {
        return false;
    }

    @Override
    public List<EditAction> getEditActions(ModelAlternative modelAlternative) {
        List<EditAction> actions = new ArrayList<EditAction>();
        ComputeModelAction cation = new ComputeModelAction("Compute", PluginName, "computeModel");
        actions.add(cation);
        return actions;
    }

    @Override
    public void editAction(String s, ModelAlternative modelAlternative) {

    }

    @Override
    public boolean saveProject() {
        boolean success = true;
        for (CfpAlternative alt: _altList){
            if (!alt.saveData()){
                success = false;
                System.out.println("Alternative " + alt.getName() + " could not save.");
            }
        }
        return success;
    }

    @Override
    public boolean displayApplication() {
        return false;
    }

    @Override
    public String getVersion() {
        return _pluginVersion;
    }

    @Override
    public CfpAlternative getSimulationAlt(ModelAlternative ma) {
        if (ma == null) {
            return null;
        }
        ComputeOptions co = ma.getComputeOptions();
        if (co == null) {
            return null;
        }
        CfpAlternative alt = getAlt(ma);
        if (alt == null) {
            return null;
        }
        String altName = ma.getName();
        RmaFile file = alt.getFile();
        String runDir = getRunDirectory(co);
        String fname = file.getName();
        String runPath = runDir.concat(RmaFile.separator).concat(fname);
        RmaFile runFile = FileManagerImpl.getFileManager().getFile(runPath);
        CfpAlternative simAlt = newAlternative(runFile.getAbsolutePath());
        simAlt.setFile(runFile);
        simAlt.setProject(alt.getProject());
        simAlt.setName(altName);
        simAlt.readData();
        return simAlt;
    }
}
