package org.iisbelluzzifioravanti.app.bfconnect;

public final class Keys {

    private static String[] keys_id = new String[]{ "1", "A34cb", "BBwcb", "ccrcb", "tt2cb"};

    //not instantiable
    private Keys(){}

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
