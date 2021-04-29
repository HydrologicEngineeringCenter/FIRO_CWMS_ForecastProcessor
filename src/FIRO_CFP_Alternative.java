import hec2.model.DataLocation;
import hec2.plugin.selfcontained.SelfContainedPluginAlt;
import hec2.plugin.model.ComputeOptions;
import org.jdom.Document;

import java.util.ArrayList;
import java.util.List;

public class FIRO_CFP_Alternative extends SelfContainedPluginAlt {
    private ComputeOptions _computeOptions;
    private List<DataLocation> _dataLocations;
    private static final String DocumentRoot = "FIRO_CFP_Alternative";
    private static final String AlternativeNameAttribute = "Name";
    private static final String AlternativeDescriptionAttribute = "Desc";

    public FIRO_CFP_Alternative() {
        super();
        _dataLocations = new ArrayList<>();
    }

    public FIRO_CFP_Alternative(String name){
        this();
        setName(name);
    }

    @Override
    protected boolean loadDocument(Document document) {
        return false;
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
}
