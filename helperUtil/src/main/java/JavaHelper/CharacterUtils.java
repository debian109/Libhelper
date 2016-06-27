package JavaHelper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Character Handling Tools
 */
public class CharacterUtils {

    /**
     * Compare new and old string, returning the need to retain the character and position of
     *
     * @param oldText Original string
     * @param newText Emerging string
     * @return Reserved characters, and mobile location
     */
    public static List<CharacterDiffResult> diff(CharSequence oldText, CharSequence newText) {

        List<CharacterDiffResult> differentList = new ArrayList<>();
        Set<Integer> skip = new HashSet<>();

        for (int i = 0; i < oldText.length(); i++) {
            char c = oldText.charAt(i);
            for (int j = 0; j < newText.length(); j++) {
                if (!skip.contains(j) && c == newText.charAt(j)) {
                    skip.add(j);
                    CharacterDiffResult different = new CharacterDiffResult();
                    different.c = c;
                    different.fromIndex = i;
                    different.moveIndex = j;
                    differentList.add(different);
                    break;
                }
            }
        }
        return differentList;
    }

    public static int needMove(int index, List<CharacterDiffResult> differentList) {
        for (CharacterDiffResult different : differentList) {
            if (different.fromIndex == index) {
                return different.moveIndex;
            }
        }
        return -1;
    }

    public static boolean stayHere(int index, List<CharacterDiffResult> differentList) {
        for (CharacterDiffResult different : differentList) {
            if (different.moveIndex == index) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the x coordinate of the index movement form the original string string move to the
     * new index in the schedule for the progress of
     * @param from The original form of the string subscript
     * @param move New string remove subscript
     * @param progress Progress moving 0-1
     * @param startX The initial value of the new string displacement
     * @param oldStartX The initial value of the original string displacement
     * @param gaps The original pitch for each character string
     * @param oldGaps New string for each character spacing
     * @return
     */
    public static float getOffset(int from, int move, float progress, float startX, float oldStartX, float[] gaps, float[] oldGaps) {

        // Calculating a target point
        float dist = startX;
        for (int i = 0; i < move; i++) {
            dist += gaps[i];
        }

        // Calculate the current point
        float cur = oldStartX;
        for (int i = 0; i < from; i++) {
            cur += oldGaps[i];
        }

        return cur + (dist - cur) * progress;

    }

}
