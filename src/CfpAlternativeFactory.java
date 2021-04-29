import com.rma.factories.AbstractNewObjectFactory;
import com.rma.factories.NewObjectFactory;

import javax.swing.*;

public class CfpAlternativeFactory extends AbstractNewObjectFactory implements NewObjectFactory {
    private CfpPlugin _plugin;

    public CfpAlternativeFactory(CfpPlugin plugin) {
        super(CfpI18n.getI18n(CfpMessages.Plugin_Name));
        _plugin = plugin;
    }

    @Override
    public JComponent createNewObjectPanel() {
        return null;
    }

    @Override
    public JComponent createOpenObjectPanel() {
        return null;
    }

    @Override
    public Object createObject(JComponent jComponent) {
        return null;
    }

    @Override
    public void openObject(JComponent jComponent) {

    }
}
