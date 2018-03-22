package home.smart.fly.scucommunity.content;

/**
 * Created by MWM on 2018/3/17.
 */

public class Question {
    private int Question_ID;
    private int uid;
    private String title;
    private String Content;
    private String name;
    private int like;

    public int getQuetsion_ID(){
        return Question_ID;
    }
    public int getuid(){return uid;}
    public String gettitle(){return title;}
    public String getContent(){
        return Content;
    }
    public String getname(){
        return name;
    }
    public int getlike(){return like;}

    public void setQuestion_ID(int Question_ID){
        this.Question_ID = Question_ID;
    }
    public void setUid(int uid){
        this.uid = uid;
    }
    public void settitle(String title){
        this.title = title;
    }
    public void setContent(String Content){
        this.Content = Content;
    }
    public void setname(String name){this.name = name;}
    public void setlike(int like){this.like = like;}

}
