public class ctcione{


public boolean isUniqueChars(String str){
    boolean[] char_set = new boolean[128];
    if(str.length() > 128){
        System.out.println("false");
        return false;
    }
    for(int i =0; i< str.length(); i++){
        int val = str.charAt(i);
        if(char_set[val]){
            System.out.println("false");
            return false;
        }
        char_set[val] = true;
    }
    System.out.println("true");
    return true;
}

public String sort(String s){
    char[] content = s.toCharArray();
    java.util.Arrays.sort(content);
    return new String(content);
}

public boolean permutation(String s, String t){
    if(s.length() != t.length()){
        return false;
    }
    return sort(s).equals(sort(t));
}

public void replaceSpaces(char[] str , int trueLength){
    int spacecount = 0;
    int index = 0;
    for(int i = 0; i < str.length; i++){
        if(str[i]== ' '){
            spacecount++;
        }
    } 
    index = trueLength + spacecount * 2; 
    char[] replacedstr = new char[index];
    System.out.println(spacecount);
    if(trueLength < str.length){
        str[trueLength] = '\0';
    }
    for(int i = trueLength - 1; i >= 0; i--){
         if(str[i] == ' '){
            replacedstr[index - 1] = '0';
            replacedstr[index - 2] = '2';
            replacedstr[index - 3] = '%';
            index = index - 3;
         } else{
            replacedstr[index - 1] = str[i];
            index--;
         }
    }    
    System.out.println(replacedstr);    
} 

public boolean isPalindrome(String phrase){
    int[] table = buildCharFreq(phrase);
    return checkMaxOneOdd(table);
}

public boolean checkMaxOneOdd(int[] table){
    boolean oddFound = false;
    for(int count: table){
        if(count % 2 == 1){
            if(oddFound){
                return false;
            }
            oddFound = true;
        }
    }
    return true; 
}

public int getCharNumber(Character c){
    int a = Character.getNumericValue('a');
    int z = Character.getNumericValue('z');
    int val = Character.getNumericValue(c);
    if(a <= val && z >= val){
        return val;
    }else{
        return -1;
    }
}

public int[] buildCharFreq(String phrase){
    int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
    for(char c : phrase.toCharArray()){
       int x = getCharNumber(c);
       if(x != -1){
           table[x] ++;
       } 
    }
    return table;
}

public boolean oneStringAway(String str1, String str2){
    if(str1.length() == str2.length()){
        return oneEditString(str1, str2);
    }else if (str1.length() + 1 == str2.length() ){
        return oneInsertString (str2, str1);
    }else if (str1.length() - 1 == str2.length()){
        return oneInsertString(str1, str2);
    }
    return false;
}

public boolean oneEditString(String first, String second){
    boolean difference = false;
    for(int i = 0; i < first.length(); i++){
        if(first.charAt(i) != second.charAt(i)){
            if(difference){
                return false;
            }
            difference = true;
        }
    }
    return true;
}

//apple
//aple

public boolean oneInsertString(String strBig, String strSmall){
    int smallCount = 0;
    int bigCount = 0;
    for(int i = 0; i < strSmall.length(); i++){
        if(strSmall.charAt(smallCount) != strBig.charAt(bigCount)){
            if(smallCount != bigCount){
                return false;
            }
            ++bigCount;
        }else{
            ++smallCount;
            ++bigCount;
        }
    }
    return true;
}

public String compress(String str){
    StringBuilder compressed = new StringBuilder();
    int countConsecutive = 0;
    for (int i = 0; i < str.length(); i++){
        countConsecutive++;
        if(i + 1 >= str.length() || str.charAt(i) != str.charAt(i+1)){
            compressed.append(str.charAt(i));
            compressed.append(countConsecutive);
            countConsecutive = 0;
        }
    
    }
    return compressed.length() < str.length() ? compressed.toString() : str;
}

public boolean rotate(int[][] matrix){
    if(matrix.length == 0 || matrix.length != matrix[0].length) return false;
    int n = matrix.length;
    for (int layer = 0; layer < n/2; layer++){
        int first = layer;
        int last = n - 1 - layer;
        for (int i = first; i < last; i++){
            int offset = i - first;
            int top = matrix[first][i];
            matrix[first][i] = matrix[last-offset][first];
            matrix[last-offset][first] = matrix[last][last - offset];
            matrix[last][last - offset] = matrix[i][last];
            matrix[i][last] = top;
        }
    }
    for(int i = 0; i< matrix.length;i++){
        System.out.println(matrix[i][i]);
    }
    System.out.println(matrix);
    return true;
}

public int[][] setZeros( int[][] matrix){
    boolean[] row = new boolean[matrix.length];
    boolean[] column = new boolean[matrix.length];
    for(int i = 0; i < matrix.length; i++){
        for(int j = 0; j < matrix.length; j++){
            if(matrix[i][j] == 0){
                row[i] = true;
                column[j] = true;
            }
        }
    }
    for(int i = 0; i < row.length; i++){
        if(row[i]){
            nullifyRow(matrix,i);
        }
    }

    for(int i = 0; i < column.length; i++){
        if(row[i]){
            nullifyColumn(matrix,i);
        }
    }

    return matrix;
}

public void nullifyRow(int[][] matrix, int row){
    for(int j = 0; j < matrix.length; j++){
        matrix[row][j] = 0;
    }
}

public void nullifyColumn(int[][] matrix, int col){
    for(int i = 0; i < matrix.length; i++){
        matrix[i][col] = 0;
    }
}



public static void main(String[] args) {
    ctcione first = new ctcione();
    /*String test = "aAbcdefghijklmnopqrstuvwxyz";
    String perm1 = "dog";
    String perm2 = "gods";
    ctcione first = new ctcione();
    first.isUniqueChars(test);
    first.sort(perm1);
    first.sort(perm2);
    if(first.permutation(perm1, perm2)){
        System.out.println("true");
    }else{
        System.out.println("false");
    };
    char[] chararr = new String("hello how is this").toCharArray();
    first.replaceSpaces(chararr, chararr.length);
    String pali ="abcdgcba";
    if(first.isPalindrome(pali)){
        System.out.println("palindrome");
    }else{
        System.out.println("isn't a palindrome");
    }
    String small = "aple";
    String big = "apple";
    if(first.oneStringAway(small,big)){
        System.out.println("One away");
    }else{
        System.out.println("Not one away");
    };
    System.out.println(first.compress("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaatest"));
    */
    int[][] matrix = new int[3][3];
    matrix[0][0] = 0;
    matrix[0][1] = 0;
    matrix[0][2] = 0;
    matrix[1][0] = 2;
    matrix[1][1] = 2;
    matrix[1][2] = 2;
    matrix[2][0] = 3;
    matrix[2][1] = 3;
    matrix[2][2] = 3;
    first.rotate(matrix);
}

}