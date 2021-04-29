import com.rma.io.RmaFile;
import hec.heclib.dss.DSSPathname;
import hec2.model.DataLocation;
import hec2.plugin.model.ModelAlternative;
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

    private List<DataLocation> defaultDataLocations() {
        if (!_dataLocations.isEmpty()) {
            for (DataLocation dl: _dataLocations) {
                String dlparts = dl.getDssPath();
                DSSPathname p = new DSSPathname(dlparts);
                if (p.aPart().equals("") && p.bPart().equals("") && p.cPart().equals("") &&
                        p.dPart().equals("") && p.ePart().equals("") && p.fPart().equals("")) {
                    if (validLinkedToDssPath(dl)) {
                        setDssParts(dl);
                    }
                }
            }
            return _dataLocations;
        }
        List<DataLocation> dlList = new ArrayList<>();
        DataLocation dloc = new DataLocation(this.getModelAlt(), _name, "Any");
        dlList.add(dloc);
        return dlList;
    }

    private void setDssParts(DataLocation dl) {
        DataLocation linkedTo = dl.getLinkedToLocation();
        String dssPath = linkedTo.getDssPath();
        DSSPathname p = new DSSPathname(dssPath);
        String[] parts = p.getParts();
        parts[1] = parts[1] + " Output";
        ModelAlternative malt = this.getModelAlt();
        malt.setProgram(CfpPlugin.PluginName);
        p.setParts(parts);
        dl.setDssPath(p.getPathname());
    }

    private boolean validLinkedToDssPath(DataLocation dl) {
        DataLocation linkedTo = dl.getLinkedToLocation();
        String dssPath = linkedTo.getDssPath();
        return !(dssPath == null || dssPath.isEmpty());

    }

    public List<DataLocation> getInputDataLocations(){
        return defaultDataLocations();
    }

    public List<DataLocation> getOutputDataLocations() {
        return defaultDataLocations();
    }

    public boolean setDataLocations(List<DataLocation> dataLocations) {
        boolean retval = false;
        for (DataLocation dl: dataLocations) {
            int i = dataLocations.indexOf(dl);
            if (!_dataLocations.contains(dl)) {
                DataLocation linkedTo = dl.getLinkedToLocation();
                String dssPath = linkedTo.getDssPath();
                if (validLinkedToDssPath(dl)) {
                    setModified(true);
                    setDssParts(dl);
                    _dataLocations.set(i, dl);
                    retval = true;
                }
            } else {
                DataLocation linkedTo = dl.getLinkedToLocation();
                String dssPath = linkedTo.getDssPath();
                if (validLinkedToDssPath(dl)) {
                    setModified(true);
                    setDssParts(dl);
                    retval = true;
                }
            }
        }
        if (retval) {saveData();}
        return retval;
    }


}
