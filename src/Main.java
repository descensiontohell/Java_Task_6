import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Task 1");
        System.out.println(bell(1));
        System.out.println(bell(2));
        System.out.println(bell(3));
        System.out.println();

        System.out.println("Task 2");
        System.out.println(translateWord("flag"));
        System.out.println(translateWord("Apple"));
        System.out.println(translateWord("button"));
        System.out.println();

        System.out.println("Task 3");
        System.out.println(true);
        System.out.println(false);
        System.out.println(false);
        System.out.println(true);
        System.out.println();

        System.out.println("Task 4");
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2"));
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2"));
        System.out.println();

        System.out.println("Task 5");
        System.out.println(getHashTags("How the Avocado Became the Fruit of the Global Trade"));
        System.out.println(getHashTags("Why You Will Probably Pay More for Your Christmas Tree This Year"));
        System.out.println(getHashTags("Hey Parents, Surprise, Fruit Juice Is Not Fruit"));
        System.out.println();

        System.out.println("Task 6");
        System.out.println(ulam(4));
        System.out.println(ulam(9));
        System.out.println(ulam(206));
        System.out.println();

        System.out.println("Task 7");
        System.out.println(longestNonrepeatingSubstring("abcabcbb"));
        System.out.println(longestNonrepeatingSubstring("aaaaaa"));
        System.out.println(longestNonrepeatingSubstring("abcde"));
        System.out.println(longestNonrepeatingSubstring("abcda"));
        System.out.println();

        System.out.println("Task 8");
        System.out.println(convertToRoman(2));
        System.out.println(convertToRoman(12));
        System.out.println(convertToRoman(16));
        System.out.println();

        System.out.println("Task 9");
        System.out.println(formula("6 * 4 = 24"));
        System.out.println(formula("18 / 17 = 2"));
        System.out.println(formula("16 * 10 = 160 = 14 + 120"));
        System.out.println();

        System.out.println("Task 10");
        System.out.println(palindromedescendant(11211230));
        System.out.println(palindromedescendant(13001120));
        System.out.println(palindromedescendant(23336014));
        System.out.println(palindromedescendant(11));
        System.out.println();
    }

    public static int bell(int num) {
        int[][] mass = new int[num + 1][num + 1];
        int sum = 0;
        mass[0][0] = 1;
        mass[num][num] = 1;
        for (int n = 1; n <= num; n++) {
            for (int i = 1; i < num; i++) {
                mass[n][i] = mass[n - 1][i - 1] + i * mass[n - 1][i];
            }
        }
        for (int i = 0; i <= num; i++) sum += mass[num][i];
        return sum;
    }

    public static String translateWord(String word){
        if (word.matches("[aeiouy]")) {
            word += "yay";
        }
        else {
            String newWord = word.split("[aeiouy]")[0];
            word = word.replaceFirst(newWord,"")+newWord+"ay";
        }
        return word;
    }

    public static String translateSentence(String word){
        String vow = "aeiouyAEIOUY";
        String[] newword = word.substring(0, word.length()-1).split(" ");
        word = "";
        for (String s : newword) {
            for (int j = 0; j < vow.length(); j++) {
                if (vow.indexOf(s.charAt(j)) != -1) {
                    word += s + "yay ";
                    break;
                } else {
                    String newWord = s.split("[aeiyouAEIYOU]")[0];
                    word += s.replaceFirst(newWord, "") + newWord + "ay ";
                    break;
                }
            }
        }
        return word + ".";
    }

    public static boolean validColor (String rgb){
        int num = rgb.indexOf('(');
        if (rgb.contains(" "))
            return false;
        String [] newrgb = rgb.substring(rgb.indexOf('(')+1,rgb.indexOf(')')).split(",");
        double [] n = new double [num];
        for (int i = 0 ; i < num; i++) {
            n[i] = Double.parseDouble(newrgb[i]);
        }
        for (int i = 0 ; i < num; i++) {
            return n[i] >= 0 && n[i] <= 255 && num == newrgb.length;
        }
        return false;
    }

    public static String stripUrlParams (String url, String ... paramsToStrip) {
        String str = "";
        if (!url.contains("?"))
            return url;
        else {
            str = url.substring(url.indexOf("?") + 1);
            url = url.substring(0, url.indexOf("?") + 1);
        }
        char[] params = str.toCharArray();

        StringBuilder print = new StringBuilder();
        for (char param : params) {
            if (Character.isLetter(param))
                if (!(print.toString().contains(String.valueOf(param)))) {
                    if (paramsToStrip.length > 0) {
                        for (String arg : paramsToStrip) {
                            if (!(arg.contains(String.valueOf(param))))
                                print.append(str.substring(str.lastIndexOf(param), str.lastIndexOf(param) + 3)).append("&");
                        }
                    }
                    else
                        print.append(str.substring(str.lastIndexOf(param), str.lastIndexOf(param) + 3)).append("&");
                }
        }
        return url + print.substring(0, print.length()-1);
    }

    public static String getHashTags(String str){
        String[] hashtags = new String[]{" ", " ", " "};
        StringBuilder buf = new StringBuilder();
        for (int i = 0 ; i<str.length();i++){
            if (str.charAt(i) != ' ')
                buf.append(str.charAt(i));
            else if (buf.length() > hashtags[0].length()) {
                hashtags[0] = buf.toString();
                buf = new StringBuilder();
            }
            else if (buf.length() > hashtags[1].length()) {
                hashtags[1] = buf.toString();
                buf = new StringBuilder();
            }
            else if (buf.length() > hashtags[2].length()) {
                hashtags[2] = buf.toString();
                buf = new StringBuilder();
            }
            else
                buf = new StringBuilder();
        }
        for (int i = 0; i < hashtags.length; i++){
            hashtags[i] = "#" + hashtags[i].toLowerCase();
        }
        return Arrays.toString(hashtags);
    }

    public static int ulam (int n){
        int[] mas = new int[n];
        mas[0]=1;
        mas[1]=2;
        int len=2, next=3;
        while (next<Integer.MAX_VALUE && len<n){
            int count =0;
            for (int i=0;i<len;i++){
                for (int j=len-1; j>i; j--){
                    if (mas[i]+mas[j]==next && mas[i]!=mas[j])
                        count++;
                    else if (count > 1)
                        break;
                }
                if (count > 1)
                    break;
            }
            if (count == 1) {
                mas[len]=next;
                len++;
            }
            next++;
        }
        return mas[n-1];
    }

    public static String longestNonrepeatingSubstring(String str){
        String podstr = "";
        char [] mass = str.toCharArray();
        StringBuilder strBuilder = new StringBuilder();
        for (char c : mass) {
            if (!strBuilder.toString().contains(String.valueOf(c)))
                strBuilder.append(c);
            else {
                if (strBuilder.length() > podstr.length())
                    podstr = strBuilder.toString();
                strBuilder = new StringBuilder("" + c);
            }
        }
        str = strBuilder.toString();
        if (str.length()>podstr.length())
            podstr=str;
        return podstr;
    }

    public static String convertToRoman (int num){
        StringBuilder roman = new StringBuilder();
        if (num < 1 || num > 3999)
            return "Введите число поменьше. ";
        while (num >= 1000) {
            roman.append("M");
            num -= 1000;        }
        while (num >= 900) {
            roman.append("CM");
            num -= 900;
        }
        while (num >= 500) {
            roman.append("D");
            num -= 500;
        }
        while (num >= 400) {
            roman.append("CD");
            num -= 400;
        }
        while (num >= 100) {
            roman.append("C");
            num -= 100;
        }
        while (num >= 90) {
            roman.append("XC");
            num -= 90;
        }
        while (num >= 50) {
            roman.append("L");
            num -= 50;
        }
        while (num >= 40) {
            roman.append("XL");
            num -= 40;
        }
        while (num >= 10) {
            roman.append("X");
            num -= 10;
        }
        while (num >= 9) {
            roman.append("IX");
            num -= 9;
        }
        while (num >= 5) {
            roman.append("V");
            num -= 5;
        }
        while (num >= 4) {
            roman.append("IV");
            num -= 4;
        }
        while (num >= 1) {
            roman.append("I");
            num -= 1;
        }
        return roman.toString();
    }

    public static boolean formula(String formula){
        String[] mass = formula.split(" ");
        int ans = 0;
        int expectedResult = 0;
        if (!Character.isDigit(mass[0].charAt(0))) return false;
        else ans = Integer.parseInt(mass[0]);
        int i = 1;
        while (!mass[i].equals("=")) {
            if (mass[i].equals("+")){
                ans += Integer.parseInt(mass[i + 1]);
            }
            if (mass[i].equals("-")){
                ans -= Integer.parseInt(mass[i + 1]);
            }
            if (mass[i].equals("*")){
                ans *= Integer.parseInt(mass[i + 1]);
            }
            if (mass[i].equals("/")){
                ans /= Integer.parseInt(mass[i + 1]);
            }
            i += 2;
        }
        i = formula.indexOf('=');
        String check = formula.substring(i + 1, formula.length());
        if (check.contains("=")) return false;
        else expectedResult = Integer.parseInt(mass[mass.length - 1]);
        return ans == expectedResult;
    }

    public static boolean palindromedescendant(int num){
        boolean bul = false;
        StringBuffer nuum =new StringBuffer(num);
        StringBuffer nuuum =new StringBuffer(num);
        if (nuum.length()%2!=0)
            return false;
        else{
            while (!bul){
                if(nuum != nuum.reverse()){
                    for(int i=0; i<nuum.length();i+=2){
                        int a = Integer.parseInt(String.valueOf(nuum.charAt(i)));
                        int b = Integer.parseInt(String.valueOf(nuum.charAt(i+1)));
                        nuuum.append(a+b);
                    }
                }
                else
                    bul = true;
            }
        }
        return bul;
    }
}
