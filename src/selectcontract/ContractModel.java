package selectcontract;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author C0501833
 */
class ContractModel {
    
    private ArrayList<Contract> theContracts;
    private ArrayList<Contract> theContractsAll;
    private SortedSet<String> originCityList;
    private String[] contractIDsString;
    private int contractCounter;
    String filename;
    public static final int BEGIN_OF_ARRAYLIST = 0;
    public static final int TOTAL_ATTRIBUTES = 4;
    public static final int INDEX_OF_CONTRACT_ID = 0;
    public static final int INDEX_OF_ORIGIN_CITY = 1;
    public static final int INDEX_OF_DEST_CITY = 2;
    public static final int INDEX_OF_ORDER_CITY = 3;
    
   
    ContractModel(String filename){
        
        try{
            this.contractCounter = 0;
            theContracts = new ArrayList<>();
            originCityList = new TreeSet<>();
            
            this.filename = filename;
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;
                
                while((line = bufferedReader.readLine()) != null){
                    String[] tokens = line.split(",", TOTAL_ATTRIBUTES);
                    
                    String contractID = tokens[INDEX_OF_CONTRACT_ID];
                    String originCity = tokens[INDEX_OF_ORIGIN_CITY];
                    String destCity = tokens[INDEX_OF_DEST_CITY];
                    String orderItem = tokens[INDEX_OF_ORDER_CITY];
                    originCityList.add(originCity); //part 8 add all the origincity names to the originCityList TreeList
              
                     
                    Contract dataContract = new Contract(contractID, originCity, destCity, orderItem);
                    theContracts.add(dataContract);  
                }
                theContractsAll = new ArrayList<>(theContracts);
                originCityList.add("All"); // adding "all" to the end of the originCityList TreeList
                fileReader.close();          
        } catch(IOException ex){
            System.out.println(ex.getMessage());
        }         
    }
    
   public boolean findContractById(String id){
       for(int i = 0; i< theContracts.size(); i++){// maybe change to theContracts 
           if(theContracts.get(i).getContractID().equals(id)){
           return true;
            }
          
       }
       return false; 
   }
   
   public void addContract (Contract newContract){
       theContracts.add(newContract);
   }
    
    boolean foundContracts(){
        
        if(theContracts.size() > BEGIN_OF_ARRAYLIST){
            return true;
        }
        else {
            return false;
        }
    }
    
    public String[] stringOfContractIDs(){
        return this.contractIDsString;
    }
    
    public Contract getTheContract(){
        
        return theContracts.get(contractCounter);
    }
    
    public int getContractCount(){
        
        return theContracts.size();
    }
    
    public int getCurrentContractNum(){
        
        return contractCounter;
    }
    
    public void nextContract(){
        if(getCurrentContractNum() <= getContractCount()){
            contractCounter +=1;
        }
        
    }
    
    public void prevContract(){
        if(getCurrentContractNum() > BEGIN_OF_ARRAYLIST){
            contractCounter -=1;
        }
    }
    // part 10, unfortunately you need to use a reguar  string array and not an Arraylist for combo boxes if you want to 
    //define a list of items to appear in the combo box,
    public String[] getOriginCityList(){
        String[] a;
        a = originCityList.toArray(new String[originCityList.size()]);  //so all were going to do here is make a String array, and we are taking the originCityList arraylist and using the 
           //toArray method on it, with parameters of a new String(size param is the arraylist size))
        return a;
    }
    
    public void updateContractList(String city){
        theContracts = new ArrayList<>(theContractsAll);
        if (city != "All"){
            theContracts.removeIf(s -> !s.contains(city));
        }
        contractCounter = 0;
    }
    
    
}
