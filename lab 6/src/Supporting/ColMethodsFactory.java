package Supporting;

import java.util.ArrayList;

public interface ColMethodsFactory {
    CollectionMethods createInstance();
    CollectionMethods createInstance(String title, int credits, ArrayList<Integer> duration);
}
