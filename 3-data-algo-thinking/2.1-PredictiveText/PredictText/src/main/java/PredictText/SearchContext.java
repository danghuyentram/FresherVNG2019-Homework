package PredictText;

public class SearchContext {
    Dictionary dictionary;

    private static SearchContext instance ;

    private SearchContext(Dictionary dictionary){
        this.dictionary = dictionary;
    }

    public static SearchContext getInstance(Dictionary dictionary){
        instance = new SearchContext(dictionary);
        return instance;
    }

    public boolean contains(String word){
        return dictionary.contains(word);
    }
}
