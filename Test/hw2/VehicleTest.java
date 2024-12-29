package hw2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {
    private Car car;
    private Motorcycle motorcycle;

    @BeforeEach
    public void setup(){
        car = new Car("Nissan", "GT-5", 2010);
        motorcycle = new Motorcycle("Java", "J1", 1987);
    }

    @Test
    public void testCarisInstanceOfVehicle(){
        assertTrue(car instanceof Vehicle, "Car является экземпляром Vehicle");
    }

    @Test
    public void testCarHasFourWheels(){
        assertEquals(4, car.getNumWheels(), "У машины должно быть 4 колеса");
    }

    @Test
    public void testMotorcycleHasTwoWheels(){
        assertEquals(2, motorcycle.getNumWheels(), "У мотоцикла должно быть 2 колеса");
    }

    @Test
    public void testCarSpeedOnTestDrive(){
        car.testDrive();
        assertEquals(60, car.getSpeed(), "Скорость машины должна быть 60 при тесте");
    }

    @Test
    public void testMotorcycleSpeedOnTestDrive(){
        motorcycle.testDrive();
        assertEquals(75, motorcycle.getSpeed(), "Скорость мотоцикла должна быть 75 при тесте");
    }

    @Test
    public void testCarSpeedAfterParking(){
        car.testDrive(); // Разгоняем до тестовой скорости
        car.park(); // Паркуем
        assertEquals(0, car.getSpeed(), "Скорость машины должна быть 0 после парковки");
    }

    public void testMotorcycleSpeedAfterParking(){
        motorcycle.testDrive(); // Разгоняем до тестовой скорости
        motorcycle.park(); // Паркуем
        assertEquals(0, motorcycle.getSpeed(), "Скорость мотоцикла должна быть 0 после парковки");
    }

    //

}