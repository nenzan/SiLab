package id.compunerd.silab.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    public static final String SP_SILAB_APP = "SILAB";

    public static final String SP_ID_PERUSAHAAN = "spIdPerusahaan";
    public static final String SP_NAMA = "spNama";
    public static final String SP_EMAIL = "spEmail";
    public static final String SP_TOKEN = "spToken";

    public static final String SP_SUDAH_LOGIN = "spSudahLogin";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context) {
        sp = context.getSharedPreferences(SP_SILAB_APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value) {
        spEditor.putString(keySP, value);
        spEditor.apply();
    }

    public void saveSPInt(String keySP, int value) {
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value) {
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public String getSPNama() {
        return sp.getString(SP_NAMA, "");
    }

    public String getSPEmail() {
        return sp.getString(SP_EMAIL, "");
    }

    public String getSPToken() {
        return sp.getString(SP_TOKEN, "");
    }

    public String getSPIdPerusahaan() {
        return sp.getString(SP_ID_PERUSAHAAN, "");
    }

    public Boolean getSPSudahLogin() {
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }
}
