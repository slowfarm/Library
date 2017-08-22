package ru.tander.mylibrary;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class PolicyRepository {

    private final String dbName = "ru.tander.emmtanderstore.tanderstoredb";
    private final Uri CONTACT_URI = Uri.parse("content://"+dbName);

    public List<ApplicationPolicies> getPoliciesList(Context context, String udid) {
        List<ApplicationPolicies> policies = new ArrayList<>();
        try {
            Cursor cursor = context.getContentResolver().query(CONTACT_URI, null, udid, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                while (cursor.moveToNext()) {
                    ApplicationPolicies policy = new ApplicationPolicies();
                    policy.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    policy.setName(cursor.getString(cursor.getColumnIndex("name")));
                    policies.add(policy);
                }
                cursor.close();
            } else {
                Log.e("ContentProvider", "DATA IS EMPTY");
            }
        } catch (IllegalArgumentException error) {
            error.printStackTrace();
            throw error;
        }
        return policies;
    }
}
