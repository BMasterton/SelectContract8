/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package selectcontract;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import java.util.ArrayList;



/**
 *
 * @author C0501833
 */
class ContractController {
    
   private ContractView theView;
   private ContractModel theModel;
   private ContractForm theForm;
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
       this.theView.addExitMenuListener(new AddExitMenuListener());
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
   
   
   public boolean isContractThere(){
       
      return true;
    
         
          
        
    
            
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
                
                theForm = new ContractForm(theView, true);
                theForm.setLocationRelativeTo(null);
                theForm.addSaveButtonListener(new SaveContractlistener());
                theForm.setVisible(true);
                

            }catch (Exception ex){
                   System.out.println(ex);

            }
        }
   }
   
   class SaveContractlistener implements ActionListener{
       @Override
       public void actionPerformed(ActionEvent e){
        String contractID;
        contractID = theForm.getContractId();
        
        String OriginCityID;
        OriginCityID = theForm.getOriginId();
        
        String DestinationID;
        DestinationID = theForm.getDestinationId();
        
        String OrderItemID;
        OrderItemID = theForm.getOrderId();
        String[] contractString = theModel.stringOfContractIDs();
        
     
        
        
          try{
              //this is validation for the contract input
              if(!((contractID.length() <5 && contractID.matches("[0-9][a-zA-Z]+")) && !contractID.equals(""))){
                  JOptionPane.showMessageDialog(null, "Please enter a proper name");
                  throw new IOException("bad contract ID "); 
            }
             
              //this is validation for the OriginCity
              if(!(((OriginCityID.matches("Victoria") || OriginCityID.matches("Vancouver") || OriginCityID.matches("Seattle") || OriginCityID.matches("Nanimo") || OriginCityID.matches("Prince George"))
                  && !OriginCityID.equals(DestinationID)) && !OriginCityID.equals(""))){
                  JOptionPane.showMessageDialog(null, "Please enter a proper OriginCity");
                  throw new IOException("bad Origin / Destination City ");
              }
              
              //this is validation for the Destination city
              if(!(((DestinationID.matches("Victoria") || DestinationID.matches("Vancouver") || DestinationID.matches("Seattle") || DestinationID.matches("Nanimo") || DestinationID.matches("Prince George"))
                  && !DestinationID.equals(OriginCityID)) && !DestinationID.equals(""))){
                  JOptionPane.showMessageDialog(null, "Please enter a proper DestinationCity");
                  throw new IOException("bad Destination / Origin City ");                  
              }
             
              //this is validation for the orderitem
              if(OrderItemID.contains(",") || OrderItemID.matches("^[0-9]+$") || OrderItemID.equals("")){
                   JOptionPane.showMessageDialog(null, "Please enter correct Order ID");
                  throw new IOException("bad order id ");
              }
             
              //iterates through the total size of all contracts in the file
            
              for(int i = 0; i < theModel.getContractCount(); i++){
                 // 
                 if(contractID.equals(contractString[i])){//this takes the most recent contarct, and compares it to a string array that holds all the contracts in it.
                     throw new IOException("contract ID matches previously input Contract ID, try again ");
                  }
               }
                     String filePath = "./contracts.txt";
                     FileWriter fw = new FileWriter(filePath, true);
                      fw.write("\n" + contractID.toUpperCase() + "," + OriginCityID + "," + DestinationID + "," + OrderItemID);
                      fw.flush();
                      fw.close();
                     contractString[theModel.getContractCount()+1]= contractID; // adding the new contract name to the end of the array for checking 
                      String emptyString = "";
                      theForm.setContractID(emptyString); 
                      theForm.setOriginID(emptyString);
                      theForm.setDestinationID(emptyString);
                      theForm.setOrderID(emptyString);
                      
                              
                  
          }catch (Exception ex) {            
               System.out.println(ex);      
          } 
          theView.updateContractViewPanel(theModel.getCurrentContractNum(), theModel.getContractCount());//why doesn't this update the counter 
                      setUpDisplay();
       } 
       
   }
   
   
   class newContractistener implements ActionListener {
       @Override
       public void actionPerformed(ActionEvent e){
       }
   }
   
   class AddExitMenuListener implements ActionListener {
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
