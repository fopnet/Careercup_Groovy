import org.apache.commons.lang.StringUtils

/**
 * Created with IntelliJ IDEA.
 * User: EII5
 * Date: 18/03/15
 * Time: 14:46
 * To change this template use File | Settings | File Templates.
 */
class ReverseSentence {
    String reverseSentenceEconomicMemory(String s) {
        List words = s.split("\\s")
        for (int i=0 ; i <Math.abs(words.size()/2) ; i++) {
            words[i, words.size()-i-1] = words[words.size()-i-1, i]
        }
        words.join(" ")
    }

    String reverseSentence(String s) {
        List words = s.split("\\s")
        def result = ""
        // percorrendo somente até a metade do vetor
        for (int i=words.size() -1; i >=0 ; i--) {
            result += "${words[i]}"
            if (i>0)
                result += " "
        }
        result
    }

    /* Tentar criar a string percorrendo até a metade */
    String reverseSentenceOptimized(String s) {
        List<String> words = s.split("\\s")

        StringBuilder result = new StringBuilder(StringUtils.repeat (" ", s.length()))
        int left = 0
        int right = s.length() -1
        for (int i=0 ; i <Math.abs(words.size()/2) ; i++) {
            result.insert(left, words[words.size()-i-1])
            left += words[words.size()-i-1].length() + 1

//            right -= words[i].length()
            result.insert(right, words[i])
            right--
        }
        result.toString()
    }

    static void main(String[] args) {
        println new ReverseSentence().reverseSentenceEconomicMemory("I wish you a merry christmas")
        println new ReverseSentence().reverseSentence("I wish you a merry christmas")
        println new ReverseSentence().reverseSentenceOptimized("I wish you a merry christmas")
    }


}
