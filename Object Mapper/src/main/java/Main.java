import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dto.Car;
import dto.User;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String args[]) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        User user = new User();
        user.setName("제니");
        user.setAge(23);

        Car car1 = new Car();
        car1.setName("Audi");
        car1.setCarNumber("11가 1111");
        car1.setType("sedan");

        Car car2= new Car();
        car2.setName("Benz");
        car2.setCarNumber("15가 11ds1");
        car2.setType("sedan");

        List<Car> carList = Arrays.asList(car1, car2);
        user.setCars(carList);

      //  System.out.println(user);

        String json = objectMapper.writeValueAsString(user);
        System.out.println(json);

        JsonNode jsonNode = objectMapper.readTree(json);
        String _name = jsonNode.get("name").asText();
        int _age = jsonNode.get("age").asInt();
        System.out.println("name : "+_name);
        System.out.println("age : "+_age);

        JsonNode cars = jsonNode.get("cars");
        ArrayNode arrayNode = (ArrayNode) cars;

        List<Car> _cars = objectMapper.convertValue(arrayNode, new TypeReference<List<Car>>() {});
        System.out.println(_cars);

        ObjectNode objectNode =(ObjectNode) jsonNode;
        objectNode.put("name","lisa");
        objectNode.put("age",23);
        System.out.println(objectNode.toPrettyString());
    }
}
