import java.util.*;

public class AllLess {
        public static ArrayList<String> findAllLess(String[] s, int x) {
            ArrayList<String> result = new ArrayList<>();

            for (int i = 0; i < s.length; i++) {
                if (s[i] == null) {
                    continue;
                }
                String str = s[i].trim(); // to cut out unnecessary spaces at the end and beginning
                if (str.length() < x && str.length() > 0) {
                    result.add(str);
                }
            }

            return result;
        }

       
}
