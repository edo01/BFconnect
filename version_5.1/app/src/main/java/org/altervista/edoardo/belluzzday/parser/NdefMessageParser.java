
/*
 * Copyright (C) 2010 The Android Open Source Project
 * Modified by Sylvain Saurel for a tutorial
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.altervista.edoardo.belluzzday.parser;

import android.app.Activity;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;

import org.altervista.edoardo.belluzzday.R;
import org.altervista.edoardo.belluzzday.record.ParsedNdefRecord;
import org.altervista.edoardo.belluzzday.record.SmartPoster;
import org.altervista.edoardo.belluzzday.record.TextRecord;
import org.altervista.edoardo.belluzzday.record.UriRecord;

import java.util.ArrayList;
import java.util.List;


public class NdefMessageParser {

    private NdefMessageParser() {
    }

    public static List<ParsedNdefRecord> parse(NdefMessage message) {
        return getRecords(message.getRecords());
    }

    public static List<ParsedNdefRecord> getRecords(NdefRecord[] records) {
        List<ParsedNdefRecord> elements = new ArrayList<ParsedNdefRecord>();

        for (final NdefRecord record : records) {
            if (UriRecord.isUri(record)) {
                elements.add(UriRecord.parse(record));
            } else if (TextRecord.isText(record)) {
                elements.add(TextRecord.parse(record));
            } else if (SmartPoster.isPoster(record)) {
                elements.add(SmartPoster.parse(record));
            } else {
                elements.add(new ParsedNdefRecord() {
                    public String str() {
                        return new String(record.getPayload());
                    }
                });
            }
        }

        return elements;
    }
}