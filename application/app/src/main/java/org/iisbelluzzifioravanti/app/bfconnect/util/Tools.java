package org.iisbelluzzifioravanti.app.bfconnect.util;

public final class Tools {

    //the key
    private static String[] keys_id = new String[]{ "beOPEN", "ETC34c", "ETC620", "ETC34d",
            "CHT32c", "CHT645", "CHT3c6", "CHTT00"};

    //not instantiable
    private Tools(){}

    //all the string must finish with cb
    public static boolean CheckId(String txt){
    //if(!txt.substring(2).equals("cb")) return false;
        for (int i=0 ; i< keys_id.length;i++){
            if (txt.equals(keys_id[i])){
                return true;
            }
        }
        return false;
    }

}
