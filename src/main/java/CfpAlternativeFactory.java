import com.rma.factories.AbstractNewObjectFactory;
import com.rma.factories.NewObjectFactory;
import com.rma.io.FileManager;
import com.rma.io.FileManagerImpl;
import com.rma.io.RmaFile;
import com.rma.model.Project;
import com.rma.ui.GenericNewObjectPanel;
import com.rma.ui.ProjectDialog;
import sun.net.www.content.text.Generic;

import javax.swing.*;

public class CfpAlternativeFactory extends AbstractNewObjectFactory implements NewObjectFactory {
    private CfpPlugin _plugin;

    public CfpAlternativeFactory(CfpPlugin plugin) {
        super(CfpI18n.getI18n(CfpMessages.Plugin_Name));
        _plugin = plugin;
    }

    @Override
    public JComponent createNewObjectPanel() {
        GenericNewObjectPanel panel = new GenericNewObjectPanel();
        panel.setFileComponentsVisible(false);
        Project p = Project.getCurrentProject();
        panel.setName("");
        panel.setDescription("");
        panel.setExistingNamesList(_plugin.getAlternativeList());
        panel.setDirectory(p.getProjectDirectory() +
                RmaFile.separator +
                _plugin.getPluginDirectory());
        return panel;
    }

    @Override
    public JComponent createOpenObjectPanel() {
        return null;
    }

    @Override
    public Object createObject(JComponent jComponent) {
        GenericNewObjectPanel panel = (GenericNewObjectPanel)jComponent;
        CfpAlternative alt = new CfpAlternative();
        alt.setName(panel.getSelectedName());
        alt.setDescription(panel.getSelectedDescription());
        alt.setFile(FileManagerImpl.getFileManager().getFile(panel.getSelectedFile().getPath() +
                RmaFile.separator +
                alt.getName() +
                _plugin.getAltFileExtension()));
        alt.setProject(Project.getCurrentProject());
        _plugin.addAlternative(alt);
        _plugin.editAlternative(alt);
        alt.saveData();
        return alt;
    }

    @Override
    public void openObject(JComponent jComponent) {

    }
}
