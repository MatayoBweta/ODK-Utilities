/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unhcr.eg.odk.utilities.xlsform.model.json;

import java.util.List;

/**
 *
 * @author Stanyslas Matayo
 */
public abstract class JsonQuestion {

    private final String type;
    private String description;
    private String title;
    private String formPath;

    public JsonQuestion(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFormPath() {
        return formPath;
    }

    public void setFormPath(String formPath) {
        this.formPath = formPath;
    }

    public abstract class Simple<T> extends JsonQuestion {

        public abstract class SimpleComparable<T> extends Simple<T> {

            private T minimum, maximum, exclusiveMinimum, exclusiveMaximum;

            public SimpleComparable(String type) {
                super(type);
            }

            public T getMinimum() {
                return minimum;
            }

            public void setMinimum(T minimum) {
                this.minimum = minimum;
            }

            public T getMaximum() {
                return maximum;
            }

            public void setMaximum(T maximum) {
                this.maximum = maximum;
            }

            public T getExclusiveMinimum() {
                return exclusiveMinimum;
            }

            public void setExclusiveMinimum(T exclusiveMinimum) {
                this.exclusiveMinimum = exclusiveMinimum;
            }

            public T getExclusiveMaximum() {
                return exclusiveMaximum;
            }

            public void setExclusiveMaximum(T exclusiveMaximum) {
                this.exclusiveMaximum = exclusiveMaximum;
            }

            public class IntegerValue extends SimpleComparable<Integer> {

                public IntegerValue() {
                    super("integer");
                }

            }

            public class NumberValue extends SimpleComparable<Number> {

                public NumberValue() {
                    super("number");
                }

            }

            public class StringValue extends SimpleComparable<String> {

                public StringValue() {
                    super("string");
                }

            }
        }

        private T defaultValue;
        private int maxLength;
        private boolean readOnly;
        private boolean required;

        public Simple(java.lang.String type) {
            super(type);
        }

        public T getDefaultValue() {
            return defaultValue;
        }

        public void setDefaultValue(T defaultValue) {
            this.defaultValue = defaultValue;
        }

        public int getMaxLength() {
            return maxLength;
        }

        public void setMaxLength(int maxLength) {
            this.maxLength = maxLength;
        }

        public boolean isReadOnly() {
            return readOnly;
        }

        public void setReadOnly(boolean readOnly) {
            this.readOnly = readOnly;
        }

        public boolean isRequired() {
            return required;
        }

        public void setRequired(boolean required) {
            this.required = required;
        }

        public class BooleanValue extends Simple<Boolean> {

            public BooleanValue() {
                super("boolean");
            }

        }

    }

    public class JsonObjectQuestion extends JsonQuestion {

        private List<JsonQuestion> properties;

        public JsonObjectQuestion() {
            super("object");
        }

        public List<JsonQuestion> getProperties() {
            return properties;
        }

        public void setProperties(List<JsonQuestion> properties) {
            this.properties = properties;
        }

    }

    public class JsonArrayQuestion extends JsonQuestion {

        private JsonQuestion items;
        private int minItems, maxItmes;

        public JsonArrayQuestion() {
            super("array");
        }

        public JsonQuestion getItems() {
            return items;
        }

        public void setItems(JsonQuestion items) {
            this.items = items;
        }

        public int getMinItems() {
            return minItems;
        }

        public void setMinItems(int minItems) {
            this.minItems = minItems;
        }

        public int getMaxItmes() {
            return maxItmes;
        }

        public void setMaxItmes(int maxItmes) {
            this.maxItmes = maxItmes;
        }
    }

}
