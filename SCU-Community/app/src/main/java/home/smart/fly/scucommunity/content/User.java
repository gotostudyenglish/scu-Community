package home.smart.fly.scucommunity.content;

/**
 * Created by MWM on 2018/3/17.
 */

public class User {
    private int uid;
    private String student_id;
    private String password;
    private String name;
    private String signature;
    private String description;
    private String habit;


    public int getUid(){
        return uid;
    }
    public String getStudent_id(){
        return student_id;
    }
    public String getPassword(){
        return password;
    }
    public String getName(){
        return name;
    }
    public String getSignature(){
        return signature;
    }
    public String getDescription(){
        return description;
    }
    public String getHabit(){
        return habit;
    }


    public void setUid(int uid){
        this.uid = uid;
    }
    public void setStudent_id(String studengt_id){
        this.student_id = studengt_id;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setSignature(String signature){
        this.signature = signature;
    }
    public void setHabit(String habit){
        this.habit = habit;
    }


}
