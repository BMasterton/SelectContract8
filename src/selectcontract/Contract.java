/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package selectcontract;

/**
 *
 * @author braon
 */
public class Contract {
    String contractID;
    String originCity;
    String destCity;
    String orderItem;
    
    Contract(String contractID, String originCity, String destCity, String orderItem){
        this.contractID = contractID;
        this.originCity = originCity;
        this.destCity = destCity;
        this.orderItem = orderItem;
    }
    
    public String getContractID(){
        return this.contractID;
    }
    
    public String getOriginCity(){
        return this.originCity;
    }
    
    public String getDestCity(){
        return this.destCity;
    }
    
    public String getOrderItem(){
        return this.orderItem;
    }
    // this method will look at the input string and if its the same as the originCity , it will return true 
    boolean contains(String city) {
        if(this.originCity.equals(city)){
            return true;
        }
        else{
            return false;
        }
    }
}
