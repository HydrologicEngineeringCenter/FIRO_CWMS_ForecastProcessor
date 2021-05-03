import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class CfpMessages {
    public static final String Bundle_Name = CfpI18n.BUNDLE_NAME;
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(Bundle_Name);
    public static final String Plugin_Name = "CfpPlugin.Name";
    public static final String Plugin_Description = "CfpPlugin.Description";
    public static final String Plugin_Short_Name = "CfpPlugin.ShortName";
    private CfpMessages() {super();}
    public static String getString(String key) {
        try{
            return RESOURCE_BUNDLE.getString(key);
        }
        catch(MissingResourceException e) {
            return "!" + key + "!";
        }
    }
}
