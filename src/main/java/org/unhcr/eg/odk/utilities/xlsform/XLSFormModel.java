/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unhcr.eg.odk.utilities.xlsform;

/**
 *
 * @author Stanyslas Matayo
 */
public class XLSFormModel {

    public enum Type_Field {

        START("start"),
        END("end"),
        TODAY("today"),
        DEVICE_ID("deviceid"),
        SUBSCRIBER_ID("subscriberid"),
        SIM_SERIAL("simserial"),
        PHONE_NUMBER("phonenumber"),
        BEGIN_GROUP("begin group"),
        END_GROUP("end group"),
        BEGIN_REPEAT("begin repeat"),
        END_REPEAT("end repeat"),
        INTEGER("integer"),
        DECIMAL("decimal"),
        TEXT("text"),
        SELECT_ONE("select_one"),
        SELECT_MULTIPLE("select_multiple"),
        NOTE("note"),
        GEO_POINT("geopoint"),
        GEO_TRACE("geotrace"),
        GEO_SHAPE("geoshape"),
        DATE("date"),
        TIME("time"),
        DATE_TIME("dateTime"),
        IMAGE("image"),
        AUDIO("audio"),
        VIDEO("video"),
        BARCODE("barcode"),
        CALCULATE("calculate");

        private final String value;

        public String value() {
            return value;
        }

        Type_Field(String value) {
            this.value = value;
        }
    }

    public enum Appearance {

        MULTILINE("multiline"),
        MINIMAL("minimal"),
        QUICK("quick"),
        MONTH_YEAR("month-year"),
        YEAR("year"),
        HORIZONTAL_COMPACT("horizontal-compact"),
        HORIZONTAL("horizontal"),
        LIKERT("likert"),
        QUICK_COMPACT("quickcompact"),
        FIELD_LIST("field-list"),
        LABEL("label"),
        LIST_NOLABEL("list-nolabel"),
        TABLE_LIST("table-list"),
        SIGNATURE("signature"),
        DRAW("draw");

        private final String value;

        public String value() {
            return value;
        }

        Appearance(String value) {
            this.value = value;
        }
    }

    public enum SheetName {

        SURVEY("survey"),
        CHOICES("choices"),
        SETTINGS("settings");

        private final String value;

        public String value() {
            return value;
        }

        SheetName(String value) {
            this.value = value;
        }
    }

    public enum SheetColumn {

        SURVEY_TYPE("type", SheetName.SURVEY),
        SURVEY_NAME("name", SheetName.SURVEY),
        SURVEY_DEFAULT("default", SheetName.SURVEY, true),
        SURVEY_CHOICE_FILTER("choice_filter", SheetName.SURVEY, false, true),
        SURVEY_HINT("hint", SheetName.SURVEY, true),
        SURVEY_CONSTRAINT("constraint", SheetName.SURVEY, true),
        SURVEY_CONSTRAINT_MESSAGE("constraint_message", SheetName.SURVEY, true),
        SURVEY_APPEARANCE("appearance", SheetName.SURVEY, false, true),
        SURVEY_RELEVANT("relevant", SheetName.SURVEY, false, true),
        SURVEY_READ_ONLY("read_only", SheetName.SURVEY, false, true),
        SURVEY_CALCULATION("calculation", SheetName.SURVEY, false, true),
        SURVEY_LABEL("label", SheetName.SURVEY, true, false),
        CHOICE_LIST_NAME("list_name", SheetName.CHOICES, false, false),
        CHOICE_NAME("name", SheetName.CHOICES, false, false),
        CHOICE_LABEL("label", SheetName.CHOICES, true, false),
        SETTINGS_FORM_TITLE("form_title", SheetName.SETTINGS, false, false),
        SETTINGS_FORM_ID("form_id", SheetName.SETTINGS, false, true),
        SETTINGS_PUBLIC_KEY("public_key", SheetName.SETTINGS, false, false),
        SETTINGS_SUBMISSION_URL("submission_url", SheetName.SETTINGS, false, false),
        SETTINGS_DEFAULT_LANGUAGE("default_language", SheetName.SETTINGS, false, false);

        private final String value;
        private final SheetName sheetName;
        private final boolean supportLangage;
        private final boolean supportExpression;

        public SheetName sheetName() {
            return sheetName;
        }

        public static SheetColumn getSheetColumn(String name) {
            for (SheetColumn sheet : SheetColumn.values()) {
                if (sheet.name().equals(name)) {
                    return sheet;
                }
            }
            return null;
        }

        public String value() {
            return value;
        }

        public boolean supportLangage() {
            return supportLangage;
        }

        public boolean supportExpression() {
            return supportExpression;
        }

        SheetColumn(String value, SheetName name) {
            this(value, name, false, false);
        }

        SheetColumn(String value, SheetName name, boolean supportLangage) {
            this(value, name, supportLangage, false);
        }

        private SheetColumn(String value, SheetName sheetName, boolean supportLangage, boolean supportExpression) {
            this.value = value;
            this.sheetName = sheetName;
            this.supportLangage = supportLangage;
            this.supportExpression = supportExpression;
        }

    }

}
