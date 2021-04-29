import com.rma.factories.AbstractNewObjectFactory;
import com.rma.factories.NewObjectFactory;
import com.rma.util.I18n;

import javax.swing.*;

public class CfpAlternativeFactory extends AbstractNewObjectFactory implements NewObjectFactory {


    public CfpAlternativeFactory(I18n info) {
        super(info);
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
