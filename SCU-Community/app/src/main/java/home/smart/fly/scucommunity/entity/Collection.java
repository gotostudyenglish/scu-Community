package home.smart.fly.scucommunity.entity;

import org.litepal.crud.DataSupport;

/**
 * Created by MWM on 2018/3/17.
 */

public class Collection extends DataSupport{
    private String Question_ID;
    private String User_ID;

    public void setQuestion_ID(String Question_ID){
        this.Question_ID = Question_ID;
    }

    public void setUser_ID(String User_ID){
        this.User_ID = User_ID;
    }

    public String getQuestion_ID(){
        return Question_ID;
    }

    public String getUser_ID(){
        return User_ID;
    }
}
