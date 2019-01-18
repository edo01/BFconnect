package org.iisbelluzzifioravanti.app.bfconnect.util;

public final class Tools {

    //the key
    private static String[] keys_id = new String[]{ "beOPEN", "ETC34c", "ETC620", "ETC34d",
            "CHT32c", "CHT645", "CHT3c6", "CHTT00", "IIc1tf", "IIc64x", "IIcpo0", "IIcpfs",
            "TTm65h", "TTms10", "TTmqw0", "TTm111", "EXt6po", "EXt400", "EXt120", "EXt600",
            "FFPP12"
    };

    public static String[] suggerimenti = { "Clicca il qr code in basso a destra e scannerizza le aule!",
            "Cerca i tag NFC e avvicina il telefono per scoprire nuovi laboratori!",
            "Scopri i nostri piani formativi cliccando in basso su scuola!",
            "Hai nostalgia dei nostri laboratori? Riguardali nella pagina apposita in basso!",
            "Chi siamo? Scoprilo cliccando sui 3 puntini in alto a destra!",
            "Hai bisogno di aiuto? Clicca in alto la \"i\" per avere una spiegazione più dettagliata!"
    };

    //not instantiable
    private Tools(){}

    public static boolean CheckId(String txt){
        for (int i=0 ; i< keys_id.length;i++){
            if (txt.equals(keys_id[i])){
                return true;
            }
        }
        return false;
    }

}
