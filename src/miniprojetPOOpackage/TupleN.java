package miniprojetPOOpackage;
import java.util.List;
public class TupleN {
	    private final List<String> elements;

	    public TupleN(List<String> elements) {
	        this.elements = List.copyOf(elements); // rend la liste immuable
	    }

	    public List<String> getElements() {
	        return elements;
	    }

	    @Override
	    public String toString() {
	        return "(" + String.join(", ", elements) + ")";
	    }
	}



