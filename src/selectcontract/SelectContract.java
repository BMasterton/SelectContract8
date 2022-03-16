
package selectcontract;

/**
 *
 * @author C0501833
 */
public class SelectContract {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ContractView theView = new ContractView();  
        ContractModel theModel = new ContractModel("./contracts.txt");  
        ContractController theController; 
        theController = new ContractController(theView, theModel); 
        theView.setVisible(true); 

    }
    
}
