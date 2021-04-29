import hec.util.I18n;
import java.util.ResourceBundle;

public class CfpI18n extends I18n {
    public static final String BUNDLE_NAME = "ComputableProperties";
    private static final ResourceBundle SAMPLE_RESOURCE_BUNDLE;
    private ResourceBundle _resourceBundle;
    static {
        SAMPLE_RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
    }
    protected CfpI18n(String prefix, String bundleName){
        super(prefix, bundleName);
    }
    public static I18n getI18n(String prefix) {
        return new CfpI18n(prefix, BUNDLE_NAME);
    }
}
