package com.geektrust.backend.appConfig;
import com.geektrust.backend.commands.AddDriverCommand;
import com.geektrust.backend.commands.AddRiderCommand;
import com.geektrust.backend.commands.BillCommand;
import com.geektrust.backend.commands.CommandInvoker;
import com.geektrust.backend.commands.MatchCommand;
import com.geektrust.backend.commands.StartRideCommand;
import com.geektrust.backend.commands.StopRideCommand;
import com.geektrust.backend.repositories.BillRepository;
import com.geektrust.backend.repositories.DriverRepository;
import com.geektrust.backend.repositories.RideRepository;
import com.geektrust.backend.repositories.RiderRepository;
import com.geektrust.backend.services.BillService;
import com.geektrust.backend.services.DistanceCalculatorService;
import com.geektrust.backend.services.DriverService;
import com.geektrust.backend.services.RideService;
import com.geektrust.backend.services.RiderService;

public class ApplicationConfig {
  
   
    private final DriverRepository driverRepository = new DriverRepository();
    private final RiderRepository riderRepository = new RiderRepository();
    private final RideRepository rideRepository = new RideRepository();
    private final BillRepository billRepository = new BillRepository();
    
    
    private final DistanceCalculatorService distanceCalculatorService= new DistanceCalculatorService();
    private final BillService billService = new BillService(billRepository);
    private final RideService rideService = new RideService(driverRepository, riderRepository, rideRepository,billService,distanceCalculatorService);
    private final DriverService driverService=new DriverService(driverRepository);
    private final RiderService riderService=new RiderService(riderRepository);
    


    private final AddDriverCommand addDriverCommand = new AddDriverCommand(driverService);
    private final AddRiderCommand addRiderCommand = new AddRiderCommand(riderService);
    private final MatchCommand matchCommand = new MatchCommand(rideService);
    private final StartRideCommand startRideCommand = new StartRideCommand(rideService);
    private final StopRideCommand stopRideCommand = new StopRideCommand(rideService);
    private final BillCommand billCommand = new BillCommand(rideService);

    private final CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker(){
        commandInvoker.register("ADD_DRIVER", addDriverCommand);
        commandInvoker.register("ADD_RIDER", addRiderCommand);
        commandInvoker.register("MATCH", matchCommand);
        commandInvoker.register("START_RIDE", startRideCommand);
        commandInvoker.register("STOP_RIDE", stopRideCommand);
        commandInvoker.register("BILL", billCommand);
        return commandInvoker;
    }
}
