package Collections.Factories;

import Collections.TheaterCollection;
import Supporting.ColMethodsFactory;
import Supporting.CollectionMethods;

import java.util.ArrayList;

public class ThColFactory implements ColMethodsFactory {
    @Override
    public CollectionMethods createInstance() {
        return new TheaterCollection();
    }

    @Override
    public CollectionMethods createInstance(String title, int credits, ArrayList<Integer> duration) {
        try {
            return new TheaterCollection(title, credits, duration);
        } catch (TheaterCollection.ThColException e) {
            throw new RuntimeException(e);
        }
    }
}
