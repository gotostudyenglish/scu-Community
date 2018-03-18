package home.smart.fly.scucommunity.util;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import home.smart.fly.scucommunity.entity.Answer;
import home.smart.fly.scucommunity.entity.Collection;

/**
 * Created by lenovo on 2018/3/17.
 */

public class Utility {

    /*解析和处理服务器返回的回答类*/
    public static boolean handleAnswerResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray answers = new JSONArray(response);
                for(int i = 0;i < answers.length();i++){
                   JSONObject answerObject = answers.getJSONObject(i);
                    Answer answer = new Answer();
                    answer.setAnswer_ID(answerObject.getString("Answer_ID"));
                    answer.setQuestion_ID(answerObject.getString("Question_ID"));
                    answer.setContent(answerObject.getString("Content"));
                    answer.setName(answerObject.getString("Name"));
                    answer.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    /*解析和处理服务器返回的收藏信息*/
    public static boolean handleCollectionResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray collections = new JSONArray(response);
                for(int i = 0;i < collections.length();i++){
                    JSONObject collectionsJSONObject = collections.getJSONObject(i);
                    Collection collection = new Collection();
                    collection.setQuestion_ID(collectionsJSONObject.getString("Question_ID"));
                    collection.setUser_ID(collectionsJSONObject.getString("User_ID"));
                    collection.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

   /*
    public static boolean handleQuestionResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray questions  = new JSONArray(response);
                for(int i = 0;i < questions.length();i++){
                    JSONObject collectionsJSONObject = collections.getJSONObject(i);
                    Collection collection = new Collection();
                    collection.setQuestion_ID(collectionsJSONObject.getString("Question_ID"));
                    collection.setUser_ID(collectionsJSONObject.getString("User_ID"));
                    collection.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
    */


}
