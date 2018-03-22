package home.smart.fly.scucommunity.content;

/**
 * Created by MWM on 2018/3/17.
 */

public class Answer {
    private int Answer_ID;
    private int Qid;
    private String Content;
    private String Name;

    public String getContent() {
        return Content;
    }
   public int getAnswer_ID(){
        return Answer_ID;
   }
   public int getQid(){return  Qid;}
   public String getName(){return  Name;}

   public void setAnswer_ID(int Answer_ID){
       this.Answer_ID = Answer_ID;
   }
   public void setContent(String Content){
       this.Content = Content;
   }
   public void setQid(int Qid){this.Qid = Qid;}
   public void setUname(String Name){this.Name = Name;}
}
