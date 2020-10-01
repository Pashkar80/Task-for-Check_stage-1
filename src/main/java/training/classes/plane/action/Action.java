package training.classes.plane.action;



import training.classes.plane.abstractplane.Plane;
import training.classes.plane.entity.CargoPlane;
import training.classes.plane.entity.PassangerPlane;

import java.util.Scanner;

/**
 * Created by user on 08.03.2019.
 */
public class Action {
    static Print print ;

    public static int capacity(Plane[] aircompany) {
        int capacity = 0;
        for (int i = 0; i < aircompany.length; i++) {
            if(aircompany[i].getClass().getName().equals("classes.plane.entity.PassangerPlane") ) {
                capacity = capacity + ((PassangerPlane)aircompany[i]).getPassangerCapacity();
            }

        }
        return capacity;
    }

    public static int carrying(Plane[] aircompany) {
        int carrying = 0;
        for (int i = 0; i < aircompany.length; i++) {
            if (aircompany[i].getClass().getName().equals("classes.plane.entity.CargoPlane")) {

                            carrying = carrying + ((CargoPlane)aircompany[i]).getCarrying();
            }
        }


            return carrying;
    }


    public static Plane[] sorteOfRange(Plane[] aircompany) {
        Plane maxRange;
        for (int i = 0; i < aircompany.length; i++) {
            for (int j = i; j < aircompany.length; j++) {
                if (aircompany[i].getRange() < aircompany[j].getRange()) {
                    maxRange = aircompany[j];
                    aircompany[j] = aircompany[i];
                    aircompany[i] = maxRange;
                }
            }
        }
        return aircompany;
    }

    public static void rangeFuelConsumption(Plane[] aircompany) {
        Scanner scanner = new Scanner(System.in);
        boolean emptyChoice = true;
        System.out.println("Enter from: ");
        int from = scanner.nextInt();
        System.out.println("Enter to: ");
        int to = scanner.nextInt();
        for (Plane plane : aircompany) {
            if (plane.getFuelConsumption() > from && plane.getFuelConsumption() <= to) {
                print.printRangeFuelConsumption(plane);
                emptyChoice = false;
            }

        }
        if (emptyChoice) {
            print.printRangeFuelConsumption(null);

        }
    }


}
