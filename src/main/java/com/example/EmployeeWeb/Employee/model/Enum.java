package com.example.EmployeeWeb.Employee.model;

public class Enum {


    public enum WorkingPlace{
        OFFICE(0,"OFFICE"),
        REMOTE(1, "REMOTE"),
        HYBRID(2, " HYBRID");



        private final int value;
        private final String description;


        WorkingPlace(int value, String description) {
            this.value = value;
            this.description = description;
        }


        public int getValue() {
            return value;
        }

        public String getDescription() {
            return description;
        }

        public static WorkingPlace fromValue(int value) {
            for (WorkingPlace workingPlace : WorkingPlace.values()) {
                if (workingPlace.value == value) {
                    return workingPlace;
                }
            }
            throw new IllegalArgumentException("Invalid value: " + value);
        }
    }

    public enum Team{
        Java(0,"Java"),
        Angular(1, "Angular"),
        C4C(2, " C4C"),
        Abap(3,"Abap"),
        DevOps(4, "DevOps"),
        Basis(5, " Basis");


        private final int value;
        private final String description;


        Team(int value, String description) {
            this.value = value;
            this.description = description;
        }




        public static Team fromValue(int value) {
            for (Team team : Team.values()) {
                if (team.value == value) {
                    return team;
                }
            }
            throw new IllegalArgumentException("Invalid value: " + value);
        }


    }

    public enum ContractType{
        Süreli(0,"Süreli"),
        Süresiz(1, "Süresiz");



        private final int value;
        private final String description;


        ContractType(int value, String description) {
            this.value = value;
            this.description = description;
        }

        public int getValue() {
            return value;
        }

        public String getDescription() {
            return description;
        }

        public static ContractType fromValue(int value) {
            for (ContractType contractType : ContractType.values()) {
                if (contractType.value == value) {
                    return contractType;
                }
            }
            throw new IllegalArgumentException("Invalid value: " + value);
        }
    }
}
