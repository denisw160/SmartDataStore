package me.wirries.smartdatastore.service.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.wirries.smartdatastore.service.model.LoginResult;
import me.wirries.smartdatastore.service.model.User;
import me.wirries.smartdatastore.service.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * This is the REST controller for the api of the coffee service.
 * It's provides the access to the sensor data and the configuration.
 *
 * @author denisw
 * @version 1.0
 * @since 08.09.2018
 */
@Api(value = "REST API")
@RestController
@RequestMapping("/api")
public class ApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    private UserRepository userRepository;

//    private SecurityConfiguration securityConfiguration;
//
//    private SensorDataRepository dataRepository;
//    private ConfigRepository configRepository;

//    /**
//     * Constructor with AutoWiring the dependencies.
//     *
//     * @param securityConfiguration Configuration of the WebSecurity
//     * @param dataRepository        Repository for the coffee sensor data
//     * @param configRepository      Repository for the config data
//     */
//    @Autowired
//    public ApiController(SecurityConfiguration securityConfiguration,
//                         SensorDataRepository dataRepository,
//                         ConfigRepository configRepository) {
//        this.securityConfiguration = securityConfiguration;
//        this.dataRepository = dataRepository;
//        this.configRepository = configRepository;
//    }

    /**
     * This simple method return the result of the login. If the the user is authenticated,
     * it return success=true.
     *
     * @param principal {@link Principal} of the current login
     * @return result of the login
     */
    @ApiOperation(
            value = "Perform the login",
            notes = "Return the state of the login. If the login ist success, the field success is true."
    )
    @GetMapping("/login")
    public LoginResult login(Principal principal) {
        if (principal == null) {
            return new LoginResult("notAuthenticated", false);
        }

        return new LoginResult(principal.getName(), true);
    }

    @ApiOperation(
            value = "Get all users",
            notes = "Return all users from the database."
    )
    @GetMapping("/users")
    public List<User> users() {
        return userRepository.findAll();
    }

//    /**
//     * Return the status of the coffee sensors.
//     *
//     * @return Alive-Status
//     */
//    @GetMapping("/alive")
//    public Alive getAlive() {
//        LOGGER.debug("Get the alive status ...");
//        Alive alive = new Alive();
//        alive.setTimestamp(new Date());
//
//        SensorData latest = getDataLatest();
//        if (latest == null || latest.getTimestamp() == null) {
//            alive.setAlive(false);
//        } else {
//            Date aliveDate = new Date(System.currentTimeMillis() - (30000));
//            alive.setAlive(latest.getTimestamp().getTime() >= aliveDate.getTime());
//        }
//
//        return alive;
//    }

//    /**
//     * Return all sensor data from the coffee sensor.
//     *
//     * @return List with all data
//     */
//    @GetMapping("/data")
//    public List<SensorData> getData() {
//        LOGGER.debug("Get all coffee sensor data ...");
//        return dataRepository.findAll();
//    }

//    /**
//     * Get latest data.
//     *
//     * @return SensorData
//     */
//    @GetMapping("/data/latest")
//    public SensorData getDataLatest() {
//        LOGGER.debug("Get latest coffee sensor data");
//        SensorData data = dataRepository.findTopByOrderByTimestampDesc();
//        return (data != null) ? data : new SensorData();
//    }

//    /**
//     * Get latest data data for the last 7 days.
//     * The data will be aggregated and shown on a hourly basis. Only the last hour will shown on an minute basis.
//     *
//     * @return SensorData
//     */
//    @GetMapping("/data/7days")
//    public List<SensorData> getData7Days() {
//        LOGGER.debug("Get sensor data for the last 7 days");
//        Date timestamp = addDays(truncate(new Date(), DATE), -6);
//        return aggregateSensorData(dataRepository.findAfterTimestamp(timestamp));
//    }
//
//    // optimize and cleanup code - reduce code duplication
//    private List<SensorData> aggregateSensorData(List<SensorData> data) {
//        List<SensorData> list = new ArrayList<>();
//        Date now = new Date();
//
//        SensorData n = null;
//        final List<Double> weightList = new ArrayList<>();
//        final List<Boolean> allocatedList = new ArrayList<>();
//        for (SensorData s : data) {
//            // Pre-condition: first element
//            if (n == null) {
//                n = new SensorData(truncate(s.getTimestamp(), HOUR));
//                list.add(n);
//
//                weightList.clear();
//                allocatedList.clear();
//            }
//
//            if ((now.getTime() - s.getTimestamp().getTime()) <= (60 * 60 * 1000)) {
//                // Last 60 minutes
//                if (isSameMinute(n.getTimestamp(), s.getTimestamp())) {
//                    // Collecting the values of the same hour
//                    weightList.add(s.getWeight());
//                    allocatedList.add(s.getAllocated());
//
//                } else {
//                    // Switch in the minute - update values and start with next minute
//                    OptionalDouble average = weightList.stream().mapToDouble(a -> a).average();
//                    n.setWeight((average.isPresent()) ? average.getAsDouble() : 0.0);
//
//                    boolean containsFalse = allocatedList.stream().anyMatch(t -> !t);
//                    n.setAllocated(!containsFalse);
//
//                    // New element
//                    n = new SensorData(truncate(s.getTimestamp(), MINUTE));
//                    list.add(n);
//
//                    weightList.clear();
//                    weightList.add(s.getWeight());
//
//                    allocatedList.clear();
//                    allocatedList.add(s.getAllocated());
//                }
//
//
//            } else {
//                // Older data
//                if (isSameHour(n.getTimestamp(), s.getTimestamp())) {
//                    // Collecting the values of the same hour
//                    weightList.add(s.getWeight());
//                    allocatedList.add(s.getAllocated());
//
//                } else {
//                    // Switch in the hour - update values and start with next hour
//                    OptionalDouble average = weightList.stream().mapToDouble(a -> a).average();
//                    n.setWeight((average.isPresent()) ? average.getAsDouble() : 0.0);
//
//                    boolean containsFalse = allocatedList.stream().anyMatch(t -> !t);
//                    n.setAllocated(!containsFalse);
//
//                    // New element
//                    n = new SensorData(truncate(s.getTimestamp(), HOUR));
//                    list.add(n);
//
//                    weightList.clear();
//                    weightList.add(s.getWeight());
//
//                    allocatedList.clear();
//                    allocatedList.add(s.getAllocated());
//                }
//            }
//        }
//
//        // handle the last element
//        if (!weightList.isEmpty()) {
//            OptionalDouble average = weightList.stream().mapToDouble(a -> a).average();
//            n.setWeight((average.isPresent()) ? average.getAsDouble() : 0.0);
//
//            boolean containsFalse = allocatedList.stream().anyMatch(t -> !t);
//            n.setAllocated(!containsFalse);
//        }
//
//        return list;
//    }

//    /**
//     * Get data by id.
//     *
//     * @param id Id of the data entry
//     * @return SensorData
//     */
//    @GetMapping("/data/{id}")
//    public SensorData getDataById(@PathVariable("id") String id) {
//        LOGGER.debug("Get coffee sensor data for {}", id);
//        return dataRepository.findById(id).orElse(null);
//    }

//    /**
//     * Return all consumption data from the coffee sensor.
//     *
//     * @return List with all data
//     */
//    @GetMapping("/consumption")
//    public List<Consumption> getConsumption() {
//        LOGGER.debug("Collecting all consumption data ...");
//        return aggregateConsumption(getData());
//    }

//    /**
//     * Get latest consumption data (from today).
//     *
//     * @return Consumption
//     */
//    @GetMapping("/consumption/latest")
//    public Consumption getConsumptionLatest() {
//        LOGGER.debug("Get latest consumption data");
//        Date timestamp = addDays(truncate(new Date(), DATE), 0);
//        List<Consumption> data = aggregateConsumption(dataRepository.findAfterTimestamp(timestamp));
//        return (data.isEmpty()) ? new Consumption() : data.get(0);
//    }

//    /**
//     * Get latest consumption data for the last 7 days.
//     *
//     * @return Consumption
//     */
//    @GetMapping("/consumption/7days")
//    public List<Consumption> getConsumption7Days() {
//        LOGGER.debug("Get consumption data for the last 7 days");
//        Date timestamp = addDays(truncate(new Date(), DATE), -6);
//        return aggregateConsumption(dataRepository.findAfterTimestamp(timestamp));
//    }

//    /**
//     * Creates one consumption object per day.
//     *
//     * @param data List of sensor data / ordered by timestamp
//     * @return List of consumption
//     */
//    private List<Consumption> aggregateConsumption(List<SensorData> data) {
//        List<Consumption> list = new ArrayList<>();
//
//        boolean state = false;
//        Consumption c = null;
//        for (SensorData s : data) {
//            if (c == null || !isSameDay(c.getDay(), s.getTimestamp())) {
//                c = new Consumption(truncate(s.getTimestamp(), DATE));
//                list.add(c);
//            }
//            if (state && !s.getAllocated()) {
//                c.incrementConsumption();
//            }
//            state = s.getAllocated();
//        }
//
//        return list;
//    }

//    /**
//     * Get latest configuration.
//     *
//     * @return Config
//     */
//    @GetMapping("/config")
//    public Config getConfig() {
//        LOGGER.debug("Get latest configuration");
//        Config config = configRepository.findTopByOrderByTimestampDesc();
//        return (config != null) ? config : new Config(2.8);
//    }

//    /**
//     * Update the configuration.
//     *
//     * @param config Config
//     */
//    @PostMapping("/config")
//    public GenericResponse<Config> setConfig(@RequestBody Config config) {
//        LOGGER.debug("Storing the configuration {}", config);
//        if (config != null) {
//            configRepository.deleteAll();
//
//            config.setId(ObjectId.get().toString());
//            config.setTimestamp(new Date());
//            configRepository.save(config);
//
//            return new GenericResponse<>(200, "Save success", config);
//        }
//
//        return new GenericResponse<>(500, "No configuration posted.");
//    }

}
