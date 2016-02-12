/*
 * Copyright (C) 2015 The Android Open Source Project
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

package com.android.messaging.mmslib.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

import com.android.messaging.mmslib.SqliteWrapper;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility functions for the Messaging Service
 */
public class MmsSmsUtils {
    private final static String TAG = "MmsSmsUtils";

    private MmsSmsUtils() {
        // Forbidden being instantiated.
    }

    /**
     * mailbox         =       name-addr
     * name-addr       =       [display-name] angle-addr
     * angle-addr      =       [CFWS] "<" addr-spec ">" [CFWS]
     */
    public static final Pattern NAME_ADDR_EMAIL_PATTERN =
            Pattern.compile("\\s*(\"[^\"]*\"|[^<>\"]+)\\s*<([^<>]+)>\\s*");

    public static String extractAddrSpec(final String address) {
        final Matcher match = NAME_ADDR_EMAIL_PATTERN.matcher(address);

        if (match.matches()) {
            return match.group(2);
        }
        return address;
    }

    /**
     * Returns true if the address is an email address
     *
     * @param address the input address to be tested
     * @return true if address is an email address
     */
    public static boolean isEmailAddress(final String address) {
        if (TextUtils.isEmpty(address)) {
            return false;
        }

        final String s = extractAddrSpec(address);
        final Matcher match = Patterns.EMAIL_ADDRESS.matcher(s);
        return match.matches();
    }

    /**
     * Returns true if the number is a Phone number
     *
     * @param number the input number to be tested
     * @return true if number is a Phone number
     */
    public static boolean isPhoneNumber(final String number) {
        if (TextUtils.isEmpty(number)) {
            return false;
        }

        final Matcher match = Patterns.PHONE.matcher(number);
        return match.matches();
    }

    /**
     * Helper functions for the "threads" table used by MMS and SMS.
     */
    public static final class Threads implements android.provider.Telephony.ThreadsColumns {
        private static final String[] ID_PROJECTION = { BaseColumns._ID };
        private static final Uri THREAD_ID_CONTENT_URI = Uri.parse(
                "content://mms-sms/threadID");
        public static final Uri CONTENT_URI = Uri.withAppendedPath(
                android.provider.Telephony.MmsSms.CONTENT_URI, "conversations");

        // No one should construct an instance of this class.
        private Threads() {
        }

        /**
         * This is a single-recipient version of
         * getOrCreateThreadId.  It's convenient for use with SMS
         * messages.
         */
        public static long getOrCreateThreadId(final Context context, final String recipient) {
            final Set<String> recipients = new HashSet<String>();

            recipients.add(recipient);
            return getOrCreateThreadId(context, recipients);
        }

        /**
         * Given the recipients list and subject of an unsaved message,
         * return its thread ID.  If the message starts a new thread,
         * allocate a new thread ID.  Otherwise, use the appropriate
         * existing thread ID.
         *
         * Find the thread ID of the same set of recipients (in
         * any order, without any additions). If one
         * is found, return it.  Otherwise, return a unique thread ID.
         */
        public static long getOrCreateThreadId(
                final Context context, final Set<String> recipients) {
            final Uri.Builder uriBuilder = THREAD_ID_CONTENT_URI.buildUpon();

            for (String recipient : recipients) {
                if (isEmailAddress(recipient)) {
                    recipient = extractAddrSpec(recipient);
                }

                uriBuilder.appendQueryParameter("recipient", recipient);
            }

            final Uri uri = uriBuilder.build();
            //if (DEBUG) Rlog.v(TAG, "getOrCreateThreadId uri: " + uri);

            final Cursor cursor = SqliteWrapper.query(context, context.getContentResolver(),
                    uri, ID_PROJECTION, null, null, null);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        return cursor.getLong(0);
                    } else {
                        Log.e(TAG, "getOrCreateThreadId returned no rows!");
                    }
                } finally {
                    cursor.close();
                }
            }

            Log.e(TAG, "getOrCreateThreadId failed");
            throw new IllegalArgumentException("Unable to find or allocate a thread ID.");
        }
    }
}
