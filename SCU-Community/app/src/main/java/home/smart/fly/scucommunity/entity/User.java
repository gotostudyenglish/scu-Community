package home.smart.fly.scucommunity.entity;

/**
 * Created by MWM on 2018/3/17.
 */

public class User {
    private String Account_ID;
    private String Password;
    private String Name;
    private String Interest;
    private String Introduction;
    private String Qianming;


    public String getID(){
        return Account_ID;

    }
    public String getPassword(){
        return Password;

    }

    public String getName(){
        return Name;

    }public String getInterest(){
        return Interest;

    }public String getIntroduction(){
        return Introduction;

    }public String getQianming(){
        return Qianming;

    }
    public void setID(String Account_ID){
        this.Account_ID=Account_ID;
    }
    public void setPassword(String Password){
        this.Password=Password;
    }
    public void setName(String Name){
        this.Name=Name;
    }
    public void setInterest(String Interest){
        this.Interest=Interest;
    }
    public void setIntroduction(String Introduction){
        this.Introduction=Introduction;
    }
    public void setQianming(String Qianming){
        this.Qianming=Qianming;
    }



}
