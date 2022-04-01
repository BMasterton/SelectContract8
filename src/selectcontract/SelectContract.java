
package selectcontract;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author C0501833
 */
public class SelectContract {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParserConfigurationException, SAXException {
        ContractView theView = new ContractView();  
        ContractModel theModel = new ContractModel("C:\\Users\\braon\\OneDrive\\Camosun\\Camosun Winter Term 2 2022\\ICS 125 - SENG Process\\SelectContract\\thecontracts.xml");  
        ContractController theController; 
        theController = new ContractController(theView, theModel); 
        theView.setVisible(true); 

    }
    
}
