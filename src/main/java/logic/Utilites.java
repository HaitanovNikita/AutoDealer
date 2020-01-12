package logic;

public class Utilites {
    protected static final String queryGetAllCars = "Select " +
            "a.id, a.car_price, a.car_make, a.year_issue_car, " +
            "p.horse_power, m.name_model, " +
            "e.type_engine, c.color_car, " +
            "t.type_body " +
            "from Automobile as a " +
            "inner join PowerCar as p on a.power_car = p.ID " +
            "inner join EngineCar as e on  a.engine_car = e.ID " +
            "inner join ColorCar as c on a.color_car = c.ID " +
            "inner join TypeCarBody as t on a.type_car_body= t.ID " +
            "inner join ModelCar as m on a.model_car = m.ID ";
}
