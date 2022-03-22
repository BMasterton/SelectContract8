/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package selectcontract;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.AbstractButton;



/**
 *
 * @author C0501833
 */
class ContractController {
    
   private ContractView theView;
   private ContractModel theModel;
   public static final int BEGIN_OF_ARRAYLIST = 0;
   
   ContractController(ContractView theView, ContractModel theModel){
       this.theView = theView;
       this.theModel = theModel;
       this.theView.addPrevListener(new PrevButtonListener());
       this.theView.addBidListener(new BidButtonListener());
       this.theView.addNextListener(new NextButtonListener());
       this.theView.addcomboBoxListener(new ComboListener());
       this.theView.setOriginCityList(this.theModel.getOriginCityList());
       this.theView.addContractListener(new NewContractListener());
       this.theView.addExitMenuListener(new addExitMenuListener());
       setUpDisplay();
   }
   
   private void setUpDisplay() {
       
       try {
           if(theModel.foundContracts()){
               Contract c = theModel.getTheContract();
               theView.setContractID(c.getContractID());
               theView.setDestCity(c.getDestCity());
               theView.setOriginCity(c.getOriginCity());
               theView.setOrderItem(c.getOrderItem());
               theView.updateContractViewPanel(theModel.getCurrentContractNum(), theModel.getContractCount());
           } else {
           theView.setContractID("???");
           theView.setDestCity("???");
           theView.setOriginCity("???");
           theView.setOrderItem("???");
           }
       } catch (Error ex){
           //provide an error message to the console output.
           System.out.println(ex);
           theView.displayErrorMessage("Error: There was a problem setting the contract. \n"
                                        + "       Contract number: " + theModel.getCurrentContractNum());
       }
       
       
   }
   
   class PrevButtonListener implements ActionListener {
      
       @Override
       public void actionPerformed(ActionEvent e){
           
           if(theModel.getCurrentContractNum() == BEGIN_OF_ARRAYLIST) {
               return;
           }
           
           try {
               
               theModel.prevContract();
           } catch (Exception ex){
               System.out.println(ex);
               theView.displayErrorMessage(
               "Error: There is a problem setting a previous contract.");
           }
           setUpDisplay();
       }
       
   }
   
   class NextButtonListener implements ActionListener {
      
       @Override
       public void actionPerformed(ActionEvent e){
           
           if(theModel.getCurrentContractNum() == theModel.getContractCount()-1) {
               return;
           }
           
           try {
              
               theModel.nextContract();
           } catch (Exception ex){
               System.out.println(ex);
               theView.displayErrorMessage(
               "Error: There is a problem setting a Next contract.");
           }
           setUpDisplay();
       }
       
   }
   
   
   class ComboListener implements ItemListener {
       @Override
       public void itemStateChanged(ItemEvent e){
           System.out.println(e.getItem().toString());
           if (e.getStateChange() == ItemEvent.SELECTED){
               String selectedCity = e.getItem().toString();
               System.out.println(selectedCity);
               theModel.updateContractList(selectedCity);
               setUpDisplay();
           }
       }
   }
   
   class NewContractListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                ContractForm nc;
                nc = new ContractForm(theView, true/*, theModel.getTheContract()*/);
                nc.setLocationRelativeTo(null);
                nc.setVisible(true);
                

            }catch (Exception ex){
                   System.out.println(ex);

            }
        }
   }
   
   class addExitMenuListener implements ActionListener {
       @Override
       public void actionPerformed(ActionEvent e){
           try{
               System.exit(0);
           }catch (Exception ex){
               System.out.println(ex); 
           }
           
       }
       
   }
   
   class BidButtonListener implements ActionListener {
      
       @Override
       public void actionPerformed(ActionEvent e){
           try {
               ConfirmBid cb;
               cb = new ConfirmBid(theView, true, theModel.getTheContract());
               cb.setLocationRelativeTo(null);
               cb.setVisible(true);
           } catch (Exception ex){
               System.out.println(ex);
               theView.displayErrorMessage("Error: The number entered must be integers");
               
           } //end catch 
       } //end action performed 
   }//end bidButtonListener
   
}
