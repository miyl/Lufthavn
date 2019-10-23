package dk.kea.shared;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class Calculator implements Comparator {


    int taxiIndLille = 12;
    int taxiIndMellem = 10;
    int taxiIndStor = 15;
    int passagerUdLille = 5;
    int passagerUdMellem = 10;
    int passagerUdStor = 15;
    int bagageUdLille = 10;
    int bagageUdMellem = 15;
    int bagageUdStor = 20;
    int fuelLille = 10;
    int fuelMellem = 20;
    int fuelStor = 30;
    int klarLille = 30;
    int klarMellem = 40;
    int klarStor = 60;
    int bagageIndLille = 15;
    int bagageIndMellem = 25;
    int bagageIndStor = 35;
    int passagererIndLille = 15;
    int passagererIndMellem = 20;
    int passagererIndStor = 35;
    int taxiUdAfgang = 10;
    int taxiToFromWaitlille = 8;
    int taxiToFromWaitMellem = 5;
    int taxiToFromWaitStor = 5;
    int personaleNabo = 0;
    int personaleSammeTerminal = 5;
    int personaleAndenTerminal = 10;



    public boolean choseFlight(ArrayList<Flights> flights) throws ParseException {

        System.out.println("Antal taxiteams");
        Scanner scanner = new Scanner(System.in);
        int taxiTeams = scanner.nextInt();

        System.out.println("Antal bagageteams");
        int bagageTeams = scanner.nextInt();

        System.out.println("Antal brændstofteams");
        int fuelTeams = scanner.nextInt();

        System.out.println("Antal klargøringsteams:");
        int readyTeams = scanner.nextInt();


        Date date1 = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2019-22-10 17:00");
        flights.add(new Flights("Boeing", "SAS", "large", "SAS", 1, date1,
                new Gate(10, "large", "Terminal 2")));


        Date date2 = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2019-22-10 17:15");
        flights.add(new Flights("Boeing", "SAS", "large", "SAS", 1, date2,
                new Gate(12, "large", "Terminal 2")));

        Date date3 = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2019-22-10 17:30");
        flights.add(new Flights("Boeing", "SAS", "large", "SAS", 1, date3,
                new Gate(8, "medium", "Terminal 2")));

        //if

        return false;
    }

    public boolean isNeighbour(int gate1, int gate2){
        if(gate1-gate2 == 2 || gate1-gate2 == -2){
            return true;
        }
        return false;
    }



    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }

    @Override
    public Comparator reversed() {
        return null;
    }

    @Override
    public Comparator thenComparing(Comparator other) {
        return null;
    }

    @Override
    public Comparator thenComparingInt(ToIntFunction keyExtractor) {
        return null;
    }

    @Override
    public Comparator thenComparingLong(ToLongFunction keyExtractor) {
        return null;
    }

    @Override
    public Comparator thenComparingDouble(ToDoubleFunction keyExtractor) {
        return null;
    }

    @Override
    public Comparator thenComparing(Function keyExtractor) {
        return null;
    }

    @Override
    public Comparator thenComparing(Function keyExtractor, Comparator keyComparator) {
        return null;
    }
}
