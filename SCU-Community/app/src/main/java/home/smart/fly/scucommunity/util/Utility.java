package home.smart.fly.scucommunity.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import home.smart.fly.scucommunity.entity.Answer;
import home.smart.fly.scucommunity.entity.Collection;
import home.smart.fly.scucommunity.entity.Question;
import home.smart.fly.scucommunity.entity.User;

/**
 * Created by lenovo on 2018/3/17.
 */

public class Utility {

    /*解析和处理服务器返回的回答类*/
    public List<Answer> handleAnswerResponse(String response){
        Gson gson = new Gson();
        List<Answer> answerList = gson.fromJson(response,new TypeToken<List<Answer>>(){}.getType());
        return answerList;
    }

    /*解析和处理服务器返回的收藏信息*/
    public List<Collection> handleCollectionResponse(String response){
        Gson gson = new Gson();
        List<Collection> collectionList = gson.fromJson(response,new TypeToken<List<Collection>>(){}.getType());
        return collectionList;

    }

    /*解析和处理服务器返回的问题信息*/
    public List<Question> handleQuestionResponse(String response){

                Gson gson = new Gson();
                List<Question> questionList = gson.fromJson(response,new TypeToken<List<Question>>(){}.getType());
                return questionList;
    }

    /*解析和处理服务器返回的用户信息*/
    public List<User> handleUserResponse(String response){
        Gson gson = new Gson();
        List<User> userList = gson.fromJson(response,new TypeToken<List<User>>(){}.getType());
        return userList;
    }

}
