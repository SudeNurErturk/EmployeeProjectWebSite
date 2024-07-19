package com.example.EmployeeWeb.Employee.specification;

public enum SearchOperation {
        EQUALITY(":", "Equalitu"),
        NEGATION("!", "negation"),
        GREATER_THAN(">", "greater_than"),
        LESS_THAN("<", "less_than"),
        LIKE("~", "like"),
        STARTS_WITH("startsWith", "Başlar"),
        ENDS_WITH("endsWith", "Biter"),
        CONTAINS("contains", "İçerir");

        private final String value;
        private final String description;

        SearchOperation(String value, String description) {
            this.value = value;
            this.description = description;
        }

        public String getValue() {
            return value;
        }

        public String getDescription() {
            return description;
        }

        public static final String[] SIMPLE_OPERATION_SET = {":", "!", ">", "<", "~"};


        public static SearchOperation getSimpleOperation(char input) {
            switch (input) {
                case ':':
                    return EQUALITY;
                case '!':
                    return NEGATION;
                case '>':
                    return GREATER_THAN;
                case '<':
                    return LESS_THAN;
                case '~':
                    return LIKE;
                default:
                    return null;
            }
        }

        public static final String ZERO_OR_MORE_REGEX = "*";
    }

