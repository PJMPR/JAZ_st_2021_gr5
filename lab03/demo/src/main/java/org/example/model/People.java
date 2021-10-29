package org.example.model;

import java.util.List;

public class People {
    public final static Person JanKowalski =
            new Person("jan", "kowalski", 30, 1200, Gender.MALE);
    public final static Person MateuszNowak =
            new Person("mateusz", "nowak", 56, 3000, Gender.MALE);
    public final static Person AnnaBuda =
            new Person("anna", "buda", 22, 4500, Gender.FEMALE);
    public final static Person ConchitaWurst =
            new Person("conchita", "wurst", 36, 12000, Gender.OTHER);
    public final static Person MariaKowalewicz=
            new Person("maria", "kowalewicz", 46, 1500, Gender.FEMALE);
    public final static Person AnetaUrban=
            new Person("aneta", "urban", 21, 4200, Gender.FEMALE);
    public final static Person ZdzislawSzydlowski=
            new Person("zdzislaw", "szydlowski", 38, 6800, Gender.MALE);
    public final static Person JanAnrusowski=
            new Person("jan", "anrusowki", 49, 1250, Gender.MALE);
    public final static Person JanuszKowalski=
            new Person("janusz", "kowalski", 18, 1000, Gender.MALE);
    public final static Person DanutaKowalska=
            new Person("danuta", "kowalska", 27, 1200, Gender.FEMALE);

    public static List<Person> Data = List.of(
            JanKowalski,
            MateuszNowak,
            JanuszKowalski,
            AnetaUrban,
            AnnaBuda,
            ConchitaWurst,
            MariaKowalewicz,
            ZdzislawSzydlowski,
            JanAnrusowski,
            DanutaKowalska
    );
}
