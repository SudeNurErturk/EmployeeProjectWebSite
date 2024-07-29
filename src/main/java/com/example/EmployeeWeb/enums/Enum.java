package com.example.EmployeeWeb.enums;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class Enum {


    public enum WorkingPlace{
        OFFICE(1,"OFFICE"),
        REMOTE(2, "REMOTE"),
        HYBRID(3, " HYBRID");



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
        Java(1,"Java"),
        Angular(2, "Angular"),
        C4C(3, " C4C"),
        Abap(4,"Abap"),
        DevOps(5, "DevOps"),
        Basis(6, " Basis");


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
        Süreli(1,"Süreli"),
        Süresiz(2, "Süresiz");



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

    public enum MaritalStatus{
        Single(1,"Single"),
        Engaged(2, "Engaged"),
        Married(3, "Married"),
        Divorced(4,"Divorced"),
        Seperated(5, "Seperated"),
        Widowed(6,"Widowed");


        private final int value;
        private final String description;


        MaritalStatus(int value, String description) {
            this.value = value;
            this.description = description;
        }

        public int getValue() {
            return value;
        }

        public String getDescription() {
            return description;
        }

        public static MaritalStatus fromValue(int value) {
            for (MaritalStatus marialStatus : MaritalStatus.values()) {
                if (marialStatus.value == value) {
                    return marialStatus;
                }
            }
            throw new IllegalArgumentException("Invalid value: " + value);
        }
    }

    public enum MilitaryService {
        Not_Applicable(1, "Not Applicable"),
        Completed(2, "Completed");


        private final int value;
        private final String description;


        MilitaryService(int value, String description) {
            this.value = value;
            this.description = description;
        }

        public int getValue() {
            return value;
        }

        public String getDescription() {
            return description;
        }

        public static MilitaryService fromValue(int value) {
            for (MilitaryService militaryService : MilitaryService.values()) {
                if (militaryService.value == value) {
                    return militaryService;
                }
            }
            throw new IllegalArgumentException("Invalid value: " + value);
        }
    }


    @Getter
    public enum Level {
        L0(1,"Level 0"),
        L1(2, "Level 1"),
        L2(3, "Level 2"),
        L3(4, "Level 3"),
        L4(5,"Level 4"),
        L5(6, "Level 5");

        private final int value;
        private final String description;

        Level(int value, String description) {
            this.value = value;
            this.description = description;
        }
        public int getValue() {
            return value;
        }

        public String getDescription() {
            return description;
        }

        public static Level fromValue(int value) {
            for (Level level : Level.values()) {
                if (level.value == value) {
                    return level;
                }
            }
            throw new IllegalArgumentException("Invalid value: " + value);
        }


    }
}
