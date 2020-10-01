package training.cleancode;


import org.testng.Assert;
import org.testng.annotations.Test;
import training.cleancode.Planes.ExperimentalPlane;
import training.cleancode.Planes.MilitaryPlane;
import training.cleancode.Planes.PassengerPlane;
import training.cleancode.Planes.Plane;
import training.cleancode.models.ClassificationLevel;
import training.cleancode.models.ExperimentalTypes;
import training.cleancode.models.MilitaryType;

import java.util.Arrays;
import java.util.List;

public class AirportTest {
    private static List<Plane> planes = Arrays.asList(
            new PassengerPlane("Boeing-737", 900, 12000, 60500, 164),
            new PassengerPlane("Boeing-737-800", 940, 12300, 63870, 192),
            new PassengerPlane("Boeing-747", 980, 16100, 70500, 242),
            new PassengerPlane("Airbus A320", 930, 11800, 65500, 188),
            new PassengerPlane("Airbus A330", 990, 14800, 80500, 222),
            new PassengerPlane("Embraer 190", 870, 8100, 30800, 64),
            new PassengerPlane("Sukhoi Superjet 100", 870, 11500, 50500, 140),
            new PassengerPlane("Bombardier CS300", 920, 11000, 60700, 196),
            new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryType.BOMBER),
            new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryType.BOMBER),
            new MilitaryPlane("B-52 Stratofortress", 1000, 20000, 80000, MilitaryType.BOMBER),
            new MilitaryPlane("F-15", 1500, 12000, 10000, MilitaryType.FIGHTER),
            new MilitaryPlane("F-22", 1550, 13000, 11000, MilitaryType.FIGHTER),
            new MilitaryPlane("C-130 Hercules", 650, 5000, 110000, MilitaryType.TRANSPORT),
            new ExperimentalPlane("Bell X-14", 277, 482, 500, ExperimentalTypes.HIGH_ALTITUDE, ClassificationLevel.SECRET),
            new ExperimentalPlane("Ryan X-13 Vertijet", 560, 307, 500, ExperimentalTypes.VTOL, ClassificationLevel.TOP_SECRET)
    );

    private static PassengerPlane planeWithMaxPassengerCapacity = new PassengerPlane("Boeing-747", 980, 16100, 70500, 242);

    @Test
    public void hasAtLeastOneMilitaryTransportAircraftTest() {
        Airport airport = new Airport(planes);
        List<MilitaryPlane> transportMilitaryPlanes = airport.getTransportMilitaryPlanes();
        Assert.assertFalse(transportMilitaryPlanes.isEmpty());
    }

    @Test
    public void hasPlaneMaxPassengerCapacityTest() {
        Airport airport = new Airport(planes);
        PassengerPlane expectedPlaneWithMaxPassengersCapacity = airport.getPassengerPlaneWithMaxPassengersCapacity();
        Assert.assertTrue(expectedPlaneWithMaxPassengersCapacity.equals(planeWithMaxPassengerCapacity));
    }


    @Test
    public void CurrentMaxLoadCapacityWithCurrentMaxLoadCapacityAfterSortTest() {
        Airport airport = new Airport(planes);
        boolean nextPlaneMaxLoadCapacityIsHigherThanCurrent = airport.equalCurrentMaxLoadCapacityWithCurrentMaxLoadCapacityAfterSort();
        Assert.assertTrue(nextPlaneMaxLoadCapacityIsHigherThanCurrent);

    }

    @Test
    public void hasAtLeastOneBomberInMilitaryPlanes() {
        Airport airport = new Airport(planes);
        List<MilitaryPlane> bomberMilitaryPlanes = airport.getBomberMilitaryPlanes();
        Assert.assertFalse(bomberMilitaryPlanes.isEmpty());

    }

    @Test
    public void hasAtLeastOneExperimentalPlanesHasClassificationUnclassified() {
        Airport airport = new Airport(planes);
        List<ExperimentalPlane> unclassifiedExperimentalPlanes = airport.getExperimentalPlanesUnclassified();
        Assert.assertFalse(unclassifiedExperimentalPlanes.isEmpty());
    }
}
