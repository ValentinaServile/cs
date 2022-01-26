package datastructures.suffixarray;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SuffixArrayTest {

    @Test
    void shouldConstructWithoutError() {
        new SuffixArray("bananaban");
    }

    @Test
    void shouldAllowToFindNumberOfUniqueSubstrings() {
        SuffixArray suffixArray = new SuffixArray("azaza");

        int result = suffixArray.uniqueSubstrings();

        assertEquals(9, result);
    }

    @Test
    void shouldAllowToFindLongestRepeatingSubstring() {
        SuffixArray suffixArray = new SuffixArray("abracadabra");

        String result = suffixArray.longestRepeatedSubstring();

        assertEquals("abra", result);
    }

    @Test
    void shouldFindLongestCommonSubstrings() {
        SuffixArray suffixArray = new SuffixArray("aabc", "bcdc", "bcde","cded");

        List<String> result = suffixArray.longestCommonSubstringsAmong(2);

        assertEquals(2, result.size());
        assertEquals("bcd", result.get(0));
        assertEquals("cde", result.get(1));
    }
}