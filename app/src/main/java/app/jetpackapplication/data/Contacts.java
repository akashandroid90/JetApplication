package app.jetpackapplication.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.ContentValues;
import android.provider.BaseColumns;

@Entity(tableName = Contacts.TABLE_NAME)
public class Contacts {
    /** The name of the Cheese table. */
    public static final String TABLE_NAME = "contacts";

    /** The name of the ID column. */
    public static final String COLUMN_ID = BaseColumns._ID;

    /** The name of the name column. */
    public static final String COLUMN_NAME = "name";

    /** The name of the name column. */
    public static final String COLUMN_CODE = "code";

    //The name of the name column. */
    public static final String COLUMN_NUMBER = "number";

    /** The unique ID of the cheese. */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    public long id;

    /** The name of the cheese. */
    @ColumnInfo(name = COLUMN_NAME)
    public String name;

    /** The name of the cheese. */
    @ColumnInfo(name = COLUMN_CODE)
    public String code;

    /** The name of the cheese. */
    @ColumnInfo(name = COLUMN_NUMBER)
    public String number;

    /**
     * Create a new {@link Contacts} from the specified {@link ContentValues}.
     *
     * @param values A {@link ContentValues} that at least contain {@link #COLUMN_NAME}.
     * @return A newly created {@link Contacts} instance.
     */
    public static Contacts fromContentValues(ContentValues values) {
        final Contacts contacts = new Contacts();
        if (values.containsKey(COLUMN_ID)) {
            contacts.id = values.getAsLong(COLUMN_ID);
        }
        if (values.containsKey(COLUMN_NAME)) {
            contacts.name = values.getAsString(COLUMN_NAME);
        }
        if (values.containsKey(COLUMN_CODE)) {
            contacts.code = values.getAsString(COLUMN_CODE);
        }
        if (values.containsKey(COLUMN_NUMBER)) {
            contacts.number = values.getAsString(COLUMN_NUMBER);
        }
        return contacts;
    }
}
