import com.rma.factories.NewObjectFactory;
import hec2.map.GraphicElement;
import hec2.model.DataLocation;
import hec2.plugin.CreatablePlugin;
import hec2.plugin.action.EditAction;
import hec2.plugin.action.OutputElement;
import hec2.plugin.lang.ModelLinkingException;
import hec2.plugin.lang.OutputException;
import hec2.plugin.model.ModelAlternative;
import hec2.plugin.selfcontained.AbstractSelfContainedPlugin;
import hec2.rts.plugin.RtsPlugin;
import hec2.rts.ui.RtsTabType;

import java.util.List;

public class FIRO_CFP_Plugin extends AbstractSelfContainedPlugin<FIRO_CFP_Alternative> implements RtsPlugin, CreatablePlugin {
    public static void main(String[] args) {

    }

    @Override
    public void editAlternative(FIRO_CFP_Alternative firo_cfp_alternative) {

    }

    @Override
    protected FIRO_CFP_Alternative newAlternative(String s) {
        return null;
    }

    @Override
    protected String getAltFileExtension() {
        return null;
    }

    @Override
    public String getPluginDirectory() {
        return null;
    }

    @Override
    protected NewObjectFactory getAltObjectFactory() {
        return null;
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
        return false;
    }

    @Override
    public List<DataLocation> getDataLocations(ModelAlternative modelAlternative, int i) {
        return null;
    }

    @Override
    public boolean setDataLocations(ModelAlternative modelAlternative, List<DataLocation> list) throws ModelLinkingException {
        return false;
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
        return null;
    }

    @Override
    public void editAction(String s, ModelAlternative modelAlternative) {

    }

    @Override
    public boolean saveProject() {
        return false;
    }

    @Override
    public boolean displayApplication() {
        return false;
    }

    @Override
    public String getVersion() {
        return null;
    }
}
