package home.smart.fly.scucommunity.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import home.smart.fly.scucommunity.content.Answer;
import home.smart.fly.scucommunity.content.Question;
import home.smart.fly.scucommunity.content.User;

/**
 * Created by lenovo on 2018/3/17.
 */

public class Utility {

    /*解析和处理服务器返回的回答类*/
    public static List<Answer> handleAnswerResponse(String response){
        Gson gson = new Gson();
        List<Answer> answerList = gson.fromJson(response,new TypeToken<List<Answer>>(){}.getType());
        return answerList;
    }

    /*解析和处理服务器返回的问题类*/
    public static List<Question> handleQuestionResponse(String response){
        Gson gson = new Gson();
        List<Question> QuestionList = gson.fromJson(response,new TypeToken<List<Question>>(){}.getType());
        return QuestionList;
    }

    /*解析和处理服务器返回的用户类*/
    public static List<User> handleUserResponse(String response){
        Gson gson = new Gson();
        List<User> UserList = gson.fromJson(response,new TypeToken<List<User>>(){}.getType());
        return UserList;
    }


}
