import com.rma.io.RmaFile;
import hec2.model.DataLocation;
import hec2.plugin.selfcontained.SelfContainedPluginAlt;
import hec2.plugin.model.ComputeOptions;
import org.jdom.Document;
import org.jdom.Element;

import java.util.ArrayList;
import java.util.List;

public class CfpAlternative extends SelfContainedPluginAlt {
    private ComputeOptions _computeOptions;
    private List<DataLocation> _dataLocations;
    private static final String DocumentRoot = "CfpAlternative";
    private static final String AlternativeNameAttribute = "Name";
    private static final String AlternativeDescriptionAttribute = "Desc";

    public CfpAlternative() {
        super();
        _dataLocations = new ArrayList<>();
    }

    public CfpAlternative(String name){
        this();
        setName(name);
    }

    @Override
    protected boolean loadDocument(Document document) {
        if (document != null) {
            org.jdom.Element ele = document.getRootElement();
            if (ele == null){
                System.out.println("No root element on the provided XML Document");
                return false;
            }
            if (ele.getName().equals(DocumentRoot)){
                setName(ele.getAttributeValue(AlternativeNameAttribute));
                setDescription(ele.getAttributeValue(AlternativeDescriptionAttribute));
            }
            else {
                System.out.println("XML document root was inproperly named.");
                return false;
            }

            if (_dataLocations == null) {
                _dataLocations = new ArrayList<>();
            }
            _dataLocations.clear();
            loadDataLocations(ele, _dataLocations);
            setModified(false);
            return true;
        }
        else {
            System.out.println("XML document was null.");
            return false;
        }
    }

    @Override
    public boolean isComputable() {
        return false;
    }

    @Override
    public boolean compute() {
        return false;
    }

    @Override
    public int getModelCount() {
        return 0;
    }

    @Override
    public boolean cancelCompute() {
        return false;
    }

    @Override
    public String getLogFile() {
        return null;
    }

    public void setComputeOptions(ComputeOptions opts){
        _computeOptions = opts;
    }

    @Override
    public boolean saveData(RmaFile file){
        if (file != null) {
            Element root = new Element(DocumentRoot);
            root.setAttribute(AlternativeNameAttribute, getName());
            root.setAttribute(AlternativeDescriptionAttribute, getDescription());
            if (_dataLocations != null) {
                saveDataLocations(root, _dataLocations);
            }
            Document doc = new Document(root);
            return writeXMLFile(doc, file);
        }
        return false;
    }
}
