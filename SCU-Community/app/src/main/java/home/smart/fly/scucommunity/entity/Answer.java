package home.smart.fly.scucommunity.entity;

import org.litepal.crud.DataSupport;

/**
 * Created by MWM on 2018/3/17.
 */

public class Answer extends DataSupport {
    private String Answer_ID;
    private String Content;
    private String Question_ID;
    private String Name;


    public String getQuestion_ID(){
        return Question_ID;
    }
    public String getName(){
        return Name;
    }
    public String getContent() {
        return Content;
    }
    public String getAnswer_ID(){
        return Answer_ID;
    }


    public void setName(String Name){
        this.Name = Name;
    }
    public void setQuestion_ID(String Question_ID){
        this.Question_ID = Question_ID;
    }
    public void setAnswer_ID(String Answer_ID){
       this.Answer_ID=Answer_ID;
    }
    public void setContent(String Content){
       this.Content=Content;
    }

}
