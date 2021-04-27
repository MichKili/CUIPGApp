package pl.pg.cui.nbp;

import pl.pg.cui.nbp.config.ConnectionConfiguration;
import pl.pg.cui.nbp.data.ExchangeRates;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;



public class App {




    public static void main(String[] args) throws IOException {
        System.out.println("Program rekrutacyjny przedstawiający notowania kursu kupna i sprzedaży USD od podanej daty do bieżącej daty wraz z różnicą wartości (kupna i sprzedaży) pomiędzy dniami. ");
        System.out.println("Autor: Michał Kilian");
        System.out.println("Prosze podać datę w formacie YYYY-MM-DD: (Wyjście - wciśnij 'e')");
        ConnectionConfiguration.initialization();
    }

}
