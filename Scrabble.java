import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
class Scrabble {

    private String word;
    Scrabble(String word) {
        this.setWord(word);
    }
    public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}

    int getScore() {
        if(this.word.isEmpty() || this.word.length() == 0)return 0;
		List<Integer> scoreList = getScoreList(); 
		return scoreList.stream().reduce((a,b)->a+b).get();
    }
    private List<Integer> getScoreList() {
		List<Character> arrayWord = Arrays
                                    .stream(word.split(""))
				   					.map(e->Arrays.asList(e.split(" ")))
				   					.flatMap(List::stream)
				   					.map(e->Character
                                    .toUpperCase(e.charAt(0)))
				   					.collect(Collectors.toList());

		 Map<List<Character>, Integer> scrabbleMapping =  Stream.of(new Object[][]{
			 { 
       Arrays.asList('A','E','I','O','U','L','N','R','S','T'),1},
			 { Arrays.asList('D','G'),2},
			 { Arrays.asList('B','C','M','P'),3},
			 { Arrays.asList('F','H','V','W','Y'),4},
			 { Arrays.asList('K'),5},
			 { Arrays.asList('J','X'),8},
			 { Arrays.asList('Q','Z'),10}
		 }).collect(Collectors.toMap(data -> ((List<Character>) 
                                data[0]), data -> (Integer) data[1]));
	 	List<Integer> list = arrayWord.stream()
	 			  					  .map(e->scrabbleMapping
                                           .entrySet().stream()			
                                           .filter(a->a.getKey()
                                           .contains(e))							                               .findFirst()
	 			  						   .get()
	 			  						   .getValue())
	 			  						  
                                    .collect(Collectors.toList());							 
		return list;
	}


}
