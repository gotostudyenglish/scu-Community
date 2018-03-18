package home.smart.fly.scucommunity.entity;

/**
 * Created by MWM on 2018/3/17.
 */

public class Question {
    private String Question_ID;
    private int Like_sum;
    private int Answer_sum;
    private Boolean isArticle;
    private String Title;
    private String Content;
    private String User_ID;
    private String User_Name;

    public String getUser_ID(){
        return User_ID;
    }

    public String getUser_Name(){
        return User_Name;
    }
    public String getQuetsion_ID(){
        return Question_ID;

    }

    public int getLike_sum(){
        return Like_sum;

    }
    public int getAnswer_sum(){
        return Answer_sum;

    }
    public Boolean isArticle(){
        return true;

    }

    public String getTitle(){
        return Title;

    }
    public String getContent(){
        return Content;

    }


    public void setIsArticle(boolean isArticle){
        this.isArticle = isArticle;
    }

    public void setLike_sum(int Like_sum){
        this.Like_sum=Like_sum;
    }
    public void setAnswer_sum(int Answer_sum){
        this.Answer_sum=Answer_sum;
    }
    public void setTitle(String Title){
        this.Title=Title;
    }
    public void setQuestion_ID(String Question_ID){
        this.Question_ID=Question_ID;
    }
    public void setContent(String Content){
        this.Content=Content;
    }

    public void setUser_ID(String User_ID){
        this.User_ID = User_ID;
    }
    public  void setUser_Name(String User_Name){
        this.User_Name = User_Name;
    }



}
