package home.smart.fly.scucommunity.content;

/**
 * Created by MWM on 2018/3/17.
 */

public class Answer {
    private int Answer_ID;
    private int Question_ID;
    private int uid;
    private String Content;
    private String Name;


    public String getContent() {
        return Content;
    }
    public int getAnswer_ID(){
        return Answer_ID;
   }
    public int getUid(){
        return uid;
    }
    public int getQuestion_ID(){return  Question_ID;}
    public String getName(){return  Name;}

    public void setAnswer_ID(int Answer_ID){
       this.Answer_ID = Answer_ID;
   }
    public void setContent(String Content){
       this.Content = Content;
   }
    public void setUid(int uid){
        this.uid=uid;
    }
    public void setQuestion_ID(int Qid){this.Question_ID = Qid;}
    public void setName(String Name){this.Name = Name;}
}
