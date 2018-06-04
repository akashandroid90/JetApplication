package app.jetpackapplication.worker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.StaleDataException;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.work.Worker;
import app.jetpackapplication.data.Contacts;
import app.jetpackapplication.data.SampleDatabase;

public class ContactWorker extends Worker {

    @NonNull
    @Override
    public WorkerResult doWork() {
        int contacts = updateContacts();
        return contacts==0?WorkerResult.FAILURE:WorkerResult.SUCCESS;
    }

    private int updateContacts() {
        String[] projection = {ContactsContract.CommonDataKinds.Phone.CONTACT_ID, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.PHOTO_URI};
        String selection = "LENGTH(" + ContactsContract.CommonDataKinds.Phone.NUMBER + ") >= 10";// AND " + ContactsContract.CommonDataKinds.Phone.CONTACT_LAST_UPDATED_TIMESTAMP + ">'" + mPrefrence.getLongValue(ContactPrefrenceKeys.LAST_SYNC, 0) + "'";
        Cursor data = getApplicationContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projection, selection, null, null);
        int count = 0;
        if (data != null) {
//            ContentValues contentValues;
            String value, code, number;
//            String phone = mPrefrence.getStringValue(ContactPrefrenceKeys.PHONE_NUMBER, "");
            if (data.getCount() > 0 && !data.isClosed()) {
                List<Contacts> dataList = new ArrayList<>();
                try {
                    while (data.moveToNext()) {
                        value = data.getString(1);
                        if (TextUtils.isEmpty(value))
                            continue;
                        value = value.replaceAll(" ", "").replaceAll("-", "").replaceAll("\\p{P}", "").replaceAll("\\s+", "");
                        if (value.startsWith("+") && value.contains("-")) {
                            code = value.substring(0, value.indexOf("-"));
                            number = value.substring(value.indexOf("-"));
                        } else if (value.startsWith("+") && value.contains(" ")) {
                            code = value.substring(0, value.indexOf(" "));
                            number = value.substring(value.indexOf(" "));
                        } else if (value.startsWith("0")) {
                            code = "+91";
                            number = value.substring(value.indexOf("0") + 1);
                        } else {
                            code = "+91";
//                                        if (value.startsWith("91"))
//                                            number = value.substring(value.indexOf("91") + 2);
//                                        else
                            if (value.startsWith("+91"))
                                number = value.substring(value.indexOf("+91") + 3);
                            else if (value.length() == 10)
                                number = value;
                            else continue;
                        }
//                        if (number.equalsIgnoreCase(phone))
//                            continue;
                        Contacts contacts=new Contacts();
                        contacts.number=number.replaceAll(" ", "").replaceAll("-", "").replaceAll("\\p{P}", "").replaceAll("\\s+", "");
                        contacts.code=code;
                        contacts.name=data.getString(2);
//                        contentValues = new ContentValues();
//                        contentValues.put(ContactDataContract.ContactDatabase.CONTACT_CODE, code);
//                        contentValues.put(ContactDataContract.ContactDatabase.CONTACT_NUMBER, number.replaceAll(" ", "").replaceAll("-", "").replaceAll("\\p{P}", "").replaceAll("\\s+", ""));
//                        contentValues.put(ContactDataContract.ContactDatabase.CONTACT_ID, data.getString(0));
//                        contentValues.put(ContactDataContract.ContactDatabase.CONTACT_NAME, data.getString(2));
//                        contentValues.put(ContactDataContract.ContactDatabase.CONTACT_IMAGE, data.getString(3));
//                        contentValues.put(ContactDataContract.ContactDatabase.USER_ID, mPrefrence.getStringValue(ContactPrefrenceKeys.USERID, ""));
                        dataList.add(contacts);
                    }
                } catch (StaleDataException e) {
                    e.printStackTrace();
                }
                data.close();
                if (dataList.size() > 0) {
                    Contacts[] values = new Contacts[dataList.size()];
                    long[] longs = SampleDatabase.getInstance(getApplicationContext()).contacts().insertAll(dataList.toArray(values));
                    Log.e(getClass().getSimpleName(),String.valueOf(longs[longs.length-1]));
                    count= (int) longs[longs.length-1];
//                    getApplicationContext().getContentResolver().bulkInsert(ContactDataContract.ContactDatabase.CONTENT_URI, dataList.toArray(values));
                }
            }
        }
        return count;
    }
}
