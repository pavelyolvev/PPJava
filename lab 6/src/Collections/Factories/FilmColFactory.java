package Collections.Factories;

import Collections.FilmCollection;
import Supporting.ColMethodsFactory;
import Supporting.CollectionMethods;

import java.util.ArrayList;

public class FilmColFactory implements ColMethodsFactory {
    @Override
    public CollectionMethods createInstance() {
        return new FilmCollection();
    }

    @Override
    public CollectionMethods createInstance(String title, int credits, ArrayList<Integer> duration) {
        try {
            return new FilmCollection(title, credits, duration);
        } catch (FilmCollection.FilmColException e) {
            throw new RuntimeException(e);
        }
    }
}
