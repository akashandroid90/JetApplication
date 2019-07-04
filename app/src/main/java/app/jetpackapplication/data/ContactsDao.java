/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package app.jetpackapplication.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import android.database.Cursor;


/**
 * Data access object for Cheese.
 */
@Dao
public interface ContactsDao {

    /**
     * Counts the number of cheeses in the table.
     *
     * @return The number of cheeses.
     */
    @Query("SELECT COUNT(*) FROM " + Contacts.TABLE_NAME)
    int count();

    /**
     * Inserts a cheese into the table.
     *
     * @param cheese A new cheese.
     * @return The row ID of the newly inserted cheese.
     */
    @Insert
    long insert(Contacts cheese);

    /**
     * Inserts multiple cheeses into the database
     *
     * @param cheeses An array of new cheeses.
     * @return The row IDs of the newly inserted cheeses.
     */
    @Insert
    long[] insertAll(Contacts[] cheeses);

    /**
     * Select all cheeses.
     *
     * @return A {@link Cursor} of all the cheeses in the table.
     */
    @Query("SELECT * FROM " + Contacts.TABLE_NAME)
    Cursor selectAll();

    /**
     * Select a cheese by the ID.
     *
     * @param id The row ID.
     * @return A {@link Cursor} of the selected cheese.
     */
    @Query("SELECT * FROM " + Contacts.TABLE_NAME + " WHERE " + Contacts.COLUMN_ID + " = :id")
    Cursor selectById(long id);

    /**
     * Delete a Contacts by the ID.
     *
     * @param id The row ID.
     * @return A number of Contactss deleted. This should always be {@code 1}.
     */
    @Query("DELETE FROM " + Contacts.TABLE_NAME + " WHERE " + Contacts.COLUMN_ID + " = :id")
    int deleteById(long id);

    /**
     * Update the Contacts. The Contacts is identified by the row ID.
     *
     * @param Contacts The Contacts to update.
     * @return A number of Contactss updated. This should always be {@code 1}.
     */
    @Update
    int update(Contacts Contacts);

}
