package training.cleancode;


import training.cleancode.Planes.ExperimentalPlane;
import training.cleancode.Planes.MilitaryPlane;
import training.cleancode.Planes.PassengerPlane;
import training.cleancode.Planes.Plane;
import training.cleancode.models.ClassificationLevel;
import training.cleancode.models.MilitaryType;

import java.util.*;


public class Airport {
    private List<? extends Plane> planes;


    public List<PassengerPlane> getPassengerPlane() {
        List<? extends Plane> planesList = this.planes;
        List<PassengerPlane> passengerPlaneList = new ArrayList<>();
        for (Plane plane : planesList) {
            if (plane instanceof PassengerPlane) {
                passengerPlaneList.add((PassengerPlane) plane);
            }
        }
        return passengerPlaneList;
    }

    public List<MilitaryPlane> getMilitaryPlanes() {
        List<MilitaryPlane> militaryPlanes = new ArrayList<>();
        for (Plane plane : planes) {
            if (plane instanceof MilitaryPlane) {
                militaryPlanes.add((MilitaryPlane) plane);
            }
        }
        return militaryPlanes;
    }

    public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
        List<PassengerPlane> passengerPlanes = getPassengerPlane();
        PassengerPlane planeWithMaxCapacity = passengerPlanes.get(0);
        for (int i = 0; i < passengerPlanes.size(); i++) {
            if (passengerPlanes.get(i).getPassengersCapacity() > planeWithMaxCapacity.getPassengersCapacity()) {
                planeWithMaxCapacity = passengerPlanes.get(i);
            }
        }


        return planeWithMaxCapacity;
    }

    public List<MilitaryPlane> getTransportMilitaryPlanes() {
        List<MilitaryPlane> transportMilitaryPlanes = new ArrayList<>();
        List<MilitaryPlane> militaryPlanes = getMilitaryPlanes();
        for (int i = 0; i < militaryPlanes.size(); i++) {
            MilitaryPlane plane = militaryPlanes.get(i);
            if (plane.getType() == MilitaryType.TRANSPORT) {
                transportMilitaryPlanes.add(plane);
            }
        }
        return transportMilitaryPlanes;
    }

    public List<MilitaryPlane> getBomberMilitaryPlanes() {
        List<MilitaryPlane> bomberMilitaryPlanes = new ArrayList<>();
        List<MilitaryPlane> militaryPlanes = getMilitaryPlanes();
        for (int i = 0; i < militaryPlanes.size(); i++) {
            MilitaryPlane plane = militaryPlanes.get(i);
            if (plane.getType() == MilitaryType.BOMBER) {
                bomberMilitaryPlanes.add(plane);
            }
        }
        return bomberMilitaryPlanes;

    }

    public List<ExperimentalPlane> getExperimentalPlanesUnclassified() {
        List<ExperimentalPlane> unclassifiedExperimentalPlanes = new ArrayList<>();
        List<ExperimentalPlane> experimentalPlanes = getExperimentalPlanes();
        for (int i = 0; i < experimentalPlanes.size(); i++) {
            ExperimentalPlane experimentalPlane = experimentalPlanes.get(i);
            if (experimentalPlane.getClassificationLevel() == ClassificationLevel.UNCLASSIFIED) {
                unclassifiedExperimentalPlanes.add(experimentalPlane);
            }
        }
        return unclassifiedExperimentalPlanes;

    }

    public List<ExperimentalPlane> getExperimentalPlanes() {
        List<ExperimentalPlane> experimentalPlanes = new ArrayList<>();
        for (Plane plane : planes) {
            if (plane instanceof ExperimentalPlane) {
                experimentalPlanes.add((ExperimentalPlane) plane);
            }
        }
        return experimentalPlanes;
    }

    public Airport sortByMaxDistance() {
        Collections.sort(planes, new Comparator<Plane>() {
            public int compare(Plane planeIsCompared , Plane planeWhichCompared) {
                return planeIsCompared.getMaxFlightDistance() - planeWhichCompared.getMaxFlightDistance();
            }
        });
        return this;
    }



    public Airport sortByMaxSpeed() {
        Collections.sort(planes, new Comparator<Plane>() {
            public int compare(Plane planeIsCompared, Plane planeWhichCompared ) {
                return planeIsCompared.getmaxSpeed() - planeWhichCompared.getmaxSpeed();
            }
        });
        return this;
    }

    public Airport sortByMaxLoadCapacity() {
        Collections.sort(planes, new Comparator<Plane>() {
            public int compare(Plane planeIsCompared, Plane planeWhichCompared) {
                return planeIsCompared.getMaxLoadCapacity() - planeWhichCompared.getMaxLoadCapacity();
            }
        });
        return this;
    }

    public boolean equalCurrentMaxLoadCapacityWithCurrentMaxLoadCapacityAfterSort(){
        Airport airport = new Airport(planes);
        airport.sortByMaxLoadCapacity();
        List<? extends Plane> planesSortedByMaxLoadCapacity = airport.getPlanes();
        boolean nextPlaneMaxLoadCapacityIsHigherThanCurrent = true;
        for (int i = 0; i < planesSortedByMaxLoadCapacity.size() - 1; i++) {
            Plane currentPlane = planesSortedByMaxLoadCapacity.get(i);
            Plane nextPlane = planesSortedByMaxLoadCapacity.get(i + 1);
            if (currentPlane.getMaxLoadCapacity() > nextPlane.getMaxLoadCapacity()) {
                nextPlaneMaxLoadCapacityIsHigherThanCurrent = false;
                break;
            }
        }
        return  nextPlaneMaxLoadCapacityIsHigherThanCurrent;
    }


    public List<? extends Plane> getPlanes() {
        return planes;
    }

    private void print(Collection<? extends Plane> collection) {
        Iterator<? extends Plane> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Plane plane = iterator.next();
            System.out.println(plane);
        }
    }

    @Override
    public String toString() {
        return "Airport{" +
                "Planes=" + planes.toString() +
                '}';
    }


    public Airport(List<? extends Plane> planes) {
        this.planes = planes;
    }

}
