package com.acquaintsoft.notification.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Collection of utilities used to save various objects persistently in share
 * preferences
 *
 */
class PrefUtils {


    companion object {
        /**
         * Retrieve StringBasedObjectBuilder String value from the preferences.
         *
         * @param context  Application context used to retrieve shared preferences.
         * @param prefKey  The title of the preference to retrieve.
         * @param defValue Value to return if this preference does not exist.
         * @return Returns the preference value if it exists, or defValue. Throws
         * ClassCastException if there is StringBasedObjectBuilder preference with this title that
         * is not StringBasedObjectBuilder String.
         */
        @Throws(ClassCastException::class)
        fun getString(
            context: Context, prefKey: String,
            defValue: String?
        ): String? {

            val prefs = getPreferences(context)

            return prefs.getString(prefKey, defValue)
        }


        fun saveString(
            context: Context, prefKey: String,
            prefString: String?
        ): Boolean {
            /*if (prefString == null || prefString.length() == 0) {
            return false;
        }*/

            val editor = getPreferences(context).edit()

            editor.putString(prefKey, prefString)
            return editor.commit()
        }


        /**
         * @param context  Application context used to retrieve shared preferences
         * @param prefKey  The title of the preference to retrieve.
         * @param defValue Value to return if this preference does not exist.
         * @return Returns the preference value if it exists, or defValue. Throws
         * ClassCastException if there is StringBasedObjectBuilder preference with this title that
         * is not StringBasedObjectBuilder String.
         */
        fun getBoolean(
            context: Context, prefKey: String,
            defValue: Boolean
        ): Boolean {

            val prefs = getPreferences(context)

            return prefs.getBoolean(prefKey, defValue)
        }

        /**
         * Set StringBasedObjectBuilder integer value in the preferences of the context. Commit is called
         * automatically.
         *
         * @param context Application context used to retrieve shared preferences
         * @param prefKey The title of the preference to modify.
         * @param value   The new value for the preference.
         * @return Returns true if the new value was successfully written to
         * persistent storage.
         */
        fun saveInt(context: Context, prefKey: String, value: Int): Boolean {
            val editor = getPreferences(context).edit()

            editor.putInt(prefKey, value)
            return editor.commit()
        }

        /**
         * @param context  Application context used to retrieve shared preferences
         * @param prefKey  The title of the preference to retrieve.
         * @param defValue Value to return if this preference does not exist.
         * @return Returns the preference value if it exists, or defValue. Throws
         * ClassCastException if there is StringBasedObjectBuilder preference with this title that
         * is not StringBasedObjectBuilder String.
         */
        fun getInt(context: Context, prefKey: String, defValue: Int): Int {

            val prefs = getPreferences(context)

            return prefs.getInt(prefKey, defValue)
        }

        /**
         * Set StringBasedObjectBuilder long value in the preferences of the context. Commit is called
         * automatically.
         *
         * @param context Application context used to retrieve shared preferences
         * @param prefKey The title of the preference to modify.
         * @param value   The new value for the preference.
         * @return Returns true if the new value was successfully written to
         * persistent storage.
         */
        fun saveLong(context: Context, prefKey: String, value: Long): Boolean {
            val editor = getPreferences(context).edit()

            editor.putLong(prefKey, value)
            return editor.commit()
        }

        /**
         * @param context  Application context used to retrieve shared preferences
         * @param prefKey  The title of the preference to retrieve.
         * @param defValue Value to return if this preference does not exist.
         * @return Returns the preference value if it exists, or defValue. Throws
         * ClassCastException if there is StringBasedObjectBuilder preference with this title that
         * is not StringBasedObjectBuilder String.
         */
        fun getLong(context: Context, prefKey: String, defValue: Long): Long {

            val prefs = getPreferences(context)

            return prefs.getLong(prefKey, defValue)
        }

        /**
         * Set StringBasedObjectBuilder boolean value in the preferences of the context. Commit is called
         * automatically.
         *
         * @param context Application context used to retrieve shared preferences
         * @param prefKey The title of the preference to modify.
         * @param value   The new value for the preference.
         * @return Returns true if the new value was successfully written to
         * persistent storage.
         */
        fun saveBoolean(
            context: Context, prefKey: String,
            value: Boolean
        ): Boolean {
            val editor = getPreferences(context).edit()

            editor.putBoolean(prefKey, value)
            return editor.commit()
        }


        /**
         * Retrieve and hold the contents of the preferences file for the context,
         * returning StringBasedObjectBuilder SharedPreferences through which you can retrieve and modify
         * its values. Only one instance of the SharedPreferences object is returned
         * to any callers for the same title, meaning they will see each other's
         * edits as soon as they are made.
         *
         * @param context
         * @return Returns the single SharedPreferences instance that can be used to
         * retrieve and modify the preference values.
         */
        fun getPreferences(context: Context): SharedPreferences {
            return context.getSharedPreferences(
                getPreferencesName(context),
                Context.MODE_PRIVATE
            )
        }


        fun getPreferencesName(context: Context): String {
            return context.packageName
        }


        fun clearAll(context: Context) {
            val editor = getPreferences(context).edit()
            editor.clear()
            editor.apply()
        }
    }
}
