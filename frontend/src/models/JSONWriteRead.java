package models;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


public class JSONWriteRead {

    //Array for category
    String[] arrayC = new String[2];

    //Array for task
    String[] arrayT = new String[2];

    public void JSONWrite() throws FileNotFoundException {
        // creating JSONObject
        JSONObject jo = new JSONObject();


        // for address data, first create LinkedHashMap
        Map m = new LinkedHashMap(1);
        m.put("Category", "NOT COMPLETED");

        // putting address to JSONObject
        jo.put("Task", m);

        // for tasks
        JSONArray jt = new JSONArray();

        m = new LinkedHashMap(2);
        m.put("Category", "Class");
        m.put("Task", "CS3331");

        // adding map to list
        jt.add(m);

        m = new LinkedHashMap(2);
        m.put("Category", "Meeting");
        m.put("Task", "TSP Meeting");

        // adding map to list
        jt.add(m);

        // putting tasks to JSONObject
        jo.put("Tasks", jt);

        // writing JSON to file:"JSONExample.json" in cwd
        PrintWriter pw = new PrintWriter("Calendar.json");
        pw.write(jo.toJSONString());

        pw.flush();
        pw.close();
    }

    public void JSONRead() throws IOException, ParseException {
        // parsing file "JSONExample.json"
        Object obj = new JSONParser().parse(new FileReader("Calendar.json"));

        // typecasting obj to JSONObject
        JSONObject jo = (JSONObject) obj;


        // getting task (map)
        Map task = ((Map)jo.get("Task"));

        //Int i
        int i = 0;

        // iterating address Map
        Iterator<Map.Entry> itr1 = task.entrySet().iterator();
        while(itr1.hasNext()) {
            Map.Entry pair = itr1.next();
            arrayC[i] = (String) pair.getValue();
            i++;
            //System.out.println(pair.getKey() + " : " + pair.getValue());
        }

        // getting tasks (arrays)
        JSONArray jt = (JSONArray) jo.get("Tasks");

        // iterating phoneNumbers
        Iterator itr2 = jt.iterator();

        //int j
        int j = 0;

        //int k
        int k = 0;

        while(itr2.hasNext())
        {
            itr1 = ((Map) itr2.next()).entrySet().iterator();
            while(itr1.hasNext()) {
                Map.Entry pair = itr1.next();

                if(pair.getKey().equals("Category")) {
                    arrayC[j] = (String) pair.getValue();
                    j++;
                }

                if(pair.getKey().equals("Task")) {
                    arrayT[k] = (String) pair.getValue();
                    k++;
                }

                //System.out.println(pair.getKey() + " : " + pair.getValue());
            }
        }
    }

    public String[] getArrayC() {
        return arrayC;/**/
    }

    public String[] getArrayT() {
        return arrayT;
    }

//    public static void main(String[] args) throws IOException, ParseException {
//        JSONWriteRead json = new JSONWriteRead();
//        json.JSONWrite();
//        json.JSONRead();
//        System.out.println(Arrays.toString(json.getArrayT()));
//    }
}


