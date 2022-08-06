package nc.apps.utils;

public class StringOperations {
    public static boolean isNullOrEmpty(String s){
        if(s==null||s.isEmpty()){
            return true;
        }
        return false;
    }
}
