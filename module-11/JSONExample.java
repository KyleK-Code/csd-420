/*Author: Kyle Klausen
  Date: 12/19/25
  Assignment: Module-11
  Class: CSD-420
  Description: Code example for JSON example in Module 11. Displays information presented in a clean matter.
   */

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonExample {
    public static void main(String[] args) {
        JSONObject person = new JSONObject();
        person.put("name", "Kyle Klausen");
        person.put("age", 25);

        JSONArray hobbies = new JSONArray();
        hobbies.put("coding");
        hobbies.put("gaming");
        hobbies.put("working out");

        person.put("hobbies", hobbies);

        System.out.println(person.toString(2)); // Pretty print JSON
    }
}
